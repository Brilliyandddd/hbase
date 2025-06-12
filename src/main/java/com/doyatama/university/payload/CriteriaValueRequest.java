package com.doyatama.university.payload;

import com.doyatama.university.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author alfa
 */
public class CriteriaValueRequest {
    private String id;

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private String value9;
    private String value10; // Tambahkan value10

    // avgOfAvgValue9 dihapus karena akan dihitung di backend
    // private String avgOfAvgValue9;

    private String idQuestion;
    private String team_teaching_id;
    private String linguistic_value_id; // Jika ini diperlukan sebagai LinguisticValue umum
    private String user_id;

    private String lecture;


    public CriteriaValueRequest() {
    }

    // Perbarui constructor untuk menyertakan value10
    public CriteriaValueRequest(String id, String value1, String value2, String value3, String value4,
                                String value5, String value6, String value7, String value8, String value9,
                                String value10, String idQuestion, String team_teaching_id,
                                String linguistic_value_id, String user_id) {
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
        this.value10 = value10; // Inisialisasi value10
        // this.avgOfAvgValue9 = avgOfAvgValue9; // Dihapus
        this.idQuestion = idQuestion;
        this.team_teaching_id = team_teaching_id;
        this.linguistic_value_id = linguistic_value_id;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return this.value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return this.value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return this.value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getValue6() {
        return this.value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    public String getValue7() {
        return this.value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    public String getValue8() {
        return this.value8;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
    }

    public String getValue9() {
        return this.value9;
    }

    public void setValue9(String value9) {
        this.value9 = value9;
    }

    public String getValue10() { // Getter untuk value10
        return this.value10;
    }

    public void setValue10(String value10) { // Setter untuk value10
        this.value10 = value10;
    }

    public String getLinguisticValue() {
        return this.linguistic_value_id;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTeamTeachingId() {
        return team_teaching_id;
    }

    public void setTeamTeachingId(String team_teaching_id) {
        this.team_teaching_id = team_teaching_id;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // Perbaiki switch-case: tambahkan 'break;' dan tangani value10
    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "idQuestion":
                this.idQuestion = value;
                break; // <-- Tambahkan break di sini!
            case "value1":
                this.value1 = value;
                break;
            case "value2":
                this.value2 = value;
                break;
            case "value3":
                this.value3 = value;
                break;
            case "value4":
                this.value4 = value;
                break;
            case "value5":
                this.value5 = value;
                break;
            case "value6":
                this.value6 = value;
                break;
            case "value7":
                this.value7 = value;
                break;
            case "value8":
                this.value8 = value;
                break;
            case "value9":
                this.value9 = value;
                break;
            case "value10": // Tambahkan case untuk value10
                this.value10 = value;
                break;
            // case "avgOfAvgValue9": // Dihapus
            //     this.avgOfAvgValue9 = value;
            //     break;
            case "user_id":
                this.user_id = value;
                break;
            case "team_teaching_id": // Tambahkan jika diperlukan
                this.team_teaching_id = value;
                break;
            case "linguistic_value_id": // Tambahkan jika diperlukan
                this.linguistic_value_id = value;
                break;
            case "lecture": // Tambahkan jika diperlukan
                this.lecture = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}