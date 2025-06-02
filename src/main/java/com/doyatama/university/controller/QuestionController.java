package com.doyatama.university.controller;

import com.doyatama.university.config.PathConfig;
import com.doyatama.university.model.Exam;
import com.doyatama.university.model.Question;
import com.doyatama.university.model.ExamType;
import com.doyatama.university.payload.*;
import com.doyatama.university.service.QuestionService;
import com.doyatama.university.util.AppConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.core.io.ByteArrayResource;
import java.util.Base64;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

// @CrossOrigin(origins = "http://localhost:3000//rps#/rps/8ed53076-d779-4f03-8c2b-9558d0d33e18/f7fa2143-6806-4556-b9a4-afec20fce867")
// @RestController
// @RequestMapping("/api/question")
// public class QuestionController {

//     private QuestionService questionService = new QuestionService();
    
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private QuestionService questionService = new QuestionService();
    
    @CrossOrigin
    @GetMapping
    public PagedResponse<Question> getQuestion(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
                                                // @RequestParam(value = "rpsDetailID", defaultValue = "*") String rpsDetailID,
                                                // @RequestParam(value = "rpsID", defaultValue = "*") String rpsID) throws IOException {
        return questionService.getAllQuestion(page, size);
    }
    
    // NEW: Separate endpoint for JSON requests (without file upload)
    @CrossOrigin
    @PostMapping("/json")
    public ResponseEntity<?> createQuestionJson(@RequestBody QuestionRequest questionRequest) {
        
        System.out.println("=== DEBUG CONTROLLER ===");
        System.out.println("Content-Type: application/json");
        System.out.println("QuestionRequest received: " + questionRequest.toString());
        
        // Validate required fields
        if (questionRequest.getRps_detail_id() == null || questionRequest.getRps_detail_id().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "rps_detail_id is required"));
        }
        
        try {
            Question question = questionService.createQuestion(questionRequest, "");

            if (question == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Please check relational ID"));
            } else {
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{idQuestion}")
                        .buildAndExpand(question.getIdQuestion()).toUri();

                return ResponseEntity.created(location)
                        .body(new ApiResponse(true, "Question Created Successfully"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Error creating question: " + e.getMessage()));
        }
    }
    
    // EXISTING: Keep the original endpoint for multipart/form-data requests (with file upload)
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createQuestion(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @ModelAttribute QuestionRequest questionRequest) throws IOException {

        System.out.println("=== DEBUG CONTROLLER ===");
        System.out.println("Content-Type: multipart/form-data");
        System.out.println("File: " + (file != null ? file.getOriginalFilename() : "null"));
        System.out.println("QuestionRequest received: " + questionRequest.toString());

        // Validate required fields
        if (questionRequest.getRps_detail_id() == null || questionRequest.getRps_detail_id().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "rps_detail_id is required"));
        }

        if (file != null && !file.isEmpty()) {
            // File upload logic (your existing code)
            try {
                String originalFileName = file.getOriginalFilename();

                if (originalFileName == null) {
                    return ResponseEntity.badRequest()
                            .body(new ApiResponse(false, "File name is invalid"));
                }

                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String timestamp = String.valueOf(System.currentTimeMillis());
                String uuid = UUID.randomUUID().toString();
                String newFileName = "file_" + timestamp + "_" + uuid;
                String filePath = PathConfig.storagePath + "/" + newFileName + fileExtension;
                File newFile = new File(filePath);

                file.transferTo(newFile);

                String localPath = newFile.getAbsolutePath();
                String uri = "hdfs://hadoop-primary:9000";
                String hdfsDir = "hdfs://hadoop-primary:9000/question/" + newFileName + fileExtension;
                Configuration configuration = new Configuration();
                FileSystem fs = FileSystem.get(URI.create(uri), configuration);
                fs.copyFromLocalFile(new Path(localPath), new Path(hdfsDir));
                String savePath = "file/" + newFileName + fileExtension;

                newFile.delete();
                Question question = questionService.createQuestion(questionRequest, savePath);

                if (question == null) {
                    return ResponseEntity.badRequest()
                            .body(new ApiResponse(false, "Please check relational ID"));
                } else {
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{idQuestion}")
                            .buildAndExpand(question.getIdQuestion()).toUri();

                    return ResponseEntity.created(location)
                            .body(new ApiResponse(true, "Question Created Successfully"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
            }
        } else {
            // No file upload
            try {
                Question question = questionService.createQuestion(questionRequest, "");

                if (question == null) {
                    return ResponseEntity.badRequest()
                            .body(new ApiResponse(false, "Please check relational ID"));
                } else {
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{idQuestion}")
                            .buildAndExpand(question.getIdQuestion()).toUri();

                    return ResponseEntity.created(location)
                            .body(new ApiResponse(true, "Question Created Successfully"));
                }
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, e.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Cannot Upload File into Hadoop"));
            }
        }
    }

    @CrossOrigin
    @GetMapping("/image/{imageName}")
    public ResponseEntity<String> getImage(@PathVariable String imageName) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fs = FileSystem.get(conf);
        Path hdfspath = new Path("/user/hadoop/" + imageName);
        FSDataInputStream inputStream = fs.open(hdfspath);
        byte[] imageBytes = IOUtils.toByteArray(inputStream);
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return ResponseEntity.ok(base64Image);
    }

    // @GetMapping("/{questionId}")
    // public DefaultResponse<Question> getQuestionById(@PathVariable String questionId) throws IOException {
    //     return questionService.getQuestionById(questionId);
    // }

    @GetMapping("/{questionId}")
    public DefaultResponse<Question> getQuestionByIdPaged(@PathVariable String questionId) throws IOException {
        return questionService.getQuestionByIdPaged(questionId);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable (value = "questionId") String questionId,
                                            @Valid @RequestBody QuestionRequest questionRequest) {
        try {
            Question question = questionService.updateQuestion(questionId, questionRequest);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{idQuestion}")
                    .buildAndExpand(question.getIdQuestion()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Question Updated Successfully"));
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Error Updating Question"));
        }
    }

    @DeleteMapping("/{idQuestion}")
    public HttpStatus deleteQuestion(@PathVariable (value = "idQuestion") String questionId) throws IOException {
        questionService.deleteQuestionById(questionId);
        return HttpStatus.FORBIDDEN;
    }
}