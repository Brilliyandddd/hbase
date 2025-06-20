package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.QuestionRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.ExamTypeRepository;
import com.doyatama.university.repository.ExerciseAttemptRepository;
import com.doyatama.university.repository.RPSDetailRepository;
import com.doyatama.university.repository.RPSRepository;
import com.doyatama.university.repository.QuestionRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository questionRepository = new QuestionRepository();
    private RPSDetailRepository rpsDetailRepository = new RPSDetailRepository();
    private RPSRepository rpsRepository = new RPSRepository(); 

    private ExamTypeRepository examTypeRepository = new ExamTypeRepository();

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);


    public PagedResponse<Question> getAllQuestion(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Question> questionsList = questionRepository.findAll(size);
        // This loop calls the getters, which now trigger deserialization in the repository
        questionsList.forEach(q -> {
            q.getQuestionRating(); 
            q.getCriteriaValues(); 
            q.setIs_rated(q.getQuestionRating() != null && !q.getQuestionRating().getReviewerRatings().isEmpty());
        });

        return new PagedResponse<>(questionsList, questionsList.size(),"Successfully retrieved data", 200);
    } 

    public PagedResponse<Question> getAllQuestionsByRPS(int page, int size, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Question> questionResponse = new ArrayList<>();

        if(rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAll(size);
        } else {
            questionResponse = questionRepository.findAllByRPS(rpsID, size);
        }

        // This loop calls the getters, which now trigger deserialization in the repository
        questionResponse.forEach(q -> {
            q.getQuestionRating(); 
            q.getCriteriaValues(); 
            q.setIs_rated(q.getQuestionRating() != null && !q.getQuestionRating().getReviewerRatings().isEmpty());
        });
        
        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public Question createQuestion(QuestionRequest questionRequest, String savePath) throws IOException {
        RPSDetail rpsDetailResponse = rpsDetailRepository.findById(questionRequest.getRps_detail_id());
        if (rpsDetailResponse == null) {
            throw new IllegalArgumentException("RPS Detail dengan ID " + questionRequest.getRps_detail_id() + " tidak ditemukan.");
        }

        if (questionRequest.getRps_detail_id() == null || questionRequest.getRps_detail_id().trim().isEmpty()) {
            throw new IllegalArgumentException("rps_detail_id wajib diisi.");
        }
        
        RPS rpsResponse = rpsRepository.findById(questionRequest.getIdRps());
        if (rpsResponse == null) {
            throw new IllegalArgumentException("RPS dengan ID " + rpsDetailResponse.getRps().getIdRps() + " tidak ditemukan.");
        }

        System.out.println("DATA " + questionRequest); 

        Question question = new Question();

        question.setIdQuestion(questionRequest.getIdQuestion());
        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());
        question.setQuestionType(Question.QuestionType.valueOf(questionRequest.getQuestion_type()));
        question.setAnswerType(Question.AnswerType.valueOf(questionRequest.getAnswer_type()));

        question.setIs_rated(false); 
        if (questionRequest.getIs_rated() != null) { 
            question.setIs_rated(questionRequest.getIs_rated());
        }

        if (questionRequest.getExamType() != null && !questionRequest.getExamType().trim().isEmpty()) {
            question.setExamType(Question.ExamType.valueOf(questionRequest.getExamType()));
        } else {
            question.setExamType(null);
        }

        if (questionRequest.getExamType2() != null && !questionRequest.getExamType2().trim().isEmpty()) {
            question.setExamType2(Question.ExamType2.valueOf(questionRequest.getExamType2()));
        } else {
            question.setExamType2(null);
        }

        if (questionRequest.getExamType3() != null && !questionRequest.getExamType3().trim().isEmpty()) {
            question.setExamType3(Question.ExamType3.valueOf(questionRequest.getExamType3()));
        } else {
            question.setExamType3(null);
        }

        question.setExplanation(questionRequest.getExplanation());
        question.setFile_path(savePath);
        question.setRps(rpsResponse);
        question.setRps_detail_id(rpsDetailResponse);
        
        return questionRepository.save(question);
    }

    public Question.QuestionRating ratingQuestion(String questionId, QuestionRequest questionRequest) throws IOException {
        Question question = questionRepository.findById(questionId);
        if (question == null) {
            throw new ResourceNotFoundException("Question", "idQuestion", questionId);
        }

        if (questionRequest.getReviewer() == null || questionRequest.getReviewer().trim().isEmpty()) {
            throw new IllegalArgumentException("Reviewer cannot be null or empty");
        }

        String normalizedReviewerId = questionRequest.getReviewer().toLowerCase();

        Question.QuestionRating currentRating = question.getQuestionRating();
        if (currentRating == null) {
            currentRating = new Question.QuestionRating();
            currentRating.setIdQuestion(questionId);
        }

        // CRITICAL CHANGE: Instantiate ReviewerRating directly with all fields
        Question.ReviewerRating reviewerRating = new Question.ReviewerRating(
            questionRequest.getAverageValue1(), questionRequest.getAverageValue2(),
            questionRequest.getAverageValue3(), questionRequest.getAverageValue4(),
            questionRequest.getAverageValue5(), questionRequest.getAverageValue6(),
            questionRequest.getAverageValue7(), questionRequest.getAverageValue8(),
            questionRequest.getAverageValue9(), questionRequest.getAverageValue10(),
            // Pass the linguistic IDs from the request
            questionRequest.getLinguisticValue1Id(), questionRequest.getLinguisticValue2Id(),
            questionRequest.getLinguisticValue3Id(), questionRequest.getLinguisticValue4Id(),
            questionRequest.getLinguisticValue5Id(), questionRequest.getLinguisticValue6Id(),
            questionRequest.getLinguisticValue7Id(), questionRequest.getLinguisticValue8Id(),
            questionRequest.getLinguisticValue9Id(), questionRequest.getLinguisticValue10Id()
        );

        currentRating.addReviewerRating(normalizedReviewerId, reviewerRating);
        question.setQuestionRating(currentRating); // This setter will serialize currentRating back to questionRatingJson
        question.setIs_rated(!currentRating.getReviewerRatings().isEmpty());

        System.out.println("DEBUG: Saving rating for question " + questionId);
        System.out.println("DEBUG: Reviewer: " + normalizedReviewerId);
        System.out.println("DEBUG: is_rated: " + question.isIs_rated());
        System.out.println("DEBUG: QuestionRating JSON to save: " + question.getQuestionRatingJson()); // Check the JSON here

        return questionRepository.saveQuestionRating(question);
    }

    public DefaultResponse<Question> getQuestionById(String questionId) throws IOException {
        Question questionResponse = questionRepository.findById(questionId);
        if (questionResponse != null) {
            questionResponse.getQuestionRating(); 
            questionResponse.getCriteriaValues(); 
            questionResponse.setIs_rated(questionResponse.getQuestionRating() != null && !questionResponse.getQuestionRating().getReviewerRatings().isEmpty());
        }
        return new DefaultResponse<>(questionResponse != null && questionResponse.isValid() ? questionResponse : null, questionResponse != null && questionResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public DefaultResponse<Question> getQuestionByIdPaged(String questionId) throws IOException {
        Question questionResponse = questionRepository.findById(questionId);
        if (questionResponse != null) {
            questionResponse.getQuestionRating(); 
            questionResponse.getCriteriaValues(); 
            questionResponse.setIs_rated(questionResponse.getQuestionRating() != null && !questionResponse.getQuestionRating().getReviewerRatings().isEmpty());
        }
        return new DefaultResponse<>(questionResponse != null && questionResponse.isValid() ? questionResponse : null, questionResponse != null && questionResponse.isValid() ? 1 : 0, "Succesfully Get Data");
    }

    public List<Question> findQuestionsByIds(List<String> questionIds) throws IOException {
        logger.info("QuestionService: Mencari pertanyaan berdasarkan daftar ID: {}", questionIds);
        if (questionIds == null || questionIds.isEmpty()) {
            logger.warn("QuestionService: Daftar ID pertanyaan yang diterima kosong.");
            return Collections.emptyList();
        }

        List<Question> fetchedQuestions = new ArrayList<>();
        for (String questionId : questionIds) {
            Question question = questionRepository.findById(questionId);
            if (question != null && question.isValid()) {
                question.getQuestionRating(); 
                question.getCriteriaValues(); 
                question.setIs_rated(question.getQuestionRating() != null && !question.getQuestionRating().getReviewerRatings().isEmpty());
                
                fetchedQuestions.add(question);
                logger.info("QuestionService: Ditemukan pertanyaan ID {} dari repository.", questionId);
            } else {
                logger.warn("QuestionService: Pertanyaan dengan ID {} tidak ditemukan atau tidak valid dari repository. Melewati.", questionId);
            }
        }
        logger.info("QuestionService: Mengembalikan {} pertanyaan yang ditemukan untuk ID yang diminta.", fetchedQuestions.size());
        return fetchedQuestions;
    }

    public Question updateQuestion(String questionId, QuestionRequest questionRequest) throws IOException {
        Question question = questionRepository.findById(questionId);
        
        if (question != null) {
            RPSDetail rpsDetailResponse = rpsDetailRepository.findById(questionRequest.getRps_detail_id().toString());
            if (rpsDetailResponse.getSub_cp_mk() != null) {
                question.setExplanation(questionRequest.getExplanation());
                question.setTitle(questionRequest.getTitle());
                question.setDescription(questionRequest.getDescription());
                question.setQuestionType(Question.QuestionType.valueOf(questionRequest.getQuestion_type()));
                question.setAnswerType(Question.AnswerType.valueOf(questionRequest.getAnswer_type()));
                question.setExamType(Question.ExamType.valueOf(questionRequest.getExamType()));
                question.setRps_detail_id(rpsDetailResponse);
                
                if (questionRequest.getIs_rated() != null) { 
                    question.setIs_rated(questionRequest.getIs_rated());
                } else {
                    question.setIs_rated(question.getQuestionRating() != null && !question.getQuestionRating().getReviewerRatings().isEmpty());
                }

                return questionRepository.update(questionId,question);
            } else {
                return null;
            }
        } else {
            throw new ResourceNotFoundException("Question", "idQuestion", questionId);
        }
    }
    public void deleteQuestionById(String rps_detail_id) throws IOException {
        Question questionResponse = questionRepository.findById(rps_detail_id);
        if(questionResponse.isValid()){
            questionRepository.deleteById(rps_detail_id);
        }else{
            throw new ResourceNotFoundException("RPSDetail", "id", rps_detail_id);
        }
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}