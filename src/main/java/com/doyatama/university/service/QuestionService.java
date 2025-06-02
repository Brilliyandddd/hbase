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

    return new PagedResponse<>(questionsList, questionsList.size(),"Successfully retrieved data", 200);
}   

    public PagedResponse<Question> getAllQuestionsByRPS(int page, int size, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Question> questionResponse = new ArrayList<>();

        // if rpsID is "*"
        if(rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAll(size);
        }

        // if rpsID is not "*"
        if(!rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAllByRPS(rpsID, size);
        }

        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public Question createQuestion(QuestionRequest questionRequest, String savePath) throws IOException {

    // Validasi RPS Detail
    if (questionRequest.getRps_detail_id() == null || questionRequest.getRps_detail_id().trim().isEmpty()) {
        throw new IllegalArgumentException("rps_detail_id wajib diisi.");
    }
    RPSDetail rpsDetailResponse = rpsDetailRepository.findById(questionRequest.getRps_detail_id());
    if (rpsDetailResponse == null) {
        throw new IllegalArgumentException("RPS Detail dengan ID " + questionRequest.getRps_detail_id() + " tidak ditemukan.");
    }
    // Validasi RPS
    RPS rpsResponse = rpsRepository.findById(questionRequest.getIdRps());
    if (rpsResponse == null) {
        throw new IllegalArgumentException("RPS dengan ID " + rpsDetailResponse.getRps().getIdRps() + " tidak ditemukan.");
    }

    System.out.println("DATA " + questionRequest);

    Question question = new Question();

    question.setIdQuestion(questionRequest.getId());
    question.setTitle(questionRequest.getTitle());
    question.setDescription(questionRequest.getDescription());
    question.setQuestionType(Question.QuestionType.valueOf(questionRequest.getQuestion_type()));
    question.setAnswerType(Question.AnswerType.valueOf(questionRequest.getAnswer_type()));

    // --- FIX START ---
    // Conditionally set examType if the request value is not null
    if (questionRequest.getExamType() != null && !questionRequest.getExamType().trim().isEmpty()) {
        question.setExamType(Question.ExamType.valueOf(questionRequest.getExamType()));
    } else {
        question.setExamType(null); // Or set a default enum value if applicable
    }

    // Conditionally set examType2 if the request value is not null
    if (questionRequest.getExamType2() != null && !questionRequest.getExamType2().trim().isEmpty()) {
        question.setExamType2(Question.ExamType2.valueOf(questionRequest.getExamType2()));
    } else {
        question.setExamType2(null); // Or set a default enum value if applicable
    }

    // examType3 is already coming as "EXAM", so it should be fine.
    if (questionRequest.getExamType3() != null && !questionRequest.getExamType3().trim().isEmpty()) {
         question.setExamType3(Question.ExamType3.valueOf(questionRequest.getExamType3()));
    } else {
         question.setExamType3(null); // Or set a default enum value if applicable
    }
    // --- FIX END ---

    question.setExplanation(questionRequest.getExplanation());
    question.setFile_path(savePath);
    question.setRps(rpsResponse);
    question.setRps_detail_id(rpsDetailResponse);

    return questionRepository.save(question);
}

    public DefaultResponse<Question> getQuestionById(String questionId) throws IOException {
        // Retrieve Question
        Question questionResponse = questionRepository.findById(questionId);
        return new DefaultResponse<>(questionResponse.isValid() ? questionResponse : null, questionResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public DefaultResponse<Question> getQuestionByIdPaged(String questionId) throws IOException {
        // Retrieve Question
        Question questionResponse = questionRepository.findById(questionId);
        return new DefaultResponse<>(questionResponse.isValid() ? questionResponse : null, questionResponse.isValid() ? 1 : 0, "Succesfully Get Data");
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