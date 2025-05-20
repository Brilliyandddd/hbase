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

import org.apache.poi.hpsf.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    System.out.println("id" + rpsRequest.getIdRps());
    if (rpsRequest.getIdRps() == null) {
        rpsRequest.setIdRps(UUID.randomUUID().toString());
    }

    // Initialize lists
    // Map<String, LearningMedia> typeLearningMedia = new HashMap<>();
    // List<LearningMedia> mediaList = new ArrayList<>(typeLearningMedia.values());
    Map<String, Lecture> roleLecturers = new HashMap<>();
    List<Lecture> lectureList = new ArrayList<>(roleLecturers.values());

    // Handle Learning Media
    // if (rpsRequest.getIdLearningMediaSoftware() != null) {
    //     LearningMedia software = learningMediaRepository.findById(rpsRequest.getIdLearningMediaSoftware());
    //     if (software != null) mediaList.add(software);
    // }

    // if (rpsRequest.getIdLearningMediaSoftware() != null){
    //     System.out.println("Hardware " + rpsRequest.getIdLearningMediaSoftware());
    //     LearningMedia software = learningMediaRepository.findById(rpsRequest.getIdLearningMediaSoftware());
    //     if (software != null){
    //         typeLearningMedia.put("software", software);
    //     }
    // }
    
    // if (rpsRequest.getIdLearningMediaHardware() != null){
    //     System.out.println("Hardware " + rpsRequest.getIdLearningMediaHardware());
    //     LearningMedia hardware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaHardware());
    //     if (hardware != null){
    //         typeLearningMedia.put("hardware", hardware);
    //     }
    // }



    // Handle Developer lecturer
    // if (rpsRequest.getDeveloper_lecturer_id() != null) {
    //     System.out.println("Developer " + rpsRequest.getDeveloper_lecturer_id());
    //     Lecture developer = lectureRepository.findById(rpsRequest.getDeveloper_lecturer_id());
    //     if (developer != null) {
    //         roleLecturers.put("developer", developer);
    //     }
    // }
    
    // // Handle Coordinator lecturer
    // if (rpsRequest.getCoordinator_lecturer_id() != null) {
    //     System.out.println("Coordinator " + rpsRequest.getCoordinator_lecturer_id());
    //     Lecture coordinator = lectureRepository.findById(rpsRequest.getCoordinator_lecturer_id());
    //     if (coordinator != null) {
    //         roleLecturers.put("coordinator", coordinator);
    //     }
    // }
    
    // // Handle Instructor lecturer
    // if (rpsRequest.getInstructor_lecturer_id() != null) {
    //     System.out.println("Instructor " + rpsRequest.getInstructor_lecturer_id());
    //     Lecture instructor = lectureRepository.findById(rpsRequest.getInstructor_lecturer_id());
    //     if (instructor != null) {
    //         roleLecturers.put("instructor", instructor);
    //     }
    // }

    StudyProgram studyProgram = studyProgramRepository.findById(rpsRequest.getIdProgramStudi());
    Subject subject = subjectRepository.findById(rpsRequest.getIdSubject());
    LearningMedia learningMediaSoftware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaSoftware());
    LearningMedia learningMediaHardware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaHardware());
    Lecture developerLecturer = lectureRepository.findById(rpsRequest.getDeveloper_lecturer_id());
    Lecture coordinatorLecturer = lectureRepository.findById(rpsRequest.getCoordinator_lecturer_id());
    Lecture instructorLecturer = lectureRepository.findById(rpsRequest.getInstructor_lecturer_id());

    // Set basic fields
    System.out.println("nameRps " + rpsRequest.getNameRps());
    System.out.println("learning media software " + learningMediaSoftware);
    System.out.println("learning media hardware " + learningMediaHardware);

    rps.setIdRps(rpsRequest.getIdRps());
    rps.setNameRps(rpsRequest.getNameRps());
    rps.setSks(rpsRequest.getSks());
    rps.setSemester(rpsRequest.getSemester());
    rps.setCplProdi(rpsRequest.getCplProdi());
    rps.setCplMk(rpsRequest.getCplMk());
    rps.setStudyProgram(studyProgram);
    rps.setSubject(subject);
    rps.setLearningMediaSoftware(learningMediaSoftware);
    rps.setLearningMediaHardware(learningMediaHardware);
    rps.setDeveloperLecturer(developerLecturer);
    rps.setCoordinatorLecturer(coordinatorLecturer);
    rps.setInstructorLecturer(instructorLecturer);
    
    ObjectMapper mapper = new ObjectMapper();
try {
    String jsonLectures = mapper.writeValueAsString(lectureList);
    System.out.println(jsonLectures);
} catch (Exception e) {
    e.printStackTrace();
}
    rps.setCreatedAt(Instant.now());

    return rpsRepository.save(rps);
}

    public DefaultResponse<RPS> getRPSById(String idRps) throws IOException {
        // Retrieve Mapel
        RPS rps = rpsRepository.findById(idRps);
        return new DefaultResponse<>(rps.isValid() ? rps : null, rps.isValid() ? 1 : 0, "Successfully get data");
    }

    public RPS updateRPS(String idRps, RPSRequest rpsRequest) throws IOException {
        StudyProgram studyProgram = studyProgramRepository.findById(rpsRequest.getIdProgramStudi());
        Subject subject = subjectRepository.findById(rpsRequest.getIdSubject());
        LearningMedia learningMediaSoftware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaSoftware());
        LearningMedia learningMediaHardware = learningMediaRepository.findById(rpsRequest.getIdLearningMediaHardware());
        Lecture developerLecturer = lectureRepository.findById(rpsRequest.getDeveloper_lecturer_id());
        Lecture coordinatorLecturer = lectureRepository.findById(rpsRequest.getCoordinator_lecturer_id());
        Lecture instructorLecturer = lectureRepository.findById(rpsRequest.getInstructor_lecturer_id());
        RPS rps = new RPS();
        rps.setNameRps(rpsRequest.getNameRps());
        rps.setSks(rpsRequest.getSks());
        rps.setSemester(rpsRequest.getSemester());
        rps.setCplProdi(rpsRequest.getCplProdi());
        rps.setCplMk(rpsRequest.getCplMk());
        rps.setStudyProgram(studyProgram);
        rps.setSubject(subject);
        rps.setLearningMediaSoftware(learningMediaSoftware);
        rps.setLearningMediaHardware(learningMediaHardware);
        rps.setDeveloperLecturer(developerLecturer);
        rps.setCoordinatorLecturer(coordinatorLecturer);
        rps.setInstructorLecturer(instructorLecturer);

        return rpsRepository.update(idRps, rps);
    }

    public void deleteRPSById(String idRps) throws IOException {
        RPS rps = rpsRepository.findById(idRps);
        if (rps != null && rps.isValid()) {
            rpsRepository.deleteById(idRps);
        } else {
            throw new ResourceNotFoundException("RPS", "id", idRps);
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
