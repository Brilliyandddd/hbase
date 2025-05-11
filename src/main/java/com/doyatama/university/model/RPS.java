package com.doyatama.university.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RPS {
    private String id;
    private String name;
    private Integer sks;
    private Integer semester;
    private String cplProdi;
    private String cplMk;
    private Instant created_at;

    private List<LearningMedia> learningMedia;
    private List<Lecture> lecture;

    // private List<String> mediaPembelajaran = new ArrayList<>();
    private StudyProgram studyProgram;
    private Subject idSubject;
    // private List<Lecture> lecturers = new ArrayList<>();
    // private List<RPSDetail> rpsDetails = new ArrayList<>();

    public RPS() {}

    public RPS(String id, String name, Integer sks, Integer semester, String cplProdi, String cplMk, List<LearningMedia> learningMedia, List<Lecture> lecture, Instant created_at) {
        this.id = id;
        this.name = name;
        this.sks = sks;
        this.semester = semester;
        this.cplProdi = cplProdi;
        this.cplMk = cplMk;
        this.learningMedia = learningMedia;
        this.lecture = lecture;
        this.created_at = created_at;
    }

    // Standard Getters & Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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

    public List<LearningMedia> getLearningMedia() { return learningMedia; }
    public void setLearningMedia(List<LearningMedia> learningMedia) { this.learningMedia = learningMedia; }
    
    public List<Lecture> getLecture() {return lecture; }
    public void setLecture (List<Lecture> lecture) {this.lecture = lecture; }

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

    public Subject getSubject() { return idSubject; }
    public void setSubject(Subject idSubject) { this.idSubject = idSubject; }

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
        return id != null && name != null && sks != null && semester != null && cplProdi != null && cplMk != null;
    }

    // Setter dinamis untuk import Excel
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id": this.id = value; break;
            case "name": this.name = value; break;
            case "sks": this.sks = Integer.parseInt(value); break;
            case "semester": this.semester = Integer.parseInt(value); break;
            case "cplProdi": this.cplProdi = value; break;
            case "cplMk": this.cplMk = value; break;
            default: throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
