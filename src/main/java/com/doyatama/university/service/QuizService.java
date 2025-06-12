package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.payload.QuizRequest;
import com.doyatama.university.repository.QuizRepository;
import com.doyatama.university.repository.RPSRepository;
import com.doyatama.university.repository.QuestionRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors; // Tambahkan import ini

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final RPSRepository rpsRepository;
    private final QuestionRepository questionRepository;

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    public QuizService(QuizRepository quizRepository, RPSRepository rpsRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.rpsRepository = rpsRepository;
        this.questionRepository = questionRepository;
    }

    public PagedResponse<Quiz> getAllQuiz(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Quiz> quizzesList = quizRepository.findAll(size);
        // NEW: Populate full Question objects for each quiz after fetching from DB
        for (Quiz quiz : quizzesList) {
            if (quiz.getQuestionsRaw() != null && !quiz.getQuestionsRaw().isEmpty()) {
                List<String> questionIds = Arrays.asList(quiz.getQuestionsRaw().split(";")); // Parse IDs from raw string
                List<Question> fetchedQuestions = new ArrayList<>();
                for (String questionId : questionIds) {
                    Question question = questionRepository.findById(questionId); // Assuming findById returns full Question object
                    if (question != null && question.isValid()) {
                        fetchedQuestions.add(question);
                    } else {
                        logger.warn("Question with ID {} not found or invalid during findAll quiz. Skipping.", questionId);
                    }
                }
                quiz.setQuestions(fetchedQuestions); // Set List<Question> objects
            }
        }
        return new PagedResponse<>(quizzesList, quizzesList.size(), "Successfully retrieved data", 200);
    }

    public Quiz getQuizById(String quizId) throws IOException {
        logger.info("QuizService: Menerima permintaan untuk quizId: {}", quizId);
        Quiz quiz = quizRepository.findById(quizId);
        
        // NEW: Populate full Question objects for the specific quiz
        if (quiz != null && quiz.getQuestionsRaw() != null && !quiz.getQuestionsRaw().isEmpty()) {
            List<String> questionIds = Arrays.asList(quiz.getQuestionsRaw().split(";")); // Parse IDs from raw string
            List<Question> fetchedQuestions = new ArrayList<>();
            for (String questionId : questionIds) {
                Question question = questionRepository.findById(questionId);
                if (question != null && question.isValid()) {
                    fetchedQuestions.add(question);
                } else {
                    logger.warn("Question with ID {} not found or invalid during getQuizById. Skipping.", questionId);
                }
            }
            quiz.setQuestions(fetchedQuestions); // Set List<Question> objects
        }
        return quiz;
    }

    public PagedResponse<Question> getAllQuestionsByRPSQuiz1(int page, int size, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Question> questionResponse;
        if (rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAll(size);
        } else {
            questionResponse = questionRepository.findAllByRPS(rpsID, size);
        }
        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public PagedResponse<Question> getAllQuestionsByRPSQuiz2(int page, int size, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Question> questionResponse;
        if (rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAll(size);
        } else {
            questionResponse = questionRepository.findAllByRPS(rpsID, size);
        }
        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public Quiz createQuiz(QuizRequest quizRequest) throws IOException {
        RPS rps = rpsRepository.findById(quizRequest.getRps_id());
        if (rps == null || !rps.isValid()) {
            throw new ResourceNotFoundException("RPS", "idQuiz", quizRequest.getRps_id());
        }

        Quiz quiz = new Quiz();
        quiz.setName(quizRequest.getName());
        quiz.setDescription(quizRequest.getDescription());
        quiz.setMin_grade(quizRequest.getMin_grade());
        quiz.setDuration(quizRequest.getDuration());
        
        quiz.setDate_start(quizRequest.getDate_start().toString());
        quiz.setDate_end(quizRequest.getDate_end().toString());
        
        quiz.setDeveloperId(quizRequest.getDeveloperId());
        quiz.setInstructorId(quizRequest.getInstructorId());
        quiz.setCoordinatorId(quizRequest.getCoordinatorId());
        quiz.setMessage(quizRequest.getMessage());
        quiz.setType_quiz(quizRequest.getType_quiz());
        quiz.setRps(rps);

        // NEW: Langsung set questionsRaw dari List<String> ID yang datang dari frontend
        if (quizRequest.getQuestions() != null) { // quizRequest.getQuestions() adalah List<String> IDs dari frontend
            quiz.setQuestionsRaw(String.join(";", quizRequest.getQuestions())); 
        } else {
            quiz.setQuestionsRaw(""); // Set kosong jika tidak ada pertanyaan
        }
        
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(String quizId, QuizRequest quizRequest) throws IOException {
        Quiz existingQuiz = quizRepository.findById(quizId);
        if (existingQuiz == null) {
            throw new ResourceNotFoundException("Quiz", "idQuiz", quizId);
        }

        RPS rps = rpsRepository.findById(quizRequest.getRps_id());
        if (rps == null || !rps.isValid()) {
            throw new ResourceNotFoundException("RPS", "idRps", quizRequest.getRps_id());
        }

        existingQuiz.setName(quizRequest.getName());
        existingQuiz.setDescription(quizRequest.getDescription());
        existingQuiz.setMin_grade(quizRequest.getMin_grade());
        existingQuiz.setDuration(quizRequest.getDuration());
        
        existingQuiz.setDate_start(quizRequest.getDate_start().toString());
        existingQuiz.setDate_end(quizRequest.getDate_end().toString());
        
        existingQuiz.setDeveloperId(quizRequest.getDeveloperId());
        existingQuiz.setInstructorId(quizRequest.getInstructorId());
        existingQuiz.setCoordinatorId(quizRequest.getCoordinatorId());
        existingQuiz.setMessage(quizRequest.getMessage());
        existingQuiz.setType_quiz(quizRequest.getType_quiz());
        existingQuiz.setRps(rps);

        // NEW: Langsung set questionsRaw dari List<String> ID yang datang dari frontend
        if (quizRequest.getQuestions() != null) { // quizRequest.getQuestions() adalah List<String> IDs
            existingQuiz.setQuestionsRaw(String.join(";", quizRequest.getQuestions()));
        } else {
            existingQuiz.setQuestionsRaw(""); // Set kosong jika tidak ada pertanyaan
        }

        return quizRepository.update(quizId, existingQuiz);
    }


    public void deleteQuiz(String quizId) throws IOException {
        Quiz quiz = quizRepository.findById(quizId);
        if (quiz == null || !quiz.isValid()) { // Check for null and validity
            throw new ResourceNotFoundException("Quiz", "idQuiz", quizId);
        } else {
            quizRepository.deleteById(quizId);
        }
    }


    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}