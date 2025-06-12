package com.doyatama.university.controller;

import com.doyatama.university.model.CriteriaValue;
import com.doyatama.university.model.Question;
import com.doyatama.university.model.RPS;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.CriteriaValueRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.security.CurrentUser;
import com.doyatama.university.service.CriteriaValueService;
import com.doyatama.university.service.QuestionService;
import com.doyatama.university.service.RPSService;

import com.doyatama.university.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid; // Pastikan ini diimpor
import java.io.IOException;
import java.net.URI;

/**
 * @author alfa
 */
@RestController
@RequestMapping("/api/criteria-value")
public class CriteriaValueController {

    private final CriteriaValueService criteriaValueService;
    private final RPSService rpsService;
    private final QuestionService questionService;

    // Menggunakan Constructor Injection sudah benar
    @Autowired
    public CriteriaValueController(CriteriaValueService criteriaValueService, RPSService rpsService, QuestionService questionService) {
        this.criteriaValueService = criteriaValueService;
        this.rpsService = rpsService;
        this.questionService = questionService;
    }

    @GetMapping
    public PagedResponse<RPS> getRPSs(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        // Nama metode 'getRPSs' di controller ini sesuai dengan yang dikembalikan oleh rpsService.getAllRPS
        return rpsService.getAllRPS(page, size);
    }

    @GetMapping("/questions")
    public PagedResponse<Question> getAllQuestionsByRPS(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                        @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                        @RequestParam("rpsID") String rpsID) throws IOException {
        return questionService.getAllQuestionsByRPS(page, size, rpsID);
    }

    @GetMapping("/question/{questionId}")
    public PagedResponse<CriteriaValue> getAllCriteriaValueByQuestion(@PathVariable String questionId,
                                                                      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size 
                                                                      ) throws IOException {
        return criteriaValueService.getAllCriteriaValueByQuestion(questionId, page, size);
    }

    // @GetMapping("/quizAnnouncement/{quizAnnouncementId}")
    // public PagedResponse<CriteriaValue> getQuestionsWithCriteriaValuesFromQuizAnnouncement(
    //             @PathVariable String quizAnnouncementId,
    //             @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
    //             @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
    //     return criteriaValueService.getQuestionsWithCriteriaValuesFromQuizAnnouncement(quizAnnouncementId, page, size);
    // }

    @PostMapping("/{questionId}")
    public ResponseEntity<?> createCriteriaValue(@Valid @RequestBody CriteriaValueRequest criteriaValueRequest, @PathVariable String questionId) throws IOException {
        // Service sekarang melempar ResourceNotFoundException atau BadRequestException
        // daripada mengembalikan null. Controller akan menangkapnya melalui @ControllerAdvice
        // atau Spring akan secara otomatis mengembalikan 400/404 jika ada exception
        CriteriaValue criteriaValue = criteriaValueService.createCriteriaValue(criteriaValueRequest, questionId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{criteriaValueId}")
                .buildAndExpand(criteriaValue.getId()).toUri(); 
        // Mengembalikan 201 Created dengan lokasi dari resource yang baru dibuat
        return ResponseEntity.created(location) // Mengembalikan 201 Created
                .body(new ApiResponse(true, "CriteriaValue Created Successfully"));
    }

    @GetMapping("/{criteriaValueId}")
    public DefaultResponse<CriteriaValue> getCriteriaValueById(@PathVariable String criteriaValueId) throws IOException {
        return criteriaValueService.getCriteriaValueById(criteriaValueId);
    }

    @PutMapping("/{criteriaValueId}")
    public ResponseEntity<?> updateCriteriaValue(@PathVariable String criteriaValueId,
                                                @Valid @RequestBody CriteriaValueRequest criteriaValueRequest) throws IOException { // Tambahkan @Valid
        // Service sekarang melempar ResourceNotFoundException atau BadRequestException
        // daripada mengembalikan null atau mengelola status secara internal.
        CriteriaValue updatedCriteriaValue = criteriaValueService.updateCriteriaValue(criteriaValueId, criteriaValueRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("") // Path saat ini tanpa {criteriaValueId} lagi
                .buildAndExpand(updatedCriteriaValue.getId()).toUri();

        return ResponseEntity.ok() // Mengembalikan 200 OK untuk update berhasil
                .body(new ApiResponse(true, "CriteriaValue Updated Successfully"));
    }

    @DeleteMapping("/{criteriaValueId}")
    public ResponseEntity<?> deleteCriteriaValue(@PathVariable (value = "criteriaValueId") String criteriaValueId) throws IOException {
        criteriaValueService.deleteCriteriaValueById(criteriaValueId);
        // Mengembalikan HttpStatus.NO_CONTENT (204) untuk operasi DELETE yang berhasil
        return ResponseEntity.noContent().build();
    }
}