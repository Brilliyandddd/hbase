package com.doyatama.university.model;

public class Causality {
    private String idCausality;
    private long dateCreated;
    private String description;
    private Subject subject;
    private int semester;
    private Lecture teamTeaching1;
    private Lecture teamTeaching2;
    private Lecture teamTeaching3;  
    
    public Causality(String idCausality, long dateCreated, String description, Subject subject, int semester, Lecture teamTeaching1, Lecture teamTeaching2, Lecture teamTeaching3) {
        this.idCausality = idCausality;
        this.dateCreated = dateCreated;
        this.description = description;
        this.subject = subject;
        this.semester = semester;
        this.teamTeaching1 = teamTeaching1;
        this.teamTeaching2 = teamTeaching2;
        this.teamTeaching3 = teamTeaching3;
    }

    public String getIdCausality() {
        return idCausality;
    }
    public void setIdCausality(String idCausality) {
        this.idCausality = idCausality;
    }

    public long getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Lecture getTeamTeaching1() {
        return teamTeaching1;
    }
    public void setTeamTeaching1(Lecture teamTeaching1) {
        this.teamTeaching1 = teamTeaching1;
    }

    public Lecture getTeamTeaching2() {
        return teamTeaching2;
    }
    public void setTeamTeaching2(Lecture teamTeaching2) {
        this.teamTeaching2 = teamTeaching2;
    }

    public Lecture getTeamTeaching3() {
        return teamTeaching3;
    }
    public void setTeamTeaching3(Lecture teamTeaching3) {
        this.teamTeaching3 = teamTeaching3;
}

public void set(String fieldName, Object value) {
    switch (fieldName) {
        case "idCausality":
            this.idCausality = String.valueOf(value);
            break;
        case "dateCreated":
            this.dateCreated = Long.parseLong(String.valueOf(value));
            break;
        case "description":
            this.description = String.valueOf(value);
            break;
        case "subject":
            this.subject = (Subject) value;
            break;
        case "semester":
            this.semester = Integer.parseInt(String.valueOf(value));
            break;
        case "teamTeaching1":
            this.teamTeaching1 = (Lecture) value;
            break;
        case "teamTeaching2":
            this.teamTeaching2 = (Lecture) value;
            break;
        case "teamTeaching3":
            this.teamTeaching3 = (Lecture) value;
            break;
        default:
            throw new IllegalArgumentException("Invalid field name: " + fieldName);
    }
}
}
