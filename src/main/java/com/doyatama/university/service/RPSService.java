package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.LearningMedia;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.RPS;
import com.doyatama.university.model.StudyProgram;
import com.doyatama.university.model.Subject;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.RPSRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.LearningMediaRepository;
import com.doyatama.university.repository.LectureRepository;
import com.doyatama.university.repository.RPSRepository;
import com.doyatama.university.repository.StudyProgramRepository;
import com.doyatama.university.repository.SubjectRepository;
import com.doyatama.university.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RPSService {
    private final RPSRepository rpsRepository = new RPSRepository();
    private final LearningMediaRepository learningMediaRepository = new LearningMediaRepository();
    private final LectureRepository lectureRepository = new LectureRepository();
    private final SubjectRepository subjectRepository = new SubjectRepository();
    private final StudyProgramRepository studyProgramRepository = new StudyProgramRepository();

    private static final Logger logger = LoggerFactory.getLogger(RPSService.class);

    public PagedResponse<RPS> getAllRPS(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        List<RPS> rpsList = rpsRepository.findAll(size);

        return new PagedResponse<>(rpsList, rpsList.size(), "Successfully retrieved data", 200);
    }

    public List<RPS> importRPSFromExcel(MultipartFile file) throws IOException {
        if (!ExcelUploadService.isValidExcelFile(file)) {
            throw new BadRequestException("Invalid Excel file");
        }

        List<RPS> rpsList = ExcelUploadService.getRPSDataFromExcel(file.getInputStream());
        for (RPS rps : rpsList) {
            rpsRepository.save(rps);
        }
        return rpsList;
    }

    public RPS createRPS(RPSRequest rpsRequest) throws IOException {
    RPS rps = new RPS();

    // Initialize lists
    List<LearningMedia> mediaList = new ArrayList<>();
    List<Lecture> lectureList = new ArrayList<>();

    // Handle Learning Media
    if (rpsRequest.getIdLearningMediaSoftware() != null) {
        LearningMedia software = learningMediaRepository.findById(rpsRequest.getIdLearningMediaSoftware());
        if (software != null) mediaList.add(software);
    }
    
    if (rpsRequest.getIdLearningMediaHardware() != null) {
        LearningMedia hardware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaHardware());
        if (hardware != null) mediaList.add(hardware);
    }

    // Handle Lectures
    if (rpsRequest.getDeveloper_lecturer_id() != null) {
        Lecture developer = lectureRepository.findById(rpsRequest.getDeveloper_lecturer_id());
        if (developer != null) lectureList.add(developer);
    }
    
    if (rpsRequest.getCoordinator_lecturer_id() != null) {
        Lecture coordinator = lectureRepository.findById(rpsRequest.getCoordinator_lecturer_id());
        if (coordinator != null) lectureList.add(coordinator);
    }
    
    if (rpsRequest.getInstructor_lecturer_id() != null) {
        Lecture instructor = lectureRepository.findById(rpsRequest.getInstructor_lecturer_id());
        if (instructor != null) lectureList.add(instructor);
    }

    // Set basic fields
    rps.setName(rpsRequest.getName());
    rps.setSks(rpsRequest.getSks());
    rps.setSemester(rpsRequest.getSemester());
    rps.setCplProdi(rpsRequest.getCplProdi());
    rps.setCplMk(rpsRequest.getCplMk());
    
    // Set relational fields (with null checks)
    if (rpsRequest.getIdProgramStudi() != null) {
        rps.setStudyProgram(studyProgramRepository.findById(rpsRequest.getIdProgramStudi()));
    }
    
    if (rpsRequest.getIdSubject() != null) {
        rps.setSubject(subjectRepository.findById(rpsRequest.getIdSubject()));
    }

    rps.setLearningMedia(mediaList.isEmpty() ? null : mediaList);
    rps.setLecture(lectureList.isEmpty() ? null : lectureList);
    rps.setCreatedAt(Instant.now());

    return rpsRepository.save(rps);
}

    public DefaultResponse<RPS> getRPSById(String rpsId) throws IOException {
        RPS rps = rpsRepository.findById(rpsId);

        return new DefaultResponse<>(
            rps != null && rps.isValid() ? rps : null,
            rps != null && rps.isValid() ? 1 : 0,
            rps != null ? "Successfully retrieved data" : "RPS not found"
        );
    }

    public RPS updateRPS(String rpsId, RPSRequest rpsRequest) throws IOException {
        RPS rps = new RPS();
        rps.setName(rpsRequest.getName());
        rps.setSks(rpsRequest.getSks());
        rps.setSemester(rpsRequest.getSemester());
        rps.setCplProdi(rpsRequest.getCplProdi());
        rps.setCplMk(rpsRequest.getCplMk());

        return rpsRepository.update(rpsId, rps);
    }

    public void deleteRPSById(String rpsId) throws IOException {
        RPS rps = rpsRepository.findById(rpsId);
        if (rps != null && rps.isValid()) {
            rpsRepository.deleteById(rpsId);
        } else {
            throw new ResourceNotFoundException("RPS", "id", rpsId);
        }
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
