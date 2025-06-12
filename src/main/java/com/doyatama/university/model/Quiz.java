package com.doyatama.university.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays; // Tambahkan import ini
import java.util.List;
import java.util.stream.Collectors; // Tambahkan import ini

public class Quiz {
    private String idQuiz;
    private String name;
    private String description;
    // --- PERUBAHAN UTAMA DI SINI ---
    // questions akan tetap menjadi List<Question> untuk representasi di aplikasi
    private List<Question> questions;
    // questionsRaw akan menjadi field yang sebenarnya disimpan di HBase (String dari IDs)
    private String questionsRaw; 
    // --- AKHIR PERUBAHAN UTAMA ---
    private RPS rps;
    private Integer min_grade;
    private Integer duration;
    private String date_start;
    private String date_end;
    private String created_at;
    private String developerId;
    private String coordinatorId;
    private String instructorId;
    private List<TodoQuestion> todos;
    private String message;
    private String type_quiz;

    public Quiz() {
    }

    public Quiz(String idQuiz, String name, String description, List<Question> questions, RPS rps, String developerId, String coordinatorId, String instructorId, Integer min_grade,String message,String type_quiz ,Integer duration, String date_start, String date_end, String created_at) {
        this.idQuiz = idQuiz;
        this.name = name;
        this.description = description;
        this.questions = questions; // Ini akan di-set oleh Service
        // NEW: Inisialisasi questionsRaw dari List<Question> jika constructor ini dipanggil
        if (questions != null) {
            this.questionsRaw = questions.stream().map(Question::getIdQuestion).collect(Collectors.joining(";"));
        } else {
            this.questionsRaw = "";
        }
        this.rps = rps;
        this.min_grade = min_grade;
        this.message = message;
        this.developerId = developerId;
        this.coordinatorId = coordinatorId;
        this.instructorId = instructorId;
        this.duration = duration;
        this.type_quiz = type_quiz;
        this.date_start = date_start;
        this.date_end = date_end;
        this.created_at = created_at;
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
    }
    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }
    public String getCoordinatorId() {
        return coordinatorId;
    }
    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }
    public String getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // --- GETTER UNTUK FRONTEND (Ini akan mengembalikan List<Question> objek jika di-set secara eksplisit,
    // atau hasil dari parsing questionsRaw, TAPI ini tidak digunakan oleh frontend yang memanggil getQuestionsByIds)
    public List<Question> getQuestions() {
        // Jika questions (List<Question> objects) sudah diisi (misalnya dari Service setelah findById), kembalikan itu.
        // Jika tidak, dan questionsRaw tersedia (dibaca dari DB), parse questionsRaw menjadi List<String> IDs.
        // Penting: Frontend akan memanggil API terpisah untuk mendapatkan objek Question penuh berdasarkan IDs ini.
        if (this.questions != null) {
            return this.questions;
        } else if (this.questionsRaw != null && !this.questionsRaw.isEmpty()) {
            // Ini akan mengembalikan List<String> IDs, bukan List<Question> objects penuh.
            // Jika frontend mengharapkan List<Question> objects, ini akan menyebabkan ClassCastException di frontend.
            // Karena itu, frontend harus memanggil API terpisah untuk mendapatkan objek penuh.
            // Untuk mencegah ClassCastException di Java jika kode lain mencoba menggunakan ini
            // sebagai List<Question> objects, kita bisa return Collections.emptyList()
            // dan biarkan frontend yang memuatnya.
            return new ArrayList<>(); // Kembalikan list kosong, biarkan frontend yang memanggil API getQuestionsByIds
        }
        return new ArrayList<>();
    }

    // --- SETTER DARI SERVICE (Ketika Service mengambil List<Question> objek penuh) ---
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        // Juga update questionsRaw ketika List<Question> di-set
        if (questions != null) {
            this.questionsRaw = questions.stream()
                                .map(Question::getIdQuestion) // Ambil hanya ID pertanyaan
                                .collect(Collectors.joining(";")); // Gabungkan dengan semicolon
        } else {
            this.questionsRaw = "";
        }
    }

    // --- NEW: Getter dan Setter untuk String mentah (yang disimpan di HBase) ---
    public String getQuestionsRaw() {
        return questionsRaw;
    }

    public void setQuestionsRaw(String questionsRaw) {
        this.questionsRaw = questionsRaw;
        // Saat membaca questionsRaw dari DB, field 'questions' (List<Question> objects)
        // tidak perlu diisi di sini karena frontend akan memuatnya secara terpisah.
        this.questions = new ArrayList<>(); // Pastikan List<Question> direset
    }
    // --- AKHIR NEW ---

    public RPS getRps() {
        return rps;
    }

    public void setRps(RPS rps) {
        this.rps = rps;
    }

    public Integer getMin_grade() {
        return min_grade;
    }

    public void setMin_grade(Integer min_grade) {
        this.min_grade = min_grade;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMessage() {
        if (this.getRps() == null || this.getName() == null || this.getDate_start() == null) {
            return "Quiz information incomplete";
        }
        return String.format(
            "Berdasarkan %s Anda diwajibkan menilai soal yang terdapat pada %s dimulai pada tanggal %s",
            this.getRps().getNameRps(),
            this.getName(),
            this.getDate_start()
        );
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TodoQuestion> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoQuestion> todos) {
        this.todos = todos;
    }

    public String getType_quiz() {
        return type_quiz;
    }

    public void setType_quiz(String type_quiz) {
        this.type_quiz = type_quiz;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isValid() {
        return this.idQuiz != null &&
                this.name != null &&
                this.description != null &&
                this.min_grade != null &&
                this.duration != null &&
                this.date_start != null &&
                this.date_end != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "idQuiz":
                this.idQuiz = value;
                break;
            case "name":
                this.name = value;
                break;
            case "description":
                this.description = value;
                break;
            case "min_grade":
                this.min_grade = Integer.parseInt(value);
                break;
            case "duration":
                this.duration = Integer.parseInt(value);
                break;
            case "message":
                this.message = value;
                break;
            case "type_quiz":
                this.type_quiz = value;
                break;
            case "date_start":
                this.date_start = value;
                break;
            case "date_end":
                this.date_end = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}