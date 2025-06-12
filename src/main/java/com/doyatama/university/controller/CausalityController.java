package com.doyatama.university.controller;

import com.doyatama.university.model.Causality;
import com.doyatama.university.model.CausalityRating;
import com.doyatama.university.payload.CausalityRatingRequest;
import com.doyatama.university.payload.CausalityRequest;
import com.doyatama.university.payload.DefaultResponse; // Your DefaultResponse
import com.doyatama.university.payload.PagedResponse;   // Your PagedResponse
import com.doyatama.university.service.CausalityService;
import com.doyatama.university.service.DematelCalculationService;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/causality")
public class CausalityController {

    private static final Logger logger = LoggerFactory.getLogger(CausalityController.class);

    @Autowired
    private CausalityService causalityService;

    @Autowired
    private DematelCalculationService dematelCalculationService;

    // --- Endpoints for Admin ---

    /**
     * GET /api/causality
     * Fetches all causality tasks created by the admin.
     */
    @GetMapping
    public ResponseEntity<PagedResponse<Causality>> getAllCausalities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            // Assuming causalityService.getAllCausality returns a PagedResponse already
            PagedResponse<Causality> causalities = causalityService.getAllCausality(page, size);
            return ResponseEntity.ok(causalities);
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging
            // Constructing PagedResponse based on your provided class
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new PagedResponse<>(
                                         Collections.emptyList(), // content: empty list
                                         0L,                      // totalElements: 0
                                         "Failed to retrieve causalities: " + e.getMessage(), // message
                                         HttpStatus.INTERNAL_SERVER_ERROR.value() // statusCode
                                 ));
        }
    }

    /**
     * GET /api/causality/{idCausality}
     * Fetches details of a causality task by its ID.
     */
    @GetMapping("/{idCausality}")
    public ResponseEntity<DefaultResponse<Causality>> getCausalityById(@PathVariable String idCausality) {
        try {
            DefaultResponse<Causality> response = causalityService.getCausalityById(idCausality);
            // Check success based on statusCode or if content is not null
            if (response.getContent() != null) { // Assuming success if content is present
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Constructing DefaultResponse based on your provided class
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(
                                         null, // content: null
                                         0L,   // totalElements: 0
                                         "Failed to retrieve causality: " + e.getMessage() // message
                                 ));
        }
    }

    /**
     * POST /api/causality
     * Admin creates a new causality assessment task.
     * Body: {
     * "description": "Penilaian Kausalitas Mata Kuliah Algoritma",
     * "subject": "Algoritma",
     * "semester": 5,
     * "teamTeaching1": "dosen_id_A",
     * "teamTeaching2": "dosen_id_B",
     * "teamTeaching3": null
     * }
     */
    @PostMapping
    public ResponseEntity<DefaultResponse<Causality>> createCausality(@Valid @RequestBody CausalityRequest causalityRequest) {
        try {
            // Service layer will handle ID generation, dateCreated, criteriaIds, and status
            Causality created = causalityService.createCausality(causalityRequest);
            // Constructing DefaultResponse based on your provided class
            DefaultResponse<Causality> response = new DefaultResponse<>(
                    created, // content
                    1L,      // totalElements: 1 for a single created item
                    "Causality task created successfully" // message
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to create causality task: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new DefaultResponse<>(null, 0L, "Invalid request: " + e.getMessage()));
        }
    }

    /**
     * PUT /api/causality/{idCausality}
     * Admin updates an existing causality assessment task.
     * Body: Same as POST, but changes might occur in teamTeaching or description.
     */
    @PutMapping("/{idCausality}")
    public ResponseEntity<DefaultResponse<Causality>> updateCausality(@PathVariable String idCausality,
                                                                       @Valid @RequestBody CausalityRequest causalityRequest) {
        try {
            // Service layer will handle converting CausalityRequest to Causality model
            Causality updated = causalityService.updateCausality(idCausality, causalityRequest);
            DefaultResponse<Causality> response = new DefaultResponse<>(
                    updated, // content
                    1L,      // totalElements: 1 for a single updated item
                    "Causality task updated successfully" // message
            );
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to update causality task: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new DefaultResponse<>(null, 0L, "Causality task not found or invalid data: " + e.getMessage()));
        }
    }

    /**
     * DELETE /api/causality/{idCausality}
     * Admin deletes a causality assessment task.
     */
    @DeleteMapping("/{idCausality}")
    public ResponseEntity<DefaultResponse<Void>> deleteCausality(@PathVariable String idCausality) {
        try {
            boolean deleted = causalityService.deleteCausality(idCausality);
            if (deleted) {
                // Return 204 No Content for successful deletion without a body
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(new DefaultResponse<>(null, 0L, "Causality task not found"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to delete causality task: " + e.getMessage()));
        }
    }

    /**
     * PUT /api/causality/{idCausality}/status
     * Admin updates the status of a causality task (e.g., from "Pending" to "Completed").
     * Body: { "status": "Completed" }
     */
    @PutMapping("/{idCausality}/status")
    public ResponseEntity<DefaultResponse<Causality>> updateCausalityStatus(@PathVariable String idCausality, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        if (newStatus == null || newStatus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new DefaultResponse<>(null, 0L, "New status cannot be empty"));
        }
        try {
            Causality updatedTask = causalityService.updateCausalityStatus(idCausality, newStatus);
            DefaultResponse<Causality> response = new DefaultResponse<>(
                    updatedTask, // content
                    1L,          // totalElements
                    "Causality task status updated successfully" // message
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new DefaultResponse<>(null, 0L, "Causality task not found or invalid status: " + e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to update causality task status: " + e.getMessage()));
        }
    }


    // --- Endpoints for Dosen/Team Teaching ---

    /**
     * GET /api/causality/tasks/teacher/{teacherId}
     * Dosen retrieves the list of tasks assigned for assessment.
     */
    @GetMapping("/tasks/teacher/{teacherId}")
    public ResponseEntity<DefaultResponse<List<Causality>>> getTasksForTeacher(@PathVariable String teacherId) {
        try {
            List<Causality> tasks = causalityService.getTasksForTeacher(teacherId);
            DefaultResponse<List<Causality>> response = new DefaultResponse<>(
                    tasks,        // content
                    (long)tasks.size(), // totalElements
                    "Tasks for teacher retrieved successfully" // message
            );
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to retrieve tasks for teacher: " + e.getMessage()));
        }
    }

    /**
     * GET /api/causality/tasks/{taskId}/pairs/{teacherId}
     * Dosen retrieves all pairs of criteria that need to be assessed for a specific task.
     */
    @GetMapping("/tasks/{taskId}/pairs/{teacherId}")
    public ResponseEntity<DefaultResponse<List<CausalityService.CausalityRatingPair>>> getCriteriaPairsToRate(@PathVariable String taskId, @PathVariable String teacherId) {
        try {
            List<CausalityService.CausalityRatingPair> pairs = causalityService.getCriteriaPairsToRate(taskId, teacherId);
            DefaultResponse<List<CausalityService.CausalityRatingPair>> response = new DefaultResponse<>(
                    pairs,       // content
                    (long)pairs.size(), // totalElements
                    "Criteria pairs retrieved successfully" // message
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new DefaultResponse<>(null, 0L, "Error retrieving criteria pairs: " + e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to retrieve criteria pairs: " + e.getMessage()));
        }
    }

    /**
     * POST /api/causality/ratings
     * Dosen submits the assessment of the relationship between criteria.
     * Body: {
     * "causalityTaskId": "id_tugas_kausalitas",
     * "reviewerId": "id_dosen",
     * "influencingCriteriaId": "C1",
     * "influencedCriteriaId": "C2",
     * "ratingValue": 3
     * }
     */
    @PostMapping("/ratings")
    public ResponseEntity<DefaultResponse<CausalityRating>> submitCausalityRating(@Valid @RequestBody CausalityRatingRequest request) { // <-- Pastikan ini CausalityRatingRequest
        logger.info("CausalityController - submitCausalityRating: Received request. Payload: {}", request.toString());
        try {
            // Panggil service dengan CausalityRatingRequest
            CausalityRating savedRating = causalityService.submitCausalityRating(request); // <-- Panggil service dengan 'request'
            DefaultResponse<CausalityRating> response = new DefaultResponse<>(
                    savedRating,
                    1L,
                    "Causality rating submitted successfully"
            );
            logger.info("CausalityController - submitCausalityRating: Rating submitted successfully. Saved ID: {}", savedRating.getIdRating());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("CausalityController - submitCausalityRating: Invalid rating submission: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new DefaultResponse<>(null, 0L, "Invalid rating submission: " + e.getMessage()));
        } catch (IOException e) {
            logger.error("CausalityController - submitCausalityRating: Failed to submit causality rating due to IO error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to submit causality rating: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("CausalityController - submitCausalityRating: An unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    /**
     * GET /api/causality/tasks/{taskId}/ratings
     * Retrieves all ratings provided for a specific causality task.
     * Used for DEMATEL calculations.
     */
    @GetMapping("/tasks/{taskId}/ratings")
    public ResponseEntity<DefaultResponse<List<CausalityRating>>> getAllRatingsForTask(@PathVariable String taskId) {
        try {
            List<CausalityRating> ratings = causalityService.getAllRatingsForTask(taskId);
            DefaultResponse<List<CausalityRating>> response = new DefaultResponse<>(
                    ratings,        // content
                    (long)ratings.size(), // totalElements
                    "All ratings for task retrieved successfully" // message
            );
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Failed to retrieve ratings for task: " + e.getMessage()));
        }
    }

    @PostMapping("/tasks/{taskId}/calculate-weights")
    public ResponseEntity<DefaultResponse<Map<String, Double>>> calculateDematelWeights(@PathVariable String taskId) {
        try {
            Map<String, Double> weights = dematelCalculationService.calculateAndApplyWeights(taskId);
            DefaultResponse<Map<String, Double>> response = new DefaultResponse<>(
                weights, (long)weights.size(), "DEMATEL weights calculated and applied successfully."
            );
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            logger.error("Error calculating weights: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new DefaultResponse<>(null, 0L, e.getMessage()));
        } catch (BadRequestException e) {
            logger.error("Error calculating weights: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new DefaultResponse<>(null, 0L, e.getMessage()));
        } catch (IOException e) {
            logger.error("Server error during DEMATEL calculation: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new DefaultResponse<>(null, 0L, "Server error during calculation: " + e.getMessage()));
        }
    }
}