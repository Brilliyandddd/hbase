package com.doyatama.university.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfa
 */

public class CriteriaValue {

    private String id;

    private LinguisticValue value1;
    private LinguisticValue value2;
    private LinguisticValue value3;
    private LinguisticValue value4;
    private LinguisticValue value5;
    private LinguisticValue value6;
    private LinguisticValue value7;
    private LinguisticValue value8;
    private LinguisticValue value9;
    private LinguisticValue value10; // Pastikan ini juga digunakan

    private Question question;
    private TeamTeaching team_teaching; // Konsisten dengan nama di repository jika diperlukan
    private LinguisticValue linguistic_value; // Jika ini mewakili LinguisticValue umum, atau hapus jika tidak diperlukan
    private Lecture lecture;
    private String user_id;

    private String avgOfAvgValue10;

    public CriteriaValue() {
    }

    // Perbaiki constructor: pastikan linguisticValue diinisialisasi
    public CriteriaValue(String id, LinguisticValue value1, LinguisticValue value2, LinguisticValue value3,
                         LinguisticValue value4, LinguisticValue value5, LinguisticValue value6,
                         LinguisticValue value7, LinguisticValue value8, LinguisticValue value9,
                         LinguisticValue value10, Question question, TeamTeaching team_teaching,
                         LinguisticValue linguisticValue, Lecture lecture, String user_id) {
        this.id = id;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10; // Tambahkan inisialisasi value10
        this.question = question;
        this.team_teaching = team_teaching;
        this.linguistic_value = linguisticValue; // Perbaiki inisialisasi
        this.user_id = user_id;
        this.lecture = lecture;
        // Hitung avgOfAvgValue9 jika value9 tersedia
        if (value10 != null) {
            this.avgOfAvgValue10 = String.valueOf((value10.getValue1() + value10.getValue2() + value10.getValue3() + value10.getValue4()) / 4.0f);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinguisticValue getValue1() {
        return this.value1;
    }

    public LinguisticValue getValue2() {
        return this.value2;
    }

    public LinguisticValue getValue3() {
        return this.value3;
    }

    public LinguisticValue getValue4() {
        return this.value4;
    }

    public LinguisticValue getValue5() {
        return this.value5;
    }

    public LinguisticValue getValue6() {
        return this.value6;
    }

    public LinguisticValue getValue7() {
        return this.value7;
    }

    public LinguisticValue getValue8() {
        return this.value8;
    }

    public LinguisticValue getValue9() {
        return this.value9;
    }

    public LinguisticValue getValue10() {
        return this.value10;
    }

    public void setValue1(LinguisticValue value1) {
        this.value1 = value1;
    }

    public void setValue2(LinguisticValue value2) {
        this.value2 = value2;
    }

    public void setValue3(LinguisticValue value3) {
        this.value3 = value3;
    }

    public void setValue4(LinguisticValue value4) {
        this.value4 = value4;
    }

    public void setValue5(LinguisticValue value5) {
        this.value5 = value5;
    }

    public void setValue6(LinguisticValue value6) {
        this.value6 = value6;
    }

    public void setValue7(LinguisticValue value7) {
        this.value7 = value7;
    }

    public void setValue8(LinguisticValue value8) {
        this.value8 = value8;
    }

    public void setValue9(LinguisticValue value9) {
        this.value9 = value9;
    }

    public void setValue10(LinguisticValue value10) {
        this.value10 = value10;
        // Hitung avgOfAvgValue10 secara otomatis saat value10 disetel
        if (value10 != null) {
            this.avgOfAvgValue10 = String.valueOf((value10.getValue1() + value10.getValue2() + value10.getValue3() + value10.getValue4()) / 4.0f);
        } else {
            this.avgOfAvgValue10 = null; // Set ke null jika value10 tidak ada
        }
    }

    public String getAvgOfAvgValue10() {
        // Getter hanya mengembalikan nilai yang sudah dihitung
        return avgOfAvgValue10;
    }

    // Setter untuk avgOfAvgValue9 jika ada kebutuhan untuk mengaturnya secara eksternal (jarang)
    public void setAvgOfAvgValue10(String avgOfAvgValue10) {
        this.avgOfAvgValue10 = avgOfAvgValue10;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public TeamTeaching getTeamTeaching() {
        return team_teaching;
    }

    public void setTeamTeaching(TeamTeaching team_teaching) {
        this.team_teaching = team_teaching;
    }

    public LinguisticValue getLinguisticValue() {
        return linguistic_value;
    }

    public void setLinguisticValue(LinguisticValue linguistic_value) {
        this.linguistic_value = linguistic_value;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getUser() {
        return user_id;
    }

    public void setUser(String user_id) {
        this.user_id = user_id;
    }

    // Tambahkan value10 dalam validasi jika diperlukan
    public boolean isValid() {
        return this.id != null && this.value1 != null && this.value2 != null && this.value3 != null
                && this.value4 != null && this.value5 != null && this.value6 != null && this.value7 != null
                && this.value8 != null && this.value9 != null && this.value10 != null; // Sesuaikan jika value10 wajib
    }

    // Metode set ini hanya akan berfungsi untuk field string primitif.
    // Untuk objek bersarang seperti LinguisticValue, Question, dll., diperlukan logika yang lebih kompleks
    // untuk melakukan lookup berdasarkan ID.
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "user_id": // Tambahkan user_id
                this.user_id = value;
                break;
            // Anda mungkin perlu menambahkan case untuk field lain jika ini digunakan untuk generic update
            // Misalnya: case "value1": this.value1 = new LinguisticValue(value, "", 0,0,0,0); break; (kalau value adalah ID)
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}