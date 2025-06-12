package com.doyatama.university.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Positive; // Tidak diperlukan lagi untuk dateCreated

public class CausalityRequest {

    // dateCreated dihapus dari sini karena akan diatur secara otomatis di service layer
    // private Long dateCreated;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Subject ID is required") // Asumsi "subject" adalah ID mata kuliah
    private String subject;

    @NotNull(message = "Semester is required") // Tambahkan @NotNull untuk tipe primitif int jika ingin validasi bahwa tidak null
    @Min(value = 1, message = "Semester must be at least 1")
    private Integer semester; // Ubah ke Integer agar bisa null jika tidak diisi

    // Team Teaching IDs - bisa null/kosong jika hanya ada satu atau dua dosen
    private String teamTeaching1;
    private String teamTeaching2;
    private String teamTeaching3;

    // Default constructor
    public CausalityRequest() {
    }

    // Constructor with essential fields for creating a new task
    // dateCreated dihilangkan dari constructor karena diatur di service
    public CausalityRequest(String description, String subject, Integer semester,
                            String teamTeaching1, String teamTeaching2, String teamTeaching3) {
        this.description = description;
        this.subject = subject;
        this.semester = semester;
        this.teamTeaching1 = teamTeaching1;
        this.teamTeaching2 = teamTeaching2;
        this.teamTeaching3 = teamTeaching3;
    }

    // --- Getters and Setters ---

    // Getter dan Setter untuk dateCreated dihapus
    // public Long getDateCreated() { ... }
    // public void setDateCreated(Long dateCreated) { ... }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSemester() { // Ubah tipe kembalian ke Integer
        return semester;
    }

    public void setSemester(Integer semester) { // Ubah tipe parameter ke Integer
        this.semester = semester;
    }

    public String getTeamTeaching1() {
        return teamTeaching1;
    }

    public void setTeamTeaching1(String teamTeaching1) {
        this.teamTeaching1 = teamTeaching1;
    }

    public String getTeamTeaching2() {
        return teamTeaching2;
    }

    public void setTeamTeaching2(String teamTeaching2) {
        this.teamTeaching2 = teamTeaching2;
    }

    public String getTeamTeaching3() {
        return teamTeaching3;
    }

    public void setTeamTeaching3(String teamTeaching3) {
        this.teamTeaching3 = teamTeaching3;
    }

    @Override
    public String toString() {
        return "CausalityRequest{" +
                // "dateCreated=" + dateCreated + // Dihapus dari toString
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", semester=" + semester +
                ", teamTeaching1='" + teamTeaching1 + '\'' +
                ", teamTeaching2='" + teamTeaching2 + '\'' +
                ", teamTeaching3='" + teamTeaching3 + '\'' +
                '}';
    }
}