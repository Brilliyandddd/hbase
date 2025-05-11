package com.doyatama.university.controller;

import com.doyatama.university.model.SubAssessmentCriteria;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.SubAssessmentCriteriaRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.SubAssessmentCriteriaService;
import com.doyatama.university.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/sub-assessment-criteria")
public class SubAssessmentCriteriaController {
    private final SubAssessmentCriteriaService subAssessmentCriteriaService = new SubAssessmentCriteriaService();

    @GetMapping
    public PagedResponse<SubAssessmentCriteria> getSubAssessmentCriterias
        (@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
        @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
        @RequestParam(value = "assessmentCriteriaId", defaultValue = "*") String assessmentCriteriaId)
        throws IOException {
    return subAssessmentCriteriaService.getAllSubAssessmentCriteria(page, size, assessmentCriteriaId);
    }

    @PostMapping
    public ResponseEntity<?> createSubAssessmentCriteria(@Valid @RequestBody SubAssessmentCriteriaRequest subAssessmentCriteriaRequest) throws IOException {
        SubAssessmentCriteria subAssessmentCriteria = subAssessmentCriteriaService.createSubAssessmentCriteria(subAssessmentCriteriaRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{subAssessmentCriteriaId}")
                .buildAndExpand(subAssessmentCriteria.getSubAssessmentCriteriaId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Sub-assessmentCriteria Created Successfully"));
    }

    @GetMapping("/{subAssessmentCriteriaId}")
    public DefaultResponse<SubAssessmentCriteria> getSubAssessmentCriteriaById(@PathVariable String subAssessmentCriteriaId) throws IOException {
        return subAssessmentCriteriaService.getSubAssessmentCriteriaById(subAssessmentCriteriaId);
    }


    @PutMapping("/{subAssessmentCriteriaId}")
    public ResponseEntity<?> updateSubAssessmentCriteria(@PathVariable String subAssessmentCriteriaId,
        @Valid @RequestBody SubAssessmentCriteriaRequest subAssessmentCriteriaRequest) throws IOException {
            try{
                SubAssessmentCriteria subAssessmentCriteria = subAssessmentCriteriaService.updateSubAssessmentCriteria(subAssessmentCriteriaId, subAssessmentCriteriaRequest);

                URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{subAssessmentCriteriaId}")
                .buildAndExpand(subAssessmentCriteria.getSubAssessmentCriteriaId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Sub-AssessmentCriteria Updated Successfully"));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, e.getMessage()));
            }
    }

    @DeleteMapping("/{subAssessmentCriteriaId}")
public ResponseEntity<?> deleteSubAssessmentCriteria(@PathVariable String subAssessmentCriteriaId) throws IOException {
    subAssessmentCriteriaService.deleteSubAssessmentCriteriaById(subAssessmentCriteriaId);
    return ResponseEntity.noContent().build();  // ✅ Kode 204 No Content
}

}
