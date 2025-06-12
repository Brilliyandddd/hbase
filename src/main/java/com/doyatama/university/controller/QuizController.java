package com.doyatama.university.controller;

import com.doyatama.university.model.Question;
import com.doyatama.university.model.Quiz;
import com.doyatama.university.payload.ApiResponse; // Keep if you use this for error handling
import com.doyatama.university.payload.DefaultResponse; // Keep if still used elsewhere
import com.doyatama.university.payload.QuizRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.QuizService;
import com.doyatama.university.util.AppConstants;
import com.doyatama.university.exception.ResourceNotFoundException; // Import ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public PagedResponse<Quiz> getQuiz(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return quizService.getAllQuiz(page, size);
    }

    @GetMapping("/questionsByRPSQuiz1")
    public PagedResponse<Question> getAllQuestionsByRPSQuiz1(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam("rpsID") String rpsID) throws IOException {
        return quizService.getAllQuestionsByRPSQuiz1(page, size, rpsID);
    }

    @GetMapping("/questionsByRPSQuiz2")
    public PagedResponse<Question> getAllQuestionsByRPSQuiz2(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam("rpsID") String rpsID) throws IOException {
        return quizService.getAllQuestionsByRPSQuiz2(page, size, rpsID);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createQuiz(@Valid @RequestBody QuizRequest quizRequest) throws IOException {
        try {
            Quiz quiz = quizService.createQuiz(quizRequest);

            if (quiz == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Please check relational ID");
                // return ResponseEntity.badRequest().body(errorResponse);
            }

            // Manual response builder to avoid Instant serialization issues
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Quiz Created Successfully");
            response.put("statusCode", 201);
            
            // Quiz data without problematic Instant fields
            Map<String, Object> quizData = new HashMap<>();
            quizData.put("idQuiz", quiz.getIdQuiz());
            quizData.put("name", quiz.getName());
            quizData.put("description", quiz.getDescription());
            quizData.put("min_grade", quiz.getMin_grade());
            quizData.put("duration", quiz.getDuration());
            quizData.put("developerId", quiz.getDeveloperId());
            quizData.put("type_quiz", quiz.getType_quiz());
            quizData.put("message", quiz.getMessage());
            quizData.put("valid", quiz.isValid());
            
            // Convert Instant to String to avoid serialization issues
            if (quiz.getDate_start() != null) {
                quizData.put("date_start", quiz.getDate_start().toString());
            }
            if (quiz.getDate_end() != null) {
                quizData.put("date_end", quiz.getDate_end().toString());
            }
            if (quiz.getCreated_at() != null) {
                quizData.put("created_at", quiz.getCreated_at().toString());
            }
            
            // Handle questions list
            quizData.put("questions", quiz.getQuestions());
            
            // Handle RPS object
            if (quiz.getRps() != null) {
                Map<String, Object> rpsData = new HashMap<>();
                rpsData.put("idRps", quiz.getRps().getIdRps());
                rpsData.put("nameRps", quiz.getRps().getNameRps());
                rpsData.put("sks", quiz.getRps().getSks());
                rpsData.put("semester", quiz.getRps().getSemester());
                rpsData.put("cplProdi", quiz.getRps().getCplProdi());
                rpsData.put("cplMk", quiz.getRps().getCplMk());
                quizData.put("rps", rpsData);
            }
            
            // Handle todos
            quizData.put("todos", quiz.getTodos());
            
            response.put("data", quizData);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            } catch (Exception e) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable String quizId) throws IOException { // Changed return type
        Quiz quiz = quizService.getQuizById(quizId); // Corrected method call
        if (quiz == null) {
            // If getQuizById returns null, it means the quiz was not found
            // You might want to throw ResourceNotFoundException here if the service doesn't
            // Or handle it gracefully like returning 404
            throw new ResourceNotFoundException("Quiz", "idQuiz", quizId);
        }
        return ResponseEntity.ok(quiz);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<Map<String, Object>> updateQuiz(@PathVariable String quizId,
                                            @Valid @RequestBody QuizRequest quizRequest) throws IOException {
        try {
            Quiz quiz = quizService.updateQuiz(quizId, quizRequest);

            // Manual response builder to avoid Instant serialization issues
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Quiz Updated Successfully");
            response.put("statusCode", 200);
            
            // Quiz data without problematic Instant fields
            Map<String, Object> quizData = new HashMap<>();
            quizData.put("idQuiz", quiz.getIdQuiz());
            quizData.put("name", quiz.getName());
            quizData.put("description", quiz.getDescription());
            quizData.put("min_grade", quiz.getMin_grade());
            quizData.put("duration", quiz.getDuration());
            quizData.put("developerId", quiz.getDeveloperId());
            quizData.put("type_quiz", quiz.getType_quiz());
            quizData.put("message", quiz.getMessage());
            quizData.put("valid", quiz.isValid());
            
            // Convert Instant to String to avoid serialization issues
            if (quiz.getDate_start() != null) {
                quizData.put("date_start", quiz.getDate_start().toString());
            }
            if (quiz.getDate_end() != null) {
                quizData.put("date_end", quiz.getDate_end().toString());
            }
            if (quiz.getCreated_at() != null) {
                quizData.put("created_at", quiz.getCreated_at().toString());
            }
            
            // Handle questions list
            quizData.put("questions", quiz.getQuestions());
            
            // Handle RPS object
            if (quiz.getRps() != null) {
                Map<String, Object> rpsData = new HashMap<>();
                rpsData.put("idRps", quiz.getRps().getIdRps());
                rpsData.put("nameRps", quiz.getRps().getNameRps());
                rpsData.put("sks", quiz.getRps().getSks());
                rpsData.put("semester", quiz.getRps().getSemester());
                rpsData.put("cplProdi", quiz.getRps().getCplProdi());
                rpsData.put("cplMk", quiz.getRps().getCplMk());
                quizData.put("rps", rpsData);
            }
            
            // Handle todos
            quizData.put("todos", quiz.getTodos());
            
            response.put("data", quizData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to update quiz: " + e.getMessage());
            errorResponse.put("statusCode", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable (value = "quizId") String quizId) throws IOException {
        try {
            quizService.deleteQuiz(quizId);
            return new ResponseEntity<>(new ApiResponse(true, "Quiz deleted successfully"), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, "Error deleting quiz: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}