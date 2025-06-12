package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.CriteriaValueRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.*;
import com.doyatama.university.util.AppConstants;
import com.doyatama.university.service.QuestionService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author alfa
 */
@Service
public class CriteriaValueService {
    private CriteriaValueRepository criteriaValueRepository = new CriteriaValueRepository();
    private LinguisticValueRepository linguisticValueRepository = new LinguisticValueRepository();
    private QuizAnnouncementRepository quizAnnouncementRepository = new QuizAnnouncementRepository();
    private TeamTeachingRepository teamTeachingRepository = new TeamTeachingRepository();
    private LectureRepository lectureRepository = new LectureRepository();
    private QuestionRepository questionRepository = new QuestionRepository();
    private UserRepository userRepository = new UserRepository();
    
    
    QuestionService questionService = new QuestionService();

    private static final Logger logger = LoggerFactory.getLogger(CriteriaValueService.class);

    public PagedResponse<CriteriaValue> getAllCriteriaValueByQuestion(String questionId, int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve CriteriaValue by questionId
        List<CriteriaValue> criteriaValueResponse;
        if (questionId == null || questionId.isEmpty()) {
            // Tanpa filter pertanyaan
            criteriaValueResponse = criteriaValueRepository.findAll(size);
        } else {
            // Filter berdasarkan pertanyaan
            criteriaValueResponse = criteriaValueRepository.findAllByQuestion(questionId, size);
        }
        return new PagedResponse<>(criteriaValueResponse, criteriaValueResponse.size(), "Successfully get data", 200);
    }

    public PagedResponse<CriteriaValue> getAllCriteriaValueByUser(String userId, int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve CriteriaValue by userId
        List<CriteriaValue> criteriaValueResponse = criteriaValueRepository.findByUser(userId, size);
        return new PagedResponse<>(criteriaValueResponse, criteriaValueResponse.size(), "Successfully get data", 200);
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    public CriteriaValue createCriteriaValue(CriteriaValueRequest criteriaValueRequest, String questionId) throws IOException {
        CriteriaValue criteriaValue = new CriteriaValue();

        // Validate and get question
        Question questionResponse = questionRepository.findById(criteriaValueRequest.getIdQuestion());
        if (questionResponse == null) {
            throw new ResourceNotFoundException("Question", "idQuestion", criteriaValueRequest.getIdQuestion());
        }

        // Get TeamTeaching if provided
        TeamTeaching teamTeaching = null;
        if (criteriaValueRequest.getTeamTeachingId() != null && !criteriaValueRequest.getTeamTeachingId().isEmpty()) {
            teamTeaching = teamTeachingRepository.findById(criteriaValueRequest.getTeamTeachingId());
        }

        // Get Lecture if provided
        Lecture lecture = null;
        if (criteriaValueRequest.getLecture() != null && !criteriaValueRequest.getLecture().isEmpty()) {
            lecture = lectureRepository.findById(criteriaValueRequest.getLecture());
        }

        // Get all linguistic values (including value10)
        LinguisticValue value1 = linguisticValueRepository.findById(criteriaValueRequest.getValue1());
        LinguisticValue value2 = linguisticValueRepository.findById(criteriaValueRequest.getValue2());
        LinguisticValue value3 = linguisticValueRepository.findById(criteriaValueRequest.getValue3());
        LinguisticValue value4 = linguisticValueRepository.findById(criteriaValueRequest.getValue4());
        LinguisticValue value5 = linguisticValueRepository.findById(criteriaValueRequest.getValue5());
        LinguisticValue value6 = linguisticValueRepository.findById(criteriaValueRequest.getValue6());
        LinguisticValue value7 = linguisticValueRepository.findById(criteriaValueRequest.getValue7());
        LinguisticValue value8 = linguisticValueRepository.findById(criteriaValueRequest.getValue8());
        LinguisticValue value9 = linguisticValueRepository.findById(criteriaValueRequest.getValue9());
        
        // Handle value10 if provided
        LinguisticValue value10 = null;
        if (criteriaValueRequest.getValue10() != null && !criteriaValueRequest.getValue10().isEmpty()) {
            value10 = linguisticValueRepository.findById(criteriaValueRequest.getValue10());
        }

        // Validate required linguistic values (1-9 are required, 10 is optional)
        if (value1 == null || value1.getName() == null ||
            value2 == null || value2.getName() == null ||
            value3 == null || value3.getName() == null ||
            value4 == null || value4.getName() == null ||
            value5 == null || value5.getName() == null ||
            value6 == null || value6.getName() == null ||
            value7 == null || value7.getName() == null ||
            value8 == null || value8.getName() == null ||
            value9 == null || value9.getName() == null) {
            
            throw new BadRequestException("One or more required linguistic values (value1-value9) are missing or invalid");
        }

        // Set all values in criteriaValue
        criteriaValue.setQuestion(questionResponse);
        criteriaValue.setUser(criteriaValueRequest.getUser_id());
        criteriaValue.setTeamTeaching(teamTeaching);
        criteriaValue.setLecture(lecture);
        criteriaValue.setValue1(value1);
        criteriaValue.setValue2(value2);
        criteriaValue.setValue3(value3);
        criteriaValue.setValue4(value4);
        criteriaValue.setValue5(value5);
        criteriaValue.setValue6(value6);
        criteriaValue.setValue7(value7);
        criteriaValue.setValue8(value8);
        criteriaValue.setValue9(value9);
        
        if (value10 != null) {
            criteriaValue.setValue10(value10);
        }

        return criteriaValueRepository.save(criteriaValue, questionId);
    }

    // public PagedResponse<CriteriaValue> getQuestionsWithCriteriaValuesFromQuizAnnouncement(String quizAnnouncementId, int page, int size) throws IOException {
    //     validatePageNumberAndSize(page, size);

    //     QuizAnnouncement quizAnnouncement = quizAnnouncementRepository.findById(quizAnnouncementId);
    //     if (quizAnnouncement == null) {
    //         throw new ResourceNotFoundException("QuizAnnouncement", "id", quizAnnouncementId);
    //     }

    //     // Convert questions from QuizAnnouncement
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     List<Question> questions = objectMapper.convertValue(quizAnnouncement.getQuestions(), new TypeReference<List<Question>>() {});

    //     logger.info("Questions size: " + questions.size());

    //     List<CriteriaValue> criteriaValueResponse = new ArrayList<>();

    //     for (Question question : questions) {
    //         List<CriteriaValue> criteriaValues = criteriaValueRepository.findAllByQuestion(question.getIdQuestion(), Integer.MAX_VALUE);
            
    //         logger.info("CriteriaValues size for question " + question.getIdQuestion() + ": " + criteriaValues.size());

    //         question.setCriteriaValues(criteriaValues);
    //         criteriaValueResponse.addAll(criteriaValues);

    //         // Calculate averages for each value type
    //         if (!criteriaValues.isEmpty()) {
    //             // Calculate average for value1
    //             double sum1 = criteriaValues.stream()
    //                 .filter(cv -> cv.getValue1() != null && cv.getValue1().getAverage() != null)
    //                 .mapToDouble(cv -> Double.parseDouble(cv.getValue1().getAverage()))
    //                 .sum();
    //             double average1 = sum1 / criteriaValues.size();
    //             question.setAverageValue1(average1);

    //             // Calculate average for value2
    //             double sum2 = criteriaValues.stream()
    //                 .filter(cv -> cv.getValue2() != null && cv.getValue2().getAverage() != null)
    //                 .mapToDouble(cv -> Double.parseDouble(cv.getValue2().getAverage()))
    //                 .sum();
    //             double average2 = sum2 / criteriaValues.size();
    //             question.setAverageValue2(average2);

    //             // Calculate average for value3
    //             double sum3 = criteriaValues.stream()
    //                 .filter(cv -> cv.getValue3() != null && cv.getValue3().getAverage() != null)
    //                 .mapToDouble(cv -> Double.parseDouble(cv.getValue3().getAverage()))
    //                 .sum();
    //             double average3 = sum3 / criteriaValues.size();
    //             question.setAverageValue3(average3);

    //             // Add more averages as needed for value4-value10
    //         }
    //     }

    //     return new PagedResponse<>(criteriaValueResponse, criteriaValueResponse.size(), "Successfully get data", 200);
    // }

    public DefaultResponse<CriteriaValue> getCriteriaValueById(String criteriaValueId) throws IOException {
        CriteriaValue criteriaValueResponse = criteriaValueRepository.findById(criteriaValueId);
        
        if (criteriaValueResponse == null) {
            throw new ResourceNotFoundException("CriteriaValue", "id", criteriaValueId);
        }
        
        return new DefaultResponse<>(criteriaValueResponse.isValid() ? criteriaValueResponse : null, 
                                   criteriaValueResponse.isValid() ? 1 : 0, 
                                   "Successfully get data");
    }

    public CriteriaValue updateCriteriaValue(String criteriaValueId, CriteriaValueRequest criteriaValueRequest) throws IOException {
        // Check if CriteriaValue exists
        CriteriaValue existingCriteriaValue = criteriaValueRepository.findById(criteriaValueId);
        if (existingCriteriaValue == null) {
            throw new ResourceNotFoundException("CriteriaValue", "id", criteriaValueId);
        }

        // Create repositories
        QuestionRepository questionRepository = new QuestionRepository();
        TeamTeachingRepository teamTeachingRepository = new TeamTeachingRepository();
        LectureRepository lectureRepository = new LectureRepository();

        // Create updated CriteriaValue
        CriteriaValue updatedCriteriaValue = new CriteriaValue();
        updatedCriteriaValue.setId(criteriaValueId);

        // Update question if provided
        if (criteriaValueRequest.getIdQuestion() != null) {
            Question question = questionRepository.findById(criteriaValueRequest.getIdQuestion());
            if (question == null) {
                throw new ResourceNotFoundException("Question", "idQuestion", criteriaValueRequest.getIdQuestion());
            }
            updatedCriteriaValue.setQuestion(question);
        }

        // Update user if provided
        if (criteriaValueRequest.getUser_id() != null) {
            updatedCriteriaValue.setUser(criteriaValueRequest.getUser_id());
        }

        // Update linguistic values
        String[] valueIds = {
            criteriaValueRequest.getValue1(), criteriaValueRequest.getValue2(),
            criteriaValueRequest.getValue3(), criteriaValueRequest.getValue4(),
            criteriaValueRequest.getValue5(), criteriaValueRequest.getValue6(),
            criteriaValueRequest.getValue7(), criteriaValueRequest.getValue8(),
            criteriaValueRequest.getValue9(), criteriaValueRequest.getValue10()
        };

        for (int i = 0; i < valueIds.length; i++) {
            if (valueIds[i] != null && !valueIds[i].isEmpty()) {
                LinguisticValue linguisticValue = linguisticValueRepository.findById(valueIds[i]);
                if (linguisticValue != null) {
                    switch (i + 1) {
                        case 1: updatedCriteriaValue.setValue1(linguisticValue); break;
                        case 2: updatedCriteriaValue.setValue2(linguisticValue); break;
                        case 3: updatedCriteriaValue.setValue3(linguisticValue); break;
                        case 4: updatedCriteriaValue.setValue4(linguisticValue); break;
                        case 5: updatedCriteriaValue.setValue5(linguisticValue); break;
                        case 6: updatedCriteriaValue.setValue6(linguisticValue); break;
                        case 7: updatedCriteriaValue.setValue7(linguisticValue); break;
                        case 8: updatedCriteriaValue.setValue8(linguisticValue); break;
                        case 9: updatedCriteriaValue.setValue9(linguisticValue); break;
                        case 10: updatedCriteriaValue.setValue10(linguisticValue); break;
                    }
                }
            }
        }

        return criteriaValueRepository.update(criteriaValueId, updatedCriteriaValue);
    }

    public void deleteCriteriaValueById(String criteriaValueId) throws IOException {
        CriteriaValue criteriaValueResponse = criteriaValueRepository.findById(criteriaValueId);
        
        if (criteriaValueResponse == null) {
            throw new ResourceNotFoundException("CriteriaValue", "id", criteriaValueId);
        }
        
        criteriaValueRepository.deleteById(criteriaValueId);
    }
}