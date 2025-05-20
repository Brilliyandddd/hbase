package com.doyatama.university.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RPS {
    private String idRps;
    private String nameRps;
    private Integer sks;
    private Integer semester;
    private String cplProdi;
    private String cplMk;
    private Instant created_at;

    private LearningMedia learningMediaSoftware;
    private LearningMedia learningMediaHardware;
    private Map<String , LearningMedia> typeLearningMedia;
    private Lecture developerLecturer;
    private Lecture coordinatorLecturer;
    private Lecture instructorLecturer;
    private Map<String, Lecture> roleLecturers;


    // private List<String> mediaPembelajaran = new ArrayList<>();
    private StudyProgram studyProgram;
    private Subject subject;
    // private List<Lecture> lecturers = new ArrayList<>();
    // private List<RPSDetail> rpsDetails = new ArrayList<>();

    public RPS() {}

    public RPS(String idRps, String nameRps, Integer sks, Integer semester, String cplProdi, String cplMk, LearningMedia learningMediaSoftware,LearningMedia learningMediaHardware, Lecture developerLecturer, Lecture coordinatorLecturer, Lecture instructorLecturer,StudyProgram studyProgram,Subject subject, Instant created_at) {
        this.idRps = idRps;
        this.nameRps = nameRps;
        this.sks = sks;
        this.semester = semester;
        this.cplProdi = cplProdi;
        this.cplMk = cplMk;
        this.learningMediaSoftware = learningMediaSoftware;
        this.learningMediaHardware = learningMediaHardware;
        this.developerLecturer = developerLecturer;
        this.coordinatorLecturer = coordinatorLecturer;
        this.instructorLecturer = instructorLecturer;
        this.studyProgram = studyProgram;
        this.subject = subject;
        this.created_at = created_at;
    }

    // Standard Getters & Setters

    public String getIdRps() { return idRps; }
    public void setIdRps(String idRps) { this.idRps = idRps; }

    public String getNameRps() { return nameRps; }
    public void setNameRps(String nameRps) { this.nameRps = nameRps; }

    public Integer getSks() { return sks; }
    public void setSks(Integer sks) { this.sks = sks; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getCplProdi() { return cplProdi; }
    public void setCplProdi(String cplProdi) { this.cplProdi = cplProdi; }

    public String getCplMk() { return cplMk; }
    public void setCplMk(String cplMk) { this.cplMk = cplMk; }

    public Instant getCreateAt() { return created_at; }
    public void setCreatedAt(Instant created_at) { this.created_at = created_at; }

    public LearningMedia getLearningMediaSoftware() { return learningMediaSoftware; }
    public void setLearningMediaSoftware(LearningMedia learningMediaSoftware) { this.learningMediaSoftware = learningMediaSoftware; }

    public LearningMedia getLearningMediaHardware() { return learningMediaHardware; }
    public void setLearningMediaHardware(LearningMedia learningMediaHardware) { this.learningMediaHardware = learningMediaHardware; }

    public Map<String, LearningMedia> getTypeLearningMedia(){
        return typeLearningMedia;
    }

    public void setTypeLearningMedia(Map<String, LearningMedia> typeLearninngMedia){
        this.typeLearningMedia = typeLearninngMedia;
    }

    public Lecture getDeveloperLecturer() { return developerLecturer; }
    public void setDeveloperLecturer(Lecture developerLecturer) { this.developerLecturer = developerLecturer; }

    public Lecture getCoordinatorLecturer() { return coordinatorLecturer; }
    public void setCoordinatorLecturer(Lecture coordinatorLecturer) { this.coordinatorLecturer = coordinatorLecturer; }

    public Lecture getInstructorLecturer() { return instructorLecturer; }
    public void setInstructorLecturer(Lecture instructorLecturer) { this.instructorLecturer = instructorLecturer; }
    
    // public List<Lecture> getLecture() {return lecture; }
    // public void setLecture (List<Lecture> lecture) {this.lecture = lecture; }

    public Map<String, Lecture> getRoleLecturers() {
        return roleLecturers;
    }
    
    public void setRoleLecturers(Map<String, Lecture> roleLecturers) {
        this.roleLecturers = roleLecturers;
    }
    // public List<String> getMandatorys() {
    //     return mandatory;
    // }
    // public void setMandatorys(List<String> mandatory) {
    //     this.mandatory = mandatory;
    // }

    // public String getKaStudyProgram() {
    //     return idProgramStudi;
    // }
    // public void setKaStudyProgram(String idProgramStudi) {
    //     this.idProgramStudi = idProgramStudi;
    // }

    // public List<String> getMediaPembelajaran() { return mediaPembelajaran; }
    // public void setMediaPembelajaran(List<String> mediaPembelajaran) {
    //     this.mediaPembelajaran = mediaPembelajaran != null ? mediaPembelajaran : new ArrayList<>();
    // }

    public StudyProgram getStudyProgram() { return studyProgram; }
    public void setStudyProgram(StudyProgram studyProgram) { this.studyProgram = studyProgram; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    // public List<Lecture> getLecturers() { return lecturers; }
    // public void setLecturers(List<Lecture> lecturers) {
    //     this.lecturers = lecturers != null ? lecturers : new ArrayList<>();
    // }

    // public List<RPSDetail> getRpsDetails() { return rpsDetails; }
    // public void setRpsDetails(List<RPSDetail> rpsDetails) {
    //     this.rpsDetails = rpsDetails != null ? rpsDetails : new ArrayList<>();
    // }

    // Validasi minimum
    public boolean isValid() {
        return idRps != null && nameRps != null && sks != null && semester != null && cplProdi != null && cplMk != null;
    }

    // Setter dinamis untuk import Excel
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idRps": this.idRps = value; break;
            case "nameRps": this.nameRps = value; break;
            case "sks": this.sks = Integer.parseInt(value); break;
            case "semester": this.semester = Integer.parseInt(value); break;
            case "cplProdi": this.cplProdi = value; break;
            case "cplMk": this.cplMk = value; break;
            default: throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
