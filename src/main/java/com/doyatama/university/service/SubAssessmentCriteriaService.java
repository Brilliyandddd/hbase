package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.AssessmentCriteria;
import com.doyatama.university.model.SubAssessmentCriteria;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.SubAssessmentCriteriaRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.SubAssessmentCriteriaRepository;
import com.doyatama.university.repository.AssessmentCriteriaRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SubAssessmentCriteriaService {
    private AssessmentCriteriaRepository assessmentCriteriaRepository = new AssessmentCriteriaRepository();
    private SubAssessmentCriteriaRepository subAssessmentCriteriaRepository = new SubAssessmentCriteriaRepository();

    private static final Logger logger = LoggerFactory.getLogger(SubAssessmentCriteriaService.class);


    public PagedResponse<SubAssessmentCriteria> getAllSubAssessmentCriteria(int page, int size, String assessmentCriteriaId) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<SubAssessmentCriteria> subAssessmentCriteriaResponse = subAssessmentCriteriaRepository.findAll(size);


        return new PagedResponse<>(subAssessmentCriteriaResponse, subAssessmentCriteriaResponse.size(), "Successfully get data", 200);
    }

    public SubAssessmentCriteria createSubAssessmentCriteria(SubAssessmentCriteriaRequest subAssessmentCriteriaRequest) throws IOException {
        
        logger.info("Received SubAssessmentCriteriaRequest: {}", subAssessmentCriteriaRequest);

    if (subAssessmentCriteriaRequest.getName() == null || subAssessmentCriteriaRequest.getName().isEmpty()) {
        throw new BadRequestException("Name cannot be null or empty.");
    }

    if (subAssessmentCriteriaRequest.getDescription() == null || subAssessmentCriteriaRequest.getDescription().isEmpty()) {
        throw new BadRequestException("Description cannot be null or empty.");
    }

    if (subAssessmentCriteriaRequest.getWeight() == 0.0) {
        throw new BadRequestException("Weight cannot be zero.");
    }

        
        SubAssessmentCriteria subAssessmentCriteria = new SubAssessmentCriteria();

        AssessmentCriteria assessmentCriteriaResponse = assessmentCriteriaRepository.findById(subAssessmentCriteriaRequest.getAssessmentCriteriaId().toString());
        if (assessmentCriteriaResponse == null) {
            throw new ResourceNotFoundException("AssessmentCriteria", "id", subAssessmentCriteriaRequest.getAssessmentCriteriaId());
        }
    
        logger.info("AssessmentCriteria found: {}", assessmentCriteriaResponse);

        subAssessmentCriteria.setSubAssessmentCriteriaId(subAssessmentCriteriaRequest.getSubAssessmentCriteriaId());
        subAssessmentCriteria.setName(subAssessmentCriteriaRequest.getName());
        subAssessmentCriteria.setDescription(subAssessmentCriteriaRequest.getDescription());
        subAssessmentCriteria.setWeight(subAssessmentCriteriaRequest.getWeight());
        subAssessmentCriteria.setAssessmentCriteria(assessmentCriteriaResponse);

        logger.info("Saving SubAssessmentCriteria: {}", subAssessmentCriteria);

        return subAssessmentCriteriaRepository.save(subAssessmentCriteria);
    }

    public DefaultResponse<SubAssessmentCriteria> getSubAssessmentCriteriaById(String subAssessmentCriteriaId) throws IOException {
        SubAssessmentCriteria subAssessmentCriteriaResponse = subAssessmentCriteriaRepository.findSubAssessmentCriteriaById(subAssessmentCriteriaId);
        return new DefaultResponse<>(subAssessmentCriteriaResponse.isValid() ? subAssessmentCriteriaResponse : null,
        subAssessmentCriteriaResponse.isValid() ? 1 : 0, "Successfully get data", 200);
    }
   
    

    public SubAssessmentCriteria updateSubAssessmentCriteria(String subAssessmentCriteriaId, SubAssessmentCriteriaRequest subAssessmentCriteriaRequest) throws IOException {
        SubAssessmentCriteria subAssessmentCriteria = new SubAssessmentCriteria();
        AssessmentCriteria assessmentCriteriaResponse = assessmentCriteriaRepository.findById(subAssessmentCriteriaRequest.getAssessmentCriteriaId().toString());

        if (assessmentCriteriaResponse != null) {
            subAssessmentCriteria.setName(subAssessmentCriteriaRequest.getName());
            subAssessmentCriteria.setDescription(subAssessmentCriteriaRequest.getDescription());
            subAssessmentCriteria.setWeight(subAssessmentCriteriaRequest.getWeight());
            subAssessmentCriteria.setAssessmentCriteria(assessmentCriteriaResponse);

        return subAssessmentCriteriaRepository.update(subAssessmentCriteriaId, subAssessmentCriteria);
        } else {
            throw new ResourceNotFoundException("AssessmentCriteria", "id", subAssessmentCriteriaRequest.getAssessmentCriteriaId());
        }
    }

    public void deleteSubAssessmentCriteriaById(String subAssessmentCriteriaId) throws IOException {
        SubAssessmentCriteria subAssessmentCriteria = subAssessmentCriteriaRepository.findSubAssessmentCriteriaById(subAssessmentCriteriaId);
        if (subAssessmentCriteria.isValid()) {
            subAssessmentCriteriaRepository.deleteById(subAssessmentCriteriaId);
        } else {
            throw new ResourceNotFoundException("SubAssessmentCriteria", "id", subAssessmentCriteriaId);
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
