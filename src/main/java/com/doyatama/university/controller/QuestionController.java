package com.doyatama.university.controller;

import com.doyatama.university.config.PathConfig;
import com.doyatama.university.model.Question;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.payload.QuestionRequest;
import com.doyatama.university.service.QuestionService;
import com.doyatama.university.util.AppConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // Import MediaType
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:5173") // Sesuaikan jika frontend berjalan di port berbeda
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private QuestionService questionService = new QuestionService();

    @CrossOrigin
    @GetMapping
    public PagedResponse<Question> getQuestion(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return questionService.getAllQuestion(page, size);
    }

    @CrossOrigin
    @PostMapping("/json")
    public ResponseEntity<?> createQuestionJson(@RequestBody QuestionRequest questionRequest) {
        System.out.println("=== DEBUG CONTROLLER - JSON Request ===");
        System.out.println("Content-Type: application/json");
        System.out.println("QuestionRequest received: " + questionRequest.toString());

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

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createQuestion(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @ModelAttribute QuestionRequest questionRequest) {
        System.out.println("=== DEBUG CONTROLLER - Multipart Request ===");
        System.out.println("Content-Type: multipart/form-data");
        System.out.println("File: " + (file != null ? file.getOriginalFilename() : "null"));
        System.out.println("QuestionRequest received: " + questionRequest.toString());

        if (questionRequest.getRps_detail_id() == null || questionRequest.getRps_detail_id().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "rps_detail_id is required"));
        }

        if (file != null && !file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                if (originalFileName == null) {
                    return ResponseEntity.badRequest()
                            .body(new ApiResponse(false, "File name is invalid"));
                }

                String fileExtension = "";
                int lastDotIndex = originalFileName.lastIndexOf(".");
                if (lastDotIndex != -1 && lastDotIndex < originalFileName.length() - 1) {
                    fileExtension = originalFileName.substring(lastDotIndex);
                } else {
                    System.err.println("Warning: File has no extension or invalid format. Original: " + originalFileName);
                }
                
                String timestamp = String.valueOf(System.currentTimeMillis());
                String uuid = java.util.UUID.randomUUID().toString();
                String newFileName = "file_" + timestamp + "_" + uuid;
                
                String filePath = PathConfig.storagePath + "/" + newFileName + fileExtension;
                File newFile = new File(filePath);

                System.out.println("Local temporary directory: " + PathConfig.storagePath);
                System.out.println("Attempting to save local file to: " + newFile.getAbsolutePath());

                file.transferTo(newFile);

                System.out.println("Local file saved. Does it exist? " + newFile.exists());
                System.out.println("Local file size: " + newFile.length() + " bytes");

                String localPath = newFile.getAbsolutePath();
                String hdfsUri = "hdfs://hadoop-primary:9000";
                String hdfsDestPath = "/question/" + newFileName + fileExtension;

                Configuration configuration = new Configuration();
                
                try (FileSystem fs = FileSystem.get(URI.create(hdfsUri), configuration)) {
                    System.out.println("Attempting to copy from local: " + localPath + " to HDFS: " + hdfsUri + hdfsDestPath);
                    fs.copyFromLocalFile(new Path(localPath), new Path(hdfsDestPath));
                    System.out.println("File copied to HDFS successfully.");
                }

                String savePath = "file/" + newFileName + fileExtension;

                newFile.delete();
                System.out.println("Local file deleted: " + !newFile.exists());

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
                System.err.println("IOException during file upload or HDFS copy: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Cannot Upload File into Hadoop or local storage failed."));
            } catch (Exception e) {
                System.err.println("An unexpected error occurred during file upload: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(false, "Internal Server Error during file upload: " + e.getMessage()));
            }
        } else {
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
    }

    // ---------- MODIFIKASI PENTING DI SINI ----------
    @CrossOrigin
    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop-primary:9000");

        try (FileSystem fs = FileSystem.get(URI.create("hdfs://hadoop-primary:9000"), conf)) {
            Path hdfspath = new Path("/question/" + imageName);

            System.out.println("Attempting to retrieve image from HDFS: " + hdfspath.toString());
            if (!fs.exists(hdfspath)) {
                System.err.println("File not found in HDFS: " + hdfspath.toString());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            try (FSDataInputStream inputStream = fs.open(hdfspath)) {
                byte[] imageBytes = IOUtils.toByteArray(inputStream);

                // Menentukan Content-Type berdasarkan ekstensi file
                MediaType contentType = MediaType.APPLICATION_OCTET_STREAM; // Default jika tidak dikenal
                String fileExtension = "";
                int dotIndex = imageName.lastIndexOf('.');
                if (dotIndex > 0 && dotIndex < imageName.length() - 1) {
                    fileExtension = imageName.substring(dotIndex + 1).toLowerCase();
                }

                switch (fileExtension) {
                    case "png":
                        contentType = MediaType.IMAGE_PNG;
                        break;
                    case "jpg":
                    case "jpeg":
                        contentType = MediaType.IMAGE_JPEG;
                        break;
                    case "gif":
                        contentType = MediaType.IMAGE_GIF;
                        break;
                    case "webp": // Tambahkan jika mendukung webp
                        contentType = MediaType.parseMediaType("image/webp");
                        break;
                    // Tambahkan case lain jika ada format gambar yang didukung
                }

                System.out.println("Image " + imageName + " retrieved from HDFS successfully. Size: " + imageBytes.length + " bytes, Content-Type: " + contentType);
                
                // Mengembalikan byte array dengan Content-Type yang benar
                return ResponseEntity.ok()
                        .contentType(contentType)
                        .body(imageBytes);
            }
        } catch (Exception e) {
            System.err.println("Error fetching image from HDFS: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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