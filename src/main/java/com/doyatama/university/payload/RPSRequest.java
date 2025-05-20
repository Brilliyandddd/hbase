package com.doyatama.university.payload;

import java.util.List;

public class RPSRequest {
    private String idRps;
    private String nameRps;
    private Integer sks;
    private Integer semester;
    private String cplProdi;
    private String cplMk;

    private String idLearningMediaSoftware;
    private String idLearningMediaHardware;
    private String developer_lecturer_id;
    private String coordinator_lecturer_id;
    private String instructor_lecturer_id;

    private String idProgramStudi;
    private String idSubject;

    

    // Constructor default
    public RPSRequest() {}

    public RPSRequest(String idRps,String nameRps, Integer sks, Integer semester, String cplProdi, String cplMk,
    String idLearningMediaSoftware, String idLearningMediaHardware, String developer_lecturer_id, String coordinator_lecturer_id, String instructor_lecturer_id, String idProgramStudi, String idSubject) {
        this.idRps = idRps;
        this.nameRps = nameRps;
        this.sks = sks;
        this.semester = semester;
        this.cplProdi = cplProdi;
        this.cplMk = cplMk;
        this.idLearningMediaSoftware = idLearningMediaSoftware;
        this.idLearningMediaHardware = idLearningMediaHardware;
        this.developer_lecturer_id = developer_lecturer_id;
        this.coordinator_lecturer_id = coordinator_lecturer_id;
        this.instructor_lecturer_id = instructor_lecturer_id;
        this.idProgramStudi = idProgramStudi;
        this.idSubject = idSubject;
    }

    // Getters & Setters
    public String getIdRps() { return idRps; }
    public void setIdRps(String idRps) { this.idRps = idRps;}

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

    public String getIdLearningMediaSoftware() { return idLearningMediaSoftware; }
    public void setIdLearningMediaSoftware(String idLearningMediaSoftware) {
        this.idLearningMediaSoftware = idLearningMediaSoftware;
    }

    public String getIdLearningMediaHardware() { return idLearningMediaHardware; }
    public void setIdLearningMediaHardware(String idLearningMediaHardware) {
        this.idLearningMediaHardware = idLearningMediaHardware;
    }

    public String getDeveloper_lecturer_id() { return developer_lecturer_id; }
    public void setDeveloper_lecturer_id(String developer_lecturer_id) {
        this.developer_lecturer_id = developer_lecturer_id;
    }
    public String getCoordinator_lecturer_id() { return coordinator_lecturer_id; }
    public void setCoordinator_lecturer_id(String coordinator_lecturer_id) {
        this.coordinator_lecturer_id = coordinator_lecturer_id;
    }
    public String getInstructor_lecturer_id() { return instructor_lecturer_id; }
    public void setInstructor_lecturer_id(String instructor_lecturer_id) {
        this.instructor_lecturer_id = instructor_lecturer_id;
    }

    // public List<String> getRequirement_subjects() { return mandatory; }
    // public void setRequirement_subjects(List<String> mandatory) {
    //     this.mandatory = mandatory;
    // }

    public String getIdProgramStudi() { return idProgramStudi; }
    public void setIdProgramStudi(String idProgramStudi) {
        this.idProgramStudi = idProgramStudi;
    }

    public String getIdSubject() { return idSubject; }
    public void setIdSubject(String idSubject) { this.idSubject = idSubject; }

    // public String getKa_study_program() { return ka_study_program; }
    // public void setKa_study_program(String ka_study_program) {
    //     this.ka_study_program = ka_study_program;
    // }

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
