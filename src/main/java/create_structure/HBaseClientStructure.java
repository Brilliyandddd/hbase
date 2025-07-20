package create_structure;

import com.github.javafaker.Faker;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HBaseClientStructure {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

        // Create Tabel Mata Kuliah
        TableName tableSubject = TableName.valueOf("subjects");
        String[] subjects = { "main", "study_program", "subject_group", "detail" };
        client.deleteTable(tableSubject);
        client.createTable(tableSubject, subjects);

        // Create Tabel Bab
        TableName tableChapter = TableName.valueOf("chapters");
        String[] chapters = { "main", "subject", "detail" };
        client.deleteTable(tableChapter);
        client.createTable(tableChapter, chapters);

        // Create Tabel Rumpun Mata Kuliah
        TableName tableSubjectGroup = TableName.valueOf("subject_groups");
        String[] subjectGroups = { "main", "detail" };
        client.deleteTable(tableSubjectGroup);
        client.createTable(tableSubjectGroup, subjectGroups);

        // Create Tabel Dosen
         TableName tableLecture= TableName.valueOf("lectures");
         String[] lectures = { "main", "study_program", "religion", "user", "detail" };
         client.deleteTable(tableLecture);
         client.createTable(tableLecture, lectures);

        // Create Tabel Mahasiswa
         TableName tableStudent = TableName.valueOf("students");
         String[] students = { "main", "study_program", "religion", "user", "detail" };
         client.deleteTable(tableStudent);
         client.createTable(tableStudent, students);

        // Create Tabel RPS
        TableName tableRPS = TableName.valueOf("rps");
        String[] RPS = { "main", "learningMediaSoftware","learningMediaHardware", "studyProgram", "subject", "developerLecturer", "coordinatorLecturer","instructorLecturer", "detail" };
        client.deleteTable(tableRPS);
        client.createTable(tableRPS, RPS);

        // Create Tabel Detail RPS
         TableName tableRPSDetail = TableName.valueOf("rps_details");
         String[] RPSDetails = { "main", "rps", "learning_materials", "form_learning", "learning_methods", "assignments", "estimated_times", "student_learning_experiences", "assessment_criterias", "appraisal_forms", "assessment_indicators", "detail" };
         client.deleteTable(tableRPSDetail);
         client.createTable(tableRPSDetail, RPSDetails);

        // Create Table Pustaka
        TableName tableReference = TableName.valueOf("references");
        String[] references = { "main", "detail" };
        client.deleteTable(tableReference);
        client.createTable(tableReference, references);

        // Create Table Media Pembelajaran
        TableName tableLearningMedia = TableName.valueOf("learning_medias");
        String[] learningMedias = { "main", "detail" };
        client.deleteTable(tableLearningMedia);
        client.createTable(tableLearningMedia, learningMedias);

        // Create Table Agama
        TableName tableReligion = TableName.valueOf("religions");
        String[] religions = { "main", "detail" };
        client.deleteTable(tableReligion);
        client.createTable(tableReligion, religions);

        // Create Table Jurusan
        TableName tableDepartment = TableName.valueOf("departments");
        String[] departments = { "main", "detail" };
        client.deleteTable(tableDepartment);
        client.createTable(tableDepartment, departments);

        // Create Table Prodi
        TableName tableStudyProgram = TableName.valueOf("study_programs");
        String[] studyPrograms = { "main", "department", "detail" };
        client.deleteTable(tableStudyProgram);
        client.createTable(tableStudyProgram, studyPrograms);

         //Create Table Users
         TableName tableUser = TableName.valueOf("users");
         String[] users = { "main", "detail" };
         client.deleteTable(tableUser);
         client.createTable(tableUser, users);

        // Create Table Bentuk Penilaian
        TableName tableAppraisalForm = TableName.valueOf("appraisal_forms");
        String[] appraisalForms = { "main", "detail" };
        client.deleteTable(tableAppraisalForm);
        client.createTable(tableAppraisalForm, appraisalForms);

        // Create Tabel Kriteria Penilaian
        TableName tableAssessmentCriteria = TableName.valueOf("assessment_criterias");
        String[] assessmentCriterias = { "main", "detail" };
        client.deleteTable(tableAssessmentCriteria);
        client.createTable(tableAssessmentCriteria, assessmentCriterias);

        // Create Tabel Sub Kriteria Penilaian
        TableName tableSubAssessmentCriteria = TableName.valueOf("sub_assessment_criterias");
        String[] subAssessmentCriterias = { "main", "assessment_criterias","detail" };
        client.deleteTable(tableSubAssessmentCriteria);
        client.createTable(tableSubAssessmentCriteria, subAssessmentCriterias);

        // Create Tabel Bentuk Pembelajaran
        TableName tableFormLearning = TableName.valueOf("form_learnings");
        String[] formLearnings = { "main", "detail" };
        client.deleteTable(tableFormLearning);
        client.createTable(tableFormLearning, formLearnings);

        // Create Tabel Metode Pembelajaran
        TableName tableLearningMethod = TableName.valueOf("learning_methods");
        String[] learningMethods = { "main", "detail" };
        client.deleteTable(tableLearningMethod);
        client.createTable(tableLearningMethod, learningMethods);

         //Create Tabel Pertanyaan
         TableName tableQuestion = TableName.valueOf("questions");
         String[] questions = { "main", "rps_detail", "detail", "rps"};
         client.deleteTable(tableQuestion);
         client.createTable(tableQuestion, questions);

         //Create Tabel Jawaban
         TableName tableAnswer = TableName.valueOf("answers");
         String[] answers = { "main", "question", "detail" };
         client.deleteTable(tableAnswer);
         client.createTable(tableAnswer, answers);

         // Create Tabel Analisa Kausalitas
         TableName tableCausality = TableName.valueOf("causality");
            String[] causality = { "main", "lecture", "subject" };
            client.deleteTable(tableCausality);
            client.createTable(tableCausality, causality);
            
        // Create Tabel Penilaian Kausalitas
        TableName causalityRatingTableName = TableName.valueOf("causality_rating");
        String[] causalityRating = { "main", "detail" };
        client.deleteTable(causalityRatingTableName);
        client.createTable(causalityRatingTableName, causalityRating);

        // Create Tabel Hasil Analisa Kausalitas untuk menyimpan bobot per mata kuliah
        TableName tableDematelWeights = TableName.valueOf("dematel_weights");
        String[] dematelWeights = { "main" };
        client.deleteTable(tableDematelWeights);
        client.createTable(tableDematelWeights, dematelWeights);

        // Create Tabel Ujian
        TableName tableExam = TableName.valueOf("exams");
        String[] exams = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableExam);
        client.createTable(tableExam, exams);

        // Create Tabel Kuis
         TableName tableQuizzes = TableName.valueOf("quizzes");
         String[] quizzes = { "main", "rps","developerLecturer","instructorLecturer","coordinatorLecturer", "questions", "detail" };
         client.deleteTable(tableQuizzes);
         client.createTable(tableQuizzes, quizzes);
         
        // Create Tabel Pengumuman Kuis
        TableName tableQuizzesAnnouncement = TableName.valueOf("quizzes_announcement");
        String[] quizzes_announcement = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableQuizzesAnnouncement);
        client.createTable(tableQuizzesAnnouncement, quizzes_announcement);

        // Create Tabel Latihan
         TableName tableExcercise = TableName.valueOf("exercises");
         String[] exercises = { "main", "rps", "questions", "detail" };
         client.deleteTable(tableExcercise);
         client.createTable(tableExcercise, exercises);

        // Create Tabel Percobaan pengumpulan Ujian
        TableName tableExamAttempts = TableName.valueOf("exam_attempts");
        String[] examAttempts = { "main", "exam", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableExamAttempts);
        client.createTable(tableExamAttempts, examAttempts);

        // Create Tabel Percobaan pengumpulan Kuis
        TableName tableQuizAttempts = TableName.valueOf("quiz_attempts");
        String[] quizAttempts = { "main", "quiz", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableQuizAttempts);
        client.createTable(tableQuizAttempts, quizAttempts);

        // // Create Tabel Percobaan pengumpulan Latihan
         TableName tableExerciseAttempts = TableName.valueOf("exercise_attempts");
         String[] exerciseAttempts = { "main", "exercise", "user", "student", "student_answers", "detail" };
         client.deleteTable(tableExerciseAttempts);
         client.createTable(tableExerciseAttempts, exerciseAttempts);

        // Create Tabel Metode Pembelajaran
        TableName tableExamType = TableName.valueOf("exam_types");
        String[] examTypes = { "main", "detail" };
        client.deleteTable(tableExamType);
        client.createTable(tableExamType, examTypes);

        // Create Tabel Krireria Penilaian Soal

         TableName tableQuestionCriteria = TableName.valueOf("question_criterias");
         String[] questionCriterias = { "main", "detail" };
         client.deleteTable(tableQuestionCriteria);
         client.createTable(tableQuestionCriteria, questionCriterias);

        // Create Tabel Nilai Linguistic

         TableName tableLinguisticValue = TableName.valueOf("linguistic_values");
         String[] linguisticValues = { "main", "detail" };
         client.deleteTable(tableLinguisticValue);
         client.createTable(tableLinguisticValue, linguisticValues);

        // Create Tabel  Team Teaching
        TableName tableTeamTeaching = TableName.valueOf("team_teachings");
        String[] teamTeachings = {"main","detail","lecture","lecture2","lecture3"};
        client.deleteTable(tableTeamTeaching);
        client.createTable(tableTeamTeaching, teamTeachings);

        // Create Tabel Penilaian Soal
        TableName tableCriteriaValue = TableName.valueOf("criteria_values");
        String[] criteriaValues = { "main","detail","team_teaching","question","user","value1","value2","value3","value4","value5","value6","value7","value8","value9","value10"};
        client.deleteTable(tableCriteriaValue);
        client.createTable(tableCriteriaValue, criteriaValues);

        // seeder
        // time now
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        // Insert Jurusan
       client.insertRecord(tableDepartment, "DP001", "main", "id", "DP001");
       client.insertRecord(tableDepartment, "DP001", "main", "name", "Jurusan Teknologi Informasi");
       client.insertRecord(tableDepartment, "DP001", "main", "description", "Ini merupakan jurusan untuk mahasiswa informatika");
       client.insertRecord(tableDepartment, "DP001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableDepartment, "DP002", "main", "id", "DP002");
       client.insertRecord(tableDepartment, "DP002", "main", "name", "Jurusan Teknik Mesin");
       client.insertRecord(tableDepartment, "DP002", "main", "description", "Ini merupakan jurusan untuk mahasiswa teknik mesin");
       client.insertRecord(tableDepartment, "DP002", "detail", "created_by", "Doyatama");
//
//        // Insert Prodi
       client.insertRecord(tableStudyProgram, "SP001", "main", "id", "SP001");
       client.insertRecord(tableStudyProgram, "SP001", "main", "name", "D4 Teknik Informatika");
       client.insertRecord(tableStudyProgram, "SP001", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
       client.insertRecord(tableStudyProgram, "SP001", "department", "id", "DP001");
       client.insertRecord(tableStudyProgram, "SP001", "department", "name", "Jurusan Teknologi Informasi");
       client.insertRecord(tableStudyProgram, "SP001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableStudyProgram, "SP002", "main", "id", "SP002");
       client.insertRecord(tableStudyProgram, "SP002", "main", "name", "D3 Manajemen Informatika");
       client.insertRecord(tableStudyProgram, "SP002", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
       client.insertRecord(tableStudyProgram, "SP002", "department", "id", "DP002");
       client.insertRecord(tableStudyProgram, "SP002", "department", "name", "Jurusan Teknik Mesin");
       client.insertRecord(tableStudyProgram, "SP002", "detail", "created_by", "Doyatama");

         //Insert Users
         client.insertRecord(tableUser, "USR001", "main", "id", "USR001");
         client.insertRecord(tableUser, "USR001", "main", "name", "Doyatama");
         client.insertRecord(tableUser, "USR001", "main", "email", "admin@gmail.com");
         client.insertRecord(tableUser, "USR001", "main", "username", "admin");
         client.insertRecord(tableUser, "USR001", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
         client.insertRecord(tableUser, "USR001", "main", "roles", "ROLE_ADMINISTRATOR");
         client.insertRecord(tableUser, "USR001", "main", "created_at", "2023-05-14T04:56:23.174Z");
         client.insertRecord(tableUser, "USR001", "detail", "created_by", "Doyatama");

         client.insertRecord(tableUser, "USR002", "main", "id", "USR002");
         client.insertRecord(tableUser, "USR002", "main", "name", "Doyatama");
         client.insertRecord(tableUser, "USR002", "main", "email", "dosen@gmail.com");
         client.insertRecord(tableUser, "USR002", "main", "username", "dosen");
         client.insertRecord(tableUser, "USR002", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
         client.insertRecord(tableUser, "USR002", "main", "roles", "ROLE_LECTURE");
         client.insertRecord(tableUser, "USR002", "main", "created_at", "2023-05-14T04:56:23.174Z");
         client.insertRecord(tableUser, "USR002", "detail", "created_by", "Doyatama");

         client.insertRecord(tableUser, "USR003", "main", "id", "USR003");
         client.insertRecord(tableUser, "USR003", "main", "name", "Doyatama");
         client.insertRecord(tableUser, "USR003", "main", "email", "dosen2@gmail.com");
         client.insertRecord(tableUser, "USR003", "main", "username", "dosen2");
         client.insertRecord(tableUser, "USR003", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
         client.insertRecord(tableUser, "USR003", "main", "roles", "ROLE_LECTURE");
         client.insertRecord(tableUser, "USR003", "main", "created_at", "2023-05-14T04:56:23.174Z");
         client.insertRecord(tableUser, "USR003", "detail", "created_by", "Doyatama");

         // Define the data
      List<Map<String, String>> usersToInsert = Arrays.asList(
           new HashMap<String, String>() {{
               put("id", "Dosen1");
               put("name", "Imam Fahrur Rozi, ST., MT");
               put("username", "ImamFahrurRozi");
               put("email", "ImamFahrurRozi@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen2");
               put("name", "Frihandhika Permana SPd., MKom.");
               put("username", "Frihandhika");
               put("email", "FrihandhikaPermana@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen3");
               put("name", "Milyun Ni’ma Shoumi, S.Kom., M.Kom.");
               put("username", "MilyunNima");
               put("email", "MilyunNi’maShoumi@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
           }},
            new HashMap<String, String>() {{
               put("id", "Dosen4");
               put("name", "Putra Prima Arhandi, ST., M.Kom.");
               put("username", "PutraPrima");
               put("email", "PutraPrima@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
               put("createdAt", null);
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen5");
               put("name", "Muhammad Shulhan Khairy, S.Kom, M.Kom");
               put("username", "MuhammadShulha");
               put("email", "MuhammadShulhanKhairy@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
               put("createdAt", null);
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen6");
               put("name", "Gunawan Budiprasetyo, S.T., M.MT., Ph.D.");
               put("username", "GunawanBudi");
               put("email", "GunawanBudi@gmaill.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
               put("createdAt", null);
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen7");
               put("name", "Banni Satria Andoko, S. Kom., M.MSI");
               put("username", "BanniSatria");
               put("email", "BanniSatriaAndoko@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
               put("createdAt", null);
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen8");
               put("name", "Priska Choirina, S.S.T., M.Tr.T");
               put("username", "PriskaChoirina");
               put("email", "PriskaChoirina@gmail.com");
               put("password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
               put("roles", "2");
               put("createdAt", null);
           }},
           new HashMap<String, String>() {{
              put("id", "Dosen10");
              put("name", "Irsyad Arif Mashudi, S.Kom., M.Kom.");
              put("username", "IrsyadArief");
              put("email", "irsyadarief@gmail.com");
              put("password","$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
              put("roles", "2");
              put("createdAt", null);
          }},
          new HashMap<String, String>() {{
              put("id", "Dosen11");
              put("name", "Inggrid Yanuar Risca Pratiwi, S.Kom., M.Kom.");
              put("username", "InggridYanuar");
              put("email", "inggridyanuar@gmail.com");
              put("password","$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2");
              put("roles", "2");
              put("createdAt", null);
          }}
      );

//       // Loop over the data and insert each user
      for (Map<String, String> user : usersToInsert) {
          client.insertRecord(tableUser, user.get("id"), "main", "id", user.get("id"));
          client.insertRecord(tableUser, user.get("id"), "main", "name", user.get("name"));
          client.insertRecord(tableUser, user.get("id"), "main", "username", user.get("username"));
          client.insertRecord(tableUser, user.get("id"), "main", "email", user.get("email"));
          client.insertRecord(tableUser, user.get("id"), "main", "password", user.get("password"));
          client.insertRecord(tableUser, user.get("id"), "main", "roles", user.get("roles"));
          client.insertRecord(tableUser, user.get("id"), "detail", "created_by", "Doyatama");
      }
        
        // Insert Religions
       client.insertRecord(tableReligion, "RLG001", "main", "id", "RLG001");
       client.insertRecord(tableReligion, "RLG001", "main", "name", "Islam");
       client.insertRecord(tableReligion, "RLG001", "main", "description", "deskripsi agama islam");
       client.insertRecord(tableReligion, "RLG001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableReligion, "RLG002", "main", "id", "RLG002");
       client.insertRecord(tableReligion, "RLG002", "main", "name", "Kristen");
       client.insertRecord(tableReligion, "RLG002", "main", "description", "deskripsi agama kristen");
       client.insertRecord(tableReligion, "RLG002", "detail", "created_by", "Doyatama");

       client.insertRecord(tableReligion, "RLG003", "main", "id", "RLG003");
       client.insertRecord(tableReligion, "RLG003", "main", "name", "Katolik");
       client.insertRecord(tableReligion, "RLG003", "main", "description", "deskripsi agama katolik");
       client.insertRecord(tableReligion, "RLG003", "detail", "created_by", "Doyatama");

       client.insertRecord(tableReligion, "RLG004", "main", "id", "RLG004");
       client.insertRecord(tableReligion, "RLG004", "main", "name", "Hindu");
       client.insertRecord(tableReligion, "RLG004", "main", "description", "deskripsi agama hindu");
       client.insertRecord(tableReligion, "RLG004", "detail", "created_by", "Doyatama");

       client.insertRecord(tableReligion, "RLG005", "main", "id", "RLG005");
       client.insertRecord(tableReligion, "RLG005", "main", "name", "Buddha");
       client.insertRecord(tableReligion, "RLG005", "main", "description", "deskripsi agama budha");
       client.insertRecord(tableReligion, "RLG005", "detail", "created_by", "Doyatama");

       client.insertRecord(tableReligion, "RLG006", "main", "id", "RLG006");
       client.insertRecord(tableReligion, "RLG006", "main", "name", "Kong Hu Chu");
       client.insertRecord(tableReligion, "RLG006", "main", "description", "deskripsi agama kong hu chu");
       client.insertRecord(tableReligion, "RLG006", "detail", "created_by", "Doyatama");
//
//        // Insert Bentuk Pembelajaran
       client.insertRecord(tableFormLearning, "BP001", "main", "id", "BP001");
       client.insertRecord(tableFormLearning, "BP001", "main", "name", "Daring");
       client.insertRecord(tableFormLearning, "BP001", "main", "description", "Pembelajaran dilakukan secara dalam jaringan / online");
       client.insertRecord(tableFormLearning, "BP001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableFormLearning, "BP002", "main", "id", "BP002");
       client.insertRecord(tableFormLearning, "BP002", "main", "name", "Luring");
       client.insertRecord(tableFormLearning, "BP002", "main", "description", "Pembelajaran dilakukan secara diluar jaringan / offline");
       client.insertRecord(tableFormLearning, "BP002", "detail", "created_by", "Doyatama");

//        // Insert Metode Pembelajaran
       client.insertRecord(tableLearningMethod, "MP001", "main", "id", "MP001");
       client.insertRecord(tableLearningMethod, "MP001", "main", "name", "Contextual Teaching and Learning (CTL)");
       client.insertRecord(tableLearningMethod, "MP001", "main", "description", "Pengertian dari CTL");
       client.insertRecord(tableLearningMethod, "MP001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableLearningMethod, "MP002", "main", "id", "MP002");
       client.insertRecord(tableLearningMethod, "MP002", "main", "name", "Problem Based Learning");
       client.insertRecord(tableLearningMethod, "MP002", "main", "description", "Pengertian dari PBL");
       client.insertRecord(tableLearningMethod, "MP002", "detail", "created_by", "Doyatama");

         // Insert Tipe ujian
       client.insertRecord(tableExamType, "EX001", "main", "id", "EX001");
       client.insertRecord(tableExamType, "EX001", "main", "name", "EXERCISE");
       client.insertRecord(tableExamType, "EX001", "main", "description", "Untuk latihan siswa");
       client.insertRecord(tableExamType, "EX001", "detail", "created_by", "Alfa");

       client.insertRecord(tableExamType, "EX002", "main", "id", "EX002");
       client.insertRecord(tableExamType, "EX002", "main", "name", "QUIZ");
       client.insertRecord(tableExamType, "EX002", "main", "description", "Untuk perssiapan ujian siswa");
       client.insertRecord(tableExamType, "EX002", "detail", "created_by", "Alfa");

       client.insertRecord(tableExamType, "EX003", "main", "id", "EX003");
       client.insertRecord(tableExamType, "EX003", "main", "name", "EXAM");
       client.insertRecord(tableExamType, "EX003", "main", "description", "Untuk ujian siswa");
       client.insertRecord(tableExamType, "EX003", "detail", "created_by", "Alfa");

        // Insert Kriteria Penilaian
       client.insertRecord(tableAssessmentCriteria, "KP001", "main", "id", "KP001");
       client.insertRecord(tableAssessmentCriteria, "KP001", "main", "name", "Ketepatan");
       client.insertRecord(tableAssessmentCriteria, "KP001", "main", "description", "Ketepatan dalam pengumpulan tugas dan kehadiran di kelas");
       client.insertRecord(tableAssessmentCriteria, "KP001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableAssessmentCriteria, "KP002", "main", "id", "KP002");
       client.insertRecord(tableAssessmentCriteria, "KP002", "main", "name", "Penugasan");
       client.insertRecord(tableAssessmentCriteria, "KP002", "main", "description", "Penilaian didasarkan dari penugasan yang diberikan dosen");
       client.insertRecord(tableAssessmentCriteria, "KP002", "detail", "created_by", "Doyatama");
//
//        // Insert Bentuk Penilaian
       client.insertRecord(tableAppraisalForm, "AF001", "main", "id", "AF001");
       client.insertRecord(tableAppraisalForm, "AF001", "main", "name", "Keaktifan diskusi kelompok meliputi bertanya dan menjawab");
       client.insertRecord(tableAppraisalForm, "AF001", "main", "description", "Keaktifan diskusi kelompok");
       client.insertRecord(tableAppraisalForm, "AF001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableAppraisalForm, "AF002", "main", "id", "AF002");
       client.insertRecord(tableAppraisalForm, "AF002", "main", "name", "Ketepatan jawaban tugas");
       client.insertRecord(tableAppraisalForm, "AF002", "main", "description", "Ketepatan jawaban yang diberikan dari tugas");
       client.insertRecord(tableAppraisalForm, "AF002", "detail", "created_by", "Doyatama");
//
//        // Insert Subject Group
       client.insertRecord(tableSubjectGroup, "SG001", "main", "id", "SG001");
       client.insertRecord(tableSubjectGroup, "SG001", "main", "name", "Big Data");
       client.insertRecord(tableSubjectGroup, "SG001", "main", "description", "ini kelompok big data");
       client.insertRecord(tableSubjectGroup, "SG001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableSubjectGroup, "SG002", "main", "id", "SG002");
       client.insertRecord(tableSubjectGroup, "SG002", "main", "name", "Artificial Inteligent");
       client.insertRecord(tableSubjectGroup, "SG002", "main", "description", "ini kelompok AI");
       client.insertRecord(tableSubjectGroup, "SG002", "detail", "created_by", "Doyatama");

       client.insertRecord(tableSubjectGroup, "SG003", "main", "id", "SG003");
       client.insertRecord(tableSubjectGroup, "SG003", "main", "name", "Sistem Pendukung Keputusan");
       client.insertRecord(tableSubjectGroup, "SG003", "main", "description", "ini kelompok SPK");
       client.insertRecord(tableSubjectGroup, "SG003", "detail", "created_by", "Doyatama");
//
//        // Insert Subject
       client.insertRecord(tableSubject, "SB001", "main", "id", "SB001");
       client.insertRecord(tableSubject, "SB001", "main", "name", "Pemrograman Berbasis Object");
       client.insertRecord(tableSubject, "SB001", "main", "description", "ini deskripsi dari mata kuliah");
       client.insertRecord(tableSubject, "SB001", "main", "credit_point", "4");
       client.insertRecord(tableSubject, "SB001", "main", "year_commenced", "2023");
       client.insertRecord(tableSubject, "SB001", "study_program", "id", "SP001");
       client.insertRecord(tableSubject, "SB001", "study_program", "name", "D4 Teknik Informatika");
       client.insertRecord(tableSubject, "SB001", "subject_group", "id", "SG001");
       client.insertRecord(tableSubject, "SB001", "subject_group", "name", "Big Data");
       client.insertRecord(tableSubject, "SB001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableSubject, "SB002", "main", "id", "SB002");
       client.insertRecord(tableSubject, "SB002", "main", "name", "Pemrograman Lanjut");
       client.insertRecord(tableSubject, "SB002", "main", "description", "ini deskripsi dari mata kuliah");
       client.insertRecord(tableSubject, "SB002", "main", "credit_point", "4");
       client.insertRecord(tableSubject, "SB002", "main", "year_commenced", "2023");
       client.insertRecord(tableSubject, "SB002", "study_program", "id", "SP001");
       client.insertRecord(tableSubject, "SB002", "study_program", "name", "D4 Teknik Informatika");
       client.insertRecord(tableSubject, "SB002", "subject_group", "id", "SG001");
       client.insertRecord(tableSubject, "SB002", "subject_group", "name", "Big Data");
       client.insertRecord(tableSubject, "SB002", "detail", "created_by", "Doyatama");
//
//        // Insert Lectures
       client.insertRecord(tableLecture, "LEC001", "main", "id", "LEC001");
       client.insertRecord(tableLecture, "LEC001", "main", "address", "dosen@gmail.com");
       client.insertRecord(tableLecture, "LEC001", "main", "date_born", "dosen");
       client.insertRecord(tableLecture, "LEC001", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
       client.insertRecord(tableLecture, "LEC001", "main", "name", "2");
       client.insertRecord(tableLecture, "LEC001", "main", "nidn", "2");
       client.insertRecord(tableLecture, "LEC001", "main", "phone", "2");
       client.insertRecord(tableLecture, "LEC001", "main", "place_born", "2");
       client.insertRecord(tableLecture, "LEC001", "main", "status", "Dosen");
       client.insertRecord(tableLecture, "LEC001", "religion", "id", "RLG001");
       client.insertRecord(tableLecture, "LEC001", "religion", "name", "Islam");
       client.insertRecord(tableLecture, "LEC001", "study_program", "id", "SP001");
       client.insertRecord(tableLecture, "LEC001", "study_program", "name", "D4 Teknik Informatika");
       client.insertRecord(tableLecture, "LEC001", "user", "id", "USR001");
       client.insertRecord(tableLecture, "LEC001", "user", "name", "dosen");
       client.insertRecord(tableLecture, "LEC001", "user", "email", "dosen@gmail.com");
       client.insertRecord(tableLecture, "LEC001", "user", "username", "dosen");
       client.insertRecord(tableLecture, "LEC001", "detail", "created_by", "Doyatama");

       client.insertRecord(tableLecture, "LEC002", "main", "id", "LEC002");
       client.insertRecord(tableLecture, "LEC002", "main", "address", "dosen2@gmail.com");
       client.insertRecord(tableLecture, "LEC002", "main", "date_born", "dosen2");
       client.insertRecord(tableLecture, "LEC002", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
       client.insertRecord(tableLecture, "LEC002", "main", "name", "2");
       client.insertRecord(tableLecture, "LEC002", "main", "nidn", "2");
       client.insertRecord(tableLecture, "LEC002", "main", "phone", "2");
       client.insertRecord(tableLecture, "LEC002", "main", "place_born", "2");
       client.insertRecord(tableLecture, "LEC002", "main", "status", "Dosen");
       client.insertRecord(tableLecture, "LEC002", "religion", "id", "RLG001");
       client.insertRecord(tableLecture, "LEC002", "religion", "name", "Islam");
       client.insertRecord(tableLecture, "LEC002", "study_program", "id", "SP001");
       client.insertRecord(tableLecture, "LEC002", "study_program", "name", "D4 Teknik Informatika");
       client.insertRecord(tableLecture, "LEC002", "user", "id", "USR002");
       client.insertRecord(tableLecture, "LEC002", "user", "name", "dosen");
       client.insertRecord(tableLecture, "LEC002", "user", "email", "dosen@gmail.com");
       client.insertRecord(tableLecture, "LEC002", "user", "username", "dosen");
       client.insertRecord(tableLecture, "LEC002", "detail", "created_by", "Doyatama");

       // Insert Linguistic Value
client.insertRecord(tableLinguisticValue, "LV001", "main", "id", "LV001");
client.insertRecord(tableLinguisticValue, "LV001", "main", "name", "Nothing");
client.insertRecord(tableLinguisticValue, "LV001", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV001", "main", "value2", "0.0000");
client.insertRecord(tableLinguisticValue, "LV001", "main", "value3", "0.0345");
client.insertRecord(tableLinguisticValue, "LV001", "main", "value4", "0.0690");

client.insertRecord(tableLinguisticValue, "LV002", "main", "id", "LV002");
client.insertRecord(tableLinguisticValue, "LV002", "main", "name", "Between nothing and very bad");
client.insertRecord(tableLinguisticValue, "LV002", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV002", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV002", "main", "value3", "0.0345");
client.insertRecord(tableLinguisticValue, "LV002", "main", "value4", "0.0690");

client.insertRecord(tableLinguisticValue, "LV003", "main", "id", "LV003");
client.insertRecord(tableLinguisticValue, "LV003", "main", "name", "Very bad");
client.insertRecord(tableLinguisticValue, "LV003", "main", "value1", "0.0345");
client.insertRecord(tableLinguisticValue, "LV003", "main", "value2", "0.0690");
client.insertRecord(tableLinguisticValue, "LV003", "main", "value3", "0.1034");
client.insertRecord(tableLinguisticValue, "LV003", "main", "value4", "0.1379");

client.insertRecord(tableLinguisticValue, "LV004", "main", "id", "LV004");
client.insertRecord(tableLinguisticValue, "LV004", "main", "name", "At least very bad");
client.insertRecord(tableLinguisticValue, "LV004", "main", "value1", "0.0690");
client.insertRecord(tableLinguisticValue, "LV004", "main", "value2", "0.1034");
client.insertRecord(tableLinguisticValue, "LV004", "main", "value3", "0.9310");
client.insertRecord(tableLinguisticValue, "LV004", "main", "value4", "0.9655");

client.insertRecord(tableLinguisticValue, "LV005", "main", "id", "LV005");
client.insertRecord(tableLinguisticValue, "LV005", "main", "name", "Between very bad and bad");
client.insertRecord(tableLinguisticValue, "LV005", "main", "value1", "0.0690");
client.insertRecord(tableLinguisticValue, "LV005", "main", "value2", "0.1034");
client.insertRecord(tableLinguisticValue, "LV005", "main", "value3", "0.2069");
client.insertRecord(tableLinguisticValue, "LV005", "main", "value4", "0.2414");

client.insertRecord(tableLinguisticValue, "LV006", "main", "id", "LV006");
client.insertRecord(tableLinguisticValue, "LV006", "main", "name", "Between very bad and medium");
client.insertRecord(tableLinguisticValue, "LV006", "main", "value1", "0.0690");
client.insertRecord(tableLinguisticValue, "LV006", "main", "value2", "0.1034");
client.insertRecord(tableLinguisticValue, "LV006", "main", "value3", "0.4138");
client.insertRecord(tableLinguisticValue, "LV006", "main", "value4", "0.4483");

client.insertRecord(tableLinguisticValue, "LV007", "main", "id", "LV007");
client.insertRecord(tableLinguisticValue, "LV007", "main", "name", "At most very bad");
client.insertRecord(tableLinguisticValue, "LV007", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV007", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV007", "main", "value3", "0.0345");
client.insertRecord(tableLinguisticValue, "LV007", "main", "value4", "0.0690");

client.insertRecord(tableLinguisticValue, "LV008", "main", "id", "LV008");
client.insertRecord(tableLinguisticValue, "LV008", "main", "name", "Bad");
client.insertRecord(tableLinguisticValue, "LV008", "main", "value1", "0.2069");
client.insertRecord(tableLinguisticValue, "LV008", "main", "value2", "0.2414");
client.insertRecord(tableLinguisticValue, "LV008", "main", "value3", "0.2759");
client.insertRecord(tableLinguisticValue, "LV008", "main", "value4", "0.3103");

client.insertRecord(tableLinguisticValue, "LV009", "main", "id", "LV009");
client.insertRecord(tableLinguisticValue, "LV009", "main", "name", "At least bad");
client.insertRecord(tableLinguisticValue, "LV009", "main", "value1", "0.2414");
client.insertRecord(tableLinguisticValue, "LV009", "main", "value2", "0.2759");
client.insertRecord(tableLinguisticValue, "LV009", "main", "value3", "0.9310");
client.insertRecord(tableLinguisticValue, "LV009", "main", "value4", "0.9655");

client.insertRecord(tableLinguisticValue, "LV010", "main", "id", "LV010");
client.insertRecord(tableLinguisticValue, "LV010", "main", "name", "Between bad and medium");
client.insertRecord(tableLinguisticValue, "LV010", "main", "value1", "0.2414");
client.insertRecord(tableLinguisticValue, "LV010", "main", "value2", "0.2759");
client.insertRecord(tableLinguisticValue, "LV010", "main", "value3", "0.4138");
client.insertRecord(tableLinguisticValue, "LV010", "main", "value4", "0.4483");

client.insertRecord(tableLinguisticValue, "LV011", "main", "id", "LV011");
client.insertRecord(tableLinguisticValue, "LV011", "main", "name", "Between bad and good");
client.insertRecord(tableLinguisticValue, "LV011", "main", "value1", "0.2414");
client.insertRecord(tableLinguisticValue, "LV011", "main", "value2", "0.2759");
client.insertRecord(tableLinguisticValue, "LV011", "main", "value3", "0.5862");
client.insertRecord(tableLinguisticValue, "LV011", "main", "value4", "0.6207");

client.insertRecord(tableLinguisticValue, "LV012", "main", "id", "LV012");
client.insertRecord(tableLinguisticValue, "LV012", "main", "name", "Between bad and very good");
client.insertRecord(tableLinguisticValue, "LV012", "main", "value1", "0.2414");
client.insertRecord(tableLinguisticValue, "LV012", "main", "value2", "0.2759");
client.insertRecord(tableLinguisticValue, "LV012", "main", "value3", "0.7586");
client.insertRecord(tableLinguisticValue, "LV012", "main", "value4", "0.7931");

client.insertRecord(tableLinguisticValue, "LV013", "main", "id", "LV013");
client.insertRecord(tableLinguisticValue, "LV013", "main", "name", "At most bad");
client.insertRecord(tableLinguisticValue, "LV013", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV013", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV013", "main", "value3", "0.2069");
client.insertRecord(tableLinguisticValue, "LV013", "main", "value4", "0.2414");

client.insertRecord(tableLinguisticValue, "LV014", "main", "id", "LV014");
client.insertRecord(tableLinguisticValue, "LV014", "main", "name", "Medium");
client.insertRecord(tableLinguisticValue, "LV014", "main", "value1", "0.4138");
client.insertRecord(tableLinguisticValue, "LV014", "main", "value2", "0.4483");
client.insertRecord(tableLinguisticValue, "LV014", "main", "value3", "0.4828");
client.insertRecord(tableLinguisticValue, "LV014", "main", "value4", "0.5172");

client.insertRecord(tableLinguisticValue, "LV015", "main", "id", "LV015");
client.insertRecord(tableLinguisticValue, "LV015", "main", "name", "At least medium");
client.insertRecord(tableLinguisticValue, "LV015", "main", "value1", "0.4483");
client.insertRecord(tableLinguisticValue, "LV015", "main", "value2", "0.4828");
client.insertRecord(tableLinguisticValue, "LV015", "main", "value3", "0.9310");
client.insertRecord(tableLinguisticValue, "LV015", "main", "value4", "0.9655");

client.insertRecord(tableLinguisticValue, "LV016", "main", "id", "LV016");
client.insertRecord(tableLinguisticValue, "LV016", "main", "name", "Between medium and good");
client.insertRecord(tableLinguisticValue, "LV016", "main", "value1", "0.4483");
client.insertRecord(tableLinguisticValue, "LV016", "main", "value2", "0.4828");
client.insertRecord(tableLinguisticValue, "LV016", "main", "value3", "0.5862");
client.insertRecord(tableLinguisticValue, "LV016", "main", "value4", "0.6207");

client.insertRecord(tableLinguisticValue, "LV017", "main", "id", "LV017");
client.insertRecord(tableLinguisticValue, "LV017", "main", "name", "Between medium and very good");
client.insertRecord(tableLinguisticValue, "LV017", "main", "value1", "0.4483");
client.insertRecord(tableLinguisticValue, "LV017", "main", "value2", "0.4828");
client.insertRecord(tableLinguisticValue, "LV017", "main", "value3", "0.7586");
client.insertRecord(tableLinguisticValue, "LV017", "main", "value4", "0.7931");

client.insertRecord(tableLinguisticValue, "LV018", "main", "id", "LV018");
client.insertRecord(tableLinguisticValue, "LV018", "main", "name", "At most medium");
client.insertRecord(tableLinguisticValue, "LV018", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV018", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV018", "main", "value3", "0.4138");
client.insertRecord(tableLinguisticValue, "LV018", "main", "value4", "0.4483");

client.insertRecord(tableLinguisticValue, "LV019", "main", "id", "LV019");
client.insertRecord(tableLinguisticValue, "LV019", "main", "name", "Good");
client.insertRecord(tableLinguisticValue, "LV019", "main", "value1", "0.5862");
client.insertRecord(tableLinguisticValue, "LV019", "main", "value2", "0.6207");
client.insertRecord(tableLinguisticValue, "LV019", "main", "value3", "0.6552");
client.insertRecord(tableLinguisticValue, "LV019", "main", "value4", "0.6897");

client.insertRecord(tableLinguisticValue, "LV020", "main", "id", "LV020");
client.insertRecord(tableLinguisticValue, "LV020", "main", "name", "At least good");
client.insertRecord(tableLinguisticValue, "LV020", "main", "value1", "0.6207");
client.insertRecord(tableLinguisticValue, "LV020", "main", "value2", "0.6552");
client.insertRecord(tableLinguisticValue, "LV020", "main", "value3", "0.9310");
client.insertRecord(tableLinguisticValue, "LV020", "main", "value4", "0.9655");

client.insertRecord(tableLinguisticValue, "LV021", "main", "id", "LV021");
client.insertRecord(tableLinguisticValue, "LV021", "main", "name", "Between good and very good");
client.insertRecord(tableLinguisticValue, "LV021", "main", "value1", "0.6207");
client.insertRecord(tableLinguisticValue, "LV021", "main", "value2", "0.6552");
client.insertRecord(tableLinguisticValue, "LV021", "main", "value3", "0.7586");
client.insertRecord(tableLinguisticValue, "LV021", "main", "value4", "0.7931");

client.insertRecord(tableLinguisticValue, "LV022", "main", "id", "LV022");
client.insertRecord(tableLinguisticValue, "LV022", "main", "name", "Between good and perfect");
client.insertRecord(tableLinguisticValue, "LV022", "main", "value1", "0.6207");
client.insertRecord(tableLinguisticValue, "LV022", "main", "value2", "0.6552");
client.insertRecord(tableLinguisticValue, "LV022", "main", "value3", "0.9655");
client.insertRecord(tableLinguisticValue, "LV022", "main", "value4", "1.0000");

client.insertRecord(tableLinguisticValue, "LV023", "main", "id", "LV023");
client.insertRecord(tableLinguisticValue, "LV023", "main", "name", "At most good");
client.insertRecord(tableLinguisticValue, "LV023", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV023", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV023", "main", "value3", "0.5862");
client.insertRecord(tableLinguisticValue, "LV023", "main", "value4", "0.6207");

client.insertRecord(tableLinguisticValue, "LV024", "main", "id", "LV024");
client.insertRecord(tableLinguisticValue, "LV024", "main", "name", "Very good");
client.insertRecord(tableLinguisticValue, "LV024", "main", "value1", "0.7586");
client.insertRecord(tableLinguisticValue, "LV024", "main", "value2", "0.7931");
client.insertRecord(tableLinguisticValue, "LV024", "main", "value3", "0.8276");
client.insertRecord(tableLinguisticValue, "LV024", "main", "value4", "0.8621");

client.insertRecord(tableLinguisticValue, "LV025", "main", "id", "LV025");
client.insertRecord(tableLinguisticValue, "LV025", "main", "name", "At least very good");
client.insertRecord(tableLinguisticValue, "LV025", "main", "value1", "0.7931");
client.insertRecord(tableLinguisticValue, "LV025", "main", "value2", "0.8276");
client.insertRecord(tableLinguisticValue, "LV025", "main", "value3", "0.9310");
client.insertRecord(tableLinguisticValue, "LV025", "main", "value4", "0.9655");

client.insertRecord(tableLinguisticValue, "LV026", "main", "id", "LV026");
client.insertRecord(tableLinguisticValue, "LV026", "main", "name", "Between very good and perfect");
client.insertRecord(tableLinguisticValue, "LV026", "main", "value1", "0.7931");
client.insertRecord(tableLinguisticValue, "LV026", "main", "value2", "0.8276");
client.insertRecord(tableLinguisticValue, "LV026", "main", "value3", "0.9655");
client.insertRecord(tableLinguisticValue, "LV026", "main", "value4", "1.0000");

client.insertRecord(tableLinguisticValue, "LV027", "main", "id", "LV027");
client.insertRecord(tableLinguisticValue, "LV027", "main", "name", "At most very good");
client.insertRecord(tableLinguisticValue, "LV027", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV027", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV027", "main", "value3", "0.7586");
client.insertRecord(tableLinguisticValue, "LV027", "main", "value4", "0.7931");

client.insertRecord(tableLinguisticValue, "LV028", "main", "id", "LV028");
client.insertRecord(tableLinguisticValue, "LV028", "main", "name", "At least perfect");
client.insertRecord(tableLinguisticValue, "LV028", "main", "value1", "0.9310");
client.insertRecord(tableLinguisticValue, "LV028", "main", "value2", "0.9655");
client.insertRecord(tableLinguisticValue, "LV028", "main", "value3", "1.0000");
client.insertRecord(tableLinguisticValue, "LV028", "main", "value4", "1.0000");

client.insertRecord(tableLinguisticValue, "LV029", "main", "id", "LV029");
client.insertRecord(tableLinguisticValue, "LV029", "main", "name", "At most perfect");
client.insertRecord(tableLinguisticValue, "LV029", "main", "value1", "0.0000");
client.insertRecord(tableLinguisticValue, "LV029", "main", "value2", "0.0345");
client.insertRecord(tableLinguisticValue, "LV029", "main", "value3", "0.9655");
client.insertRecord(tableLinguisticValue, "LV029", "main", "value4", "1.0000");

client.insertRecord(tableLinguisticValue, "LV030", "main", "id", "LV030");
client.insertRecord(tableLinguisticValue, "LV030", "main", "name", "Perfect");
client.insertRecord(tableLinguisticValue, "LV030", "main", "value1", "0.9655");
client.insertRecord(tableLinguisticValue, "LV030", "main", "value2", "1.0000");
client.insertRecord(tableLinguisticValue, "LV030", "main", "value3", "1.0000");
client.insertRecord(tableLinguisticValue, "LV030", "main", "value4", "1.0000");

       // Insert Question Criteria

       client.insertRecord(tableQuestionCriteria, "QC001", "main", "id", "QC001");
       client.insertRecord(tableQuestionCriteria, "QC001", "main", "name", "Knowledge");
       client.insertRecord(tableQuestionCriteria, "QC001", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC001", "main", "category", "Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC001", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC002", "main", "id", "QC002");
       client.insertRecord(tableQuestionCriteria, "QC002", "main", "name", "Comprehension");
       client.insertRecord(tableQuestionCriteria, "QC002", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC002", "main", "category", "Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC002", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC003", "main", "id", "QC003");
       client.insertRecord(tableQuestionCriteria, "QC003", "main", "name", "Application");
       client.insertRecord(tableQuestionCriteria, "QC003", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC003", "main", "category", "Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC003", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC004", "main", "id", "QC004");
       client.insertRecord(tableQuestionCriteria, "QC004", "main", "name", "Analysis");
       client.insertRecord(tableQuestionCriteria, "QC004", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC004", "main", "category", "Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC004", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC005", "main", "id", "QC005");
       client.insertRecord(tableQuestionCriteria, "QC005", "main", "name", "Evaluation");
       client.insertRecord(tableQuestionCriteria, "QC005", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC005", "main", "category", "Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC005", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC006", "main", "id", "QC006");
       client.insertRecord(tableQuestionCriteria, "QC006", "main", "name", "Difficulty");
       client.insertRecord(tableQuestionCriteria, "QC006", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC006", "main", "category", "Non-Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC006", "main", "type", "COST");

       client.insertRecord(tableQuestionCriteria, "QC007", "main", "id", "QC007");
       client.insertRecord(tableQuestionCriteria, "QC007", "main", "name", "Discrimination");
       client.insertRecord(tableQuestionCriteria, "QC007", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC007", "main", "category", "Non-Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC007", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC008", "main", "id", "QC008");
       client.insertRecord(tableQuestionCriteria, "QC008", "main", "name", "Reliability");
       client.insertRecord(tableQuestionCriteria, "QC008", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC008", "main", "category", "Non-Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC008", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC009", "main", "id", "QC009");
       client.insertRecord(tableQuestionCriteria, "QC009", "main", "name", "Problem Solving");
       client.insertRecord(tableQuestionCriteria, "QC009", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC009", "main", "category", "Meta-Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC009", "main", "type", "BENEFIT");

       client.insertRecord(tableQuestionCriteria, "QC010", "main", "id", "QC010");
       client.insertRecord(tableQuestionCriteria, "QC010", "main", "name", "Creativity");
       client.insertRecord(tableQuestionCriteria, "QC010", "main", "description", "Dummy");
       client.insertRecord(tableQuestionCriteria, "QC010", "main", "category", "Meta-Cognitive");
       client.insertRecord(tableQuestionCriteria, "QC010", "main", "type", "BENEFIT");

       // Define the data
      List<Map<String, String>> lecturersToInsert = Arrays.asList (
           new HashMap<String, String>() {{
               put("id", "Dosen4");
               put("nidn", "dummy");
               put("name", "Putra Prima Arhandi, ST., M.Kom.");
               put("place_born", "dummy");
               put("date_born", "2024-06-21T17:10:48.134Z");
               put("gender", "L");
               put("status", "dosen");
               put("address", "dummy");
               put("phone", "6123123");
               put("religion_id", "RLG001");
               put("religion_name", "Islam");
               put("study_program_id", "SP001");
               put("study_program_name", "D4 Teknik Informatika");
               put("user_id", "Dosen4");
               put("user_name", "Putra Prima Arhandi, ST., M.Kom.");
               put("created_by", "Doyatama");
           }},
           new HashMap<String, String>() {{
               put("id", "Dosen3");
               put("nidn", "dummy");
               put("name", "Milyun Ni’ma Shoumi, S.Kom., M.Kom.");
               put("place_born", "dummy");
               put("date_born", "2024-06-21T17:10:48.134Z");
               put("gender", "L");
               put("status", "dosen");
               put("address", "dummy");
               put("phone", "6123123");
               put("religion_id", "RLG001");
               put("religion_name", "Islam");
               put("study_program_id", "SP001");
               put("study_program_name", "D4 Teknik Informatika");
               put("user_id", "Dosen3");
               put("user_name", "Milyun Ni’ma Shoumi, S.Kom., M.Kom.");
               put("created_by", "Doyatama");
       }},
           new HashMap<String, String>() {{
               put("id", "Dosen1");
               put("nidn", "dummy");
               put("name", "Imam Fahrur Rozi, ST., MT");
               put("place_born", "dummy");
               put("date_born", "2024-06-21T17:10:48.134Z");
               put("gender", "L");
               put("status", "dosen");
               put("address", "dummy");
               put("phone", "6123123");
               put("religion_id", "RLG001");
               put("religion_name", "Islam");
               put("study_program_id", "SP001");
               put("study_program_name", "D4 Teknik Informatika");
               put("user_id", "Dosen1");
               put("user_name", "Imam Fahrur Rozi, ST., MT");
               put("created_by", "Doyatama");
       }},
       new HashMap<String, String>() {{
               put("id", "Dosen2");
               put("nidn", "dummy");
               put("name", "Frihandhika Permana SPd., MKom.");
               put("place_born", "dummy");
               put("date_born", "2024-06-21T17:10:48.134Z");
               put("gender", "L");
               put("status", "dosen");
               put("address", "dummy");
               put("phone", "6123123");
               put("religion_id", "RLG001");
               put("religion_name", "Islam");
               put("study_program_id", "SP001");
               put("study_program_name", "D4 Teknik Informatika");
               put("user_id", "Dosen2");
               put("user_name", "Frihandhika Permana SPd., MKom.");
               put("created_by", "Doyatama");
       }},
       new HashMap<String, String>() {{
           put("id", "Dosen5");
           put("nidn", "dummy");
           put("name", "Muhammad Shulhan Khairy, S.Kom, M.Kom");
           put("place_born", "dummy");
           put("date_born", "2024-06-21T17:10:48.134Z");
           put("gender", "L");
           put("status", "dosen");
           put("address", "dummy");
           put("phone", "6123123");
           put("religion_id", "RLG001");
           put("religion_name", "Islam");
           put("study_program_id", "SP001");
           put("study_program_name", "D4 Teknik Informatika");
           put("user_id", "MuhammadShulha");
           put("user_name", "Dosen5");
           put("created_by", "Doyatama");
       }},
       new HashMap<String, String>() {{
           put("id", "Dosen6");
           put("nidn", "dummy");
           put("name", "Gunawan Budiprasetyo, S.T., M.MT., Ph.D.");
           put("place_born", "dummy");
           put("date_born", "2024-06-21T17:10:48.134Z");
           put("gender", "L");
           put("status", "dosen");
           put("address", "dummy");
           put("phone", "6123123");
           put("religion_id", "RLG001");
           put("religion_name", "Islam");
           put("study_program_id", "SP001");
           put("study_program_name", "D4 Teknik Informatika");
           put("user_id", "Dosen6");
           put("user_name", "Gunawan Budiprasetyo, S.T., M.MT., Ph.D.");
           put("created_by", "Doyatama");
       }},
       new HashMap<String, String>() {{
           put("id", "Dosen7");
           put("nidn", "dummy");
           put("name", "Banni Satria Andoko, S. Kom., M.MSI");
           put("place_born", "dummy");
           put("date_born", "2024-06-21T17:10:48.134Z");
           put("gender", "L");
           put("status", "dosen");
           put("address", "dummy");
           put("phone", "6123123");
           put("religion_id", "RLG001");
           put("religion_name", "Islam");
           put("study_program_id", "SP001");
           put("study_program_name", "D4 Teknik Informatika");
           put("user_id", "Dosen7");
           put("user_name", "Banni Satria Andoko, S. Kom., M.MSI");
           put("created_by", "Doyatama");
       }},
       new HashMap<String, String>() {{
           put("id", "Dosen8");
           put("nidn", "dummy");
           put("name", "Priska Choirina, S.S.T., M.Tr.T");
           put("place_born", "dummy");
           put("date_born", "2024-06-21T17:10:48.134Z");
           put("gender", "L");
           put("status", "dosen");
           put("address", "dummy");
           put("phone", "6123123");
           put("religion_id", "RLG001");
           put("religion_name", "Islam");
           put("study_program_id", "SP001");
           put("study_program_name", "D4 Teknik Informatika");
           put("user_id", "Dosen8");
           put("user_name", "Priska Choirina, S.S.T., M.Tr.T");
           put("created_by", "Doyatama");
       }},
      new HashMap<String, String>() {{
          put("id", "Dosen10");
          put("nidn", "dummy");
          put("name", "Irsyad Arif Mashudi, S.Kom., M.Kom.");
          put("place_born", "dummy");
          put("date_born", "2024-06-21T17:10:48.134Z");
          put("gender", "L");
          put("status", "dosen");
          put("address", "dummy");
          put("phone", "6123123");
          put("religion_id", "RLG001");
          put("religion_name", "Islam");
          put("study_program_id", "SP001");
          put("study_program_name", "D4 Teknik Informatika");
          put("user_id", "Dosen10");
          put("user_name", "IrsyadArief");
          put("created_by", "Doyatama");

      }},
    new HashMap<String, String>() {{
          put("id", "Dosen11");
          put("nidn", "dummy");
          put("name", "Inggrid Yanuar Risca Pratiwi, S.Kom., M.Kom.");
          put("place_born", "dummy");
          put("date_born", "2024-06-21T17:10:48.134Z");
          put("gender", "L");
          put("status", "dosen");
          put("address", "dummy");
          put("phone", "6123123");
          put("religion_id", "RLG001");
          put("religion_name", "Islam");
          put("study_program_id", "SP001");
          put("study_program_name", "D4 Teknik Informatika");
          put("user_id", "Dosen11");
          put("user_name", "InggridYanuar");
          put("created_by", "Doyatama");
        }}
      );

//       // Loop over the data and insert each lecturer
      for (Map<String, String> lecturer : lecturersToInsert) {
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "id", lecturer.get("id"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "nidn", lecturer.get("nidn"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "name", lecturer.get("name"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "place_born", lecturer.get("place_born"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "date_born", lecturer.get("date_born"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "gender", lecturer.get("gender"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "status", lecturer.get("status"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "address", lecturer.get("address"));
          client.insertRecord(tableLecture, lecturer.get("id"), "main", "phone", lecturer.get("phone"));
          client.insertRecord(tableLecture, lecturer.get("id"), "religion", "id", lecturer.get("religion_id"));
          client.insertRecord(tableLecture, lecturer.get("id"), "religion", "name", lecturer.get("religion_name"));
          client.insertRecord(tableLecture, lecturer.get("id"), "study_program", "id", lecturer.get("study_program_id"));
          client.insertRecord(tableLecture, lecturer.get("id"), "study_program", "name", lecturer.get("study_program_name"));
          client.insertRecord(tableLecture, lecturer.get("id"), "user", "id", lecturer.get("user_id"));
          client.insertRecord(tableLecture, lecturer.get("id"), "user", "name", lecturer.get("user_name"));
          client.insertRecord(tableLecture, lecturer.get("id"), "detail", "created_by", lecturer.get("created_by"));
      }
       
        // Learning Media
        // Software
       client.insertRecord(tableLearningMedia, "LM001", "main", "id", "LM001");
       client.insertRecord(tableLearningMedia, "LM001", "main", "name", "Netbeans");
       client.insertRecord(tableLearningMedia, "LM001", "main", "description", "ini deskripsi netbeans");
       client.insertRecord(tableLearningMedia, "LM001", "main", "type", "1");
       client.insertRecord(tableLearningMedia, "LM001", "detail", "created_by", "Doyatama");
//
//        // Hardware
       client.insertRecord(tableLearningMedia, "LM002", "main", "id", "LM002");
       client.insertRecord(tableLearningMedia, "LM002", "main", "name", "PC / Komputer");
       client.insertRecord(tableLearningMedia, "LM002", "main", "description", "ini deskripsi pc / komputer");
       client.insertRecord(tableLearningMedia, "LM002", "main", "type", "2");
       client.insertRecord(tableLearningMedia, "LM002", "detail", "created_by", "Doyatama");
//
//        // RPS
       client.insertRecord(tableRPS, "RPS001", "main", "idRps", "RPS001");
       client.insertRecord(tableRPS, "RPS001", "main", "nameRps", "RPS 1");
       client.insertRecord(tableRPS, "RPS001", "main", "sks", "3");
       client.insertRecord(tableRPS, "RPS001", "main", "semester", "3");
       client.insertRecord(tableRPS, "RPS001", "main", "cplProdi", "D4 - Teknik Informatika");
       client.insertRecord(tableRPS, "RPS001", "main", "cplMk", "Pemrograman Berbasis Object");
       client.insertRecord(tableRPS, "RPS001", "studyProgram", "id", "SP001");
       client.insertRecord(tableRPS, "RPS001", "studyProgram", "name", "D4 Teknik Informatika");
       client.insertRecord(tableRPS, "RPS001", "subject", "id", "SB001");
       client.insertRecord(tableRPS, "RPS001", "subject", "name", "Pemrograman Berbasis Object");
       client.insertRecord(tableRPS, "RPS001", "developerLecturer", "id", "dosen6");
       client.insertRecord(tableRPS, "RPS001", "developerLecturer", "name", "Gunawan Budiprasetyo, S.T., M.MT., Ph.D.");
       client.insertRecord(tableRPS, "RPS001", "coordinatorLecturer", "id", "Dosen10");
       client.insertRecord(tableRPS, "RPS001", "coordinatorLecturer", "name", "Irsyad Arif Mashudi, S.Kom., M.Kom.");
       client.insertRecord(tableRPS, "RPS001", "instructorLecturer", "id", "Dosen11");
       client.insertRecord(tableRPS, "RPS001", "instructorLecturer", "name", "Inggrid Yanuar Risca Pratiwi, S.Kom., M.Kom.");
       client.insertRecord(tableRPS, "RPS001", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPS, "RPS001", "detail", "created_at", instant.toString());

       client.insertRecord(tableRPS, "RPS002", "main", "idRps", "RPS002");
       client.insertRecord(tableRPS, "RPS002", "main", "nameRps", "RPS 2");
       client.insertRecord(tableRPS, "RPS002", "main", "sks", "3");
       client.insertRecord(tableRPS, "RPS002", "main", "semester", "3");
       client.insertRecord(tableRPS, "RPS002", "main", "cplProdi", "D4 - Teknik Informatika");
       client.insertRecord(tableRPS, "RPS002", "main", "cplMk", "Pemrograman Berbasis Object");
       client.insertRecord(tableRPS, "RPS002", "studyProgram", "id", "SP001");
       client.insertRecord(tableRPS, "RPS002", "studyProgram", "name", "D4 Teknik Informatika");
       client.insertRecord(tableRPS, "RPS002", "subject", "id", "SB001");
       client.insertRecord(tableRPS, "RPS002", "subject", "name", "Pemrograman Berbasis Object");
       client.insertRecord(tableRPS, "RPS002", "developerLecturer", "id", "dosen6");
       client.insertRecord(tableRPS, "RPS002", "developerLecturer", "name", "Gunawan Budiprasetyo, S.T., M.MT., Ph.D.");
       client.insertRecord(tableRPS, "RPS001", "coordinatorLecturer", "id", "Dosen10");
       client.insertRecord(tableRPS, "RPS001", "coordinatorLecturer", "name", "Irsyad Arif Mashudi, S.Kom., M.Kom.");
       client.insertRecord(tableRPS, "RPS001", "instructorLecturer", "id", "Dosen11");
       client.insertRecord(tableRPS, "RPS001", "instructorLecturer", "name", "Inggrid Yanuar Risca Pratiwi, S.Kom., M.Kom.");
       client.insertRecord(tableRPS, "RPS002", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPS, "RPS002", "detail", "created_at", instant.toString());
//
//        // RPS Detail
       client.insertRecord(tableRPSDetail, "RPS001-D001", "main", "id", "RPS001-D001");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "main", "week", "1");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "rps", "idRps", "RPS001");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "rps", "nameRps", "RPS 1");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "main", "sub_cp_mk", "Dummy Sub CP MK");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "main", "learning_materials", "Operation");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "form_learning", "id", "BP001");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "form_learning", "name", "Daring");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "main", "weight", "3");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPSDetail, "RPS001-D001", "detail", "created_at", instant.toString());

       client.insertRecord(tableRPSDetail, "RPS001-D002", "main", "id", "RPS001-D002");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "main", "week", "2");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "rps", "idRps", "RPS001");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "rps", "nameRps", "RPS 1");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "main", "sub_cp_mk", "Dummy Sub CP MK");
         client.insertRecord(tableRPSDetail, "RPS001-D002", "main", "learning_materials", "Operation");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "form_learning", "id", "BP001");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "form_learning", "name", "Daring");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "main", "weight", "3");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPSDetail, "RPS001-D002", "detail", "created_at", instant.toString());

       client.insertRecord(tableRPSDetail, "RPS002-D001", "main", "id", "RPS002-D001");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "main", "week", "1");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "rps", "idRps", "RPS002");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "rps", "nameRps", "RPS 2");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "main", "sub_cp_mk", "Dummy Sub CP MK");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "main", "learning_materials", "Operation");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "form_learning", "id", "BP001");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "form_learning", "name", "Daring");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "main", "weight", "3");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPSDetail, "RPS002-D001", "detail", "created_at", instant.toString());

       client.insertRecord(tableRPSDetail, "RPS002-D002", "main", "id", "RPS002-D002");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "main", "week", "2");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "rps", "idRps", "RPS002");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "rps", "nameRps", "RPS 2");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "main", "sub_cp_mk", "Dummy Sub CP MK");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "main", "learning_materials", "Operation");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "form_learning", "id", "BP001");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "form_learning", "name", "Daring");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "main", "weight", "3");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "detail", "created_by", "Doyatama");
       client.insertRecord(tableRPSDetail, "RPS002-D002", "detail", "created_at", instant.toString());

       // Seeder for Questions and Answers

// Question 1
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "title", "Which of the following are legal entry point methods that can be run from the command\r\n" + //
        "line? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "description", "Pertanyaan tentang entry point methods Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q001", "main", "examType2", "QUIZ");

// Answer 1 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "main", "idAnswer", "RPS001-D001-Q001-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "main", "title", "A. private static void main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A001", "main", "is_right", "false");

// Answer 2 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "main", "idAnswer", "RPS001-D001-Q001-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "main", "title", "B. public static final main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A002", "main", "is_right", "false");

// Answer 3 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "main", "idAnswer", "RPS001-D001-Q001-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "main", "title", "C. public void main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A003", "main", "is_right", "false");

// Answer 4 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "main", "idAnswer", "RPS001-D001-Q001-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "main", "title", "D. public static final void main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A004", "main", "is_right", "true");

// Answer 5 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "main", "idAnswer", "RPS001-D001-Q001-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "main", "title", "E. public static void main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A005", "main", "is_right", "true");

// Answer 6 for Q001
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "main", "idAnswer", "RPS001-D001-Q001-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "question", "idQuestion", "RPS001-D001-Q001");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "main", "title", "F. public static main(String[] args)");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q001-A006", "main", "is_right", "false");

// Question 2
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "title", "Which of the following are valid Java identifiers? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "description", "Pertanyaan tentang Java identifiers");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q002", "main", "examType2", "QUIZ");

// Answer 1 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "main", "idAnswer", "RPS001-D001-Q002-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "main", "title", "A. _");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A001", "main", "is_right", "true");

// Answer 2 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "main", "idAnswer", "RPS001-D001-Q002-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "main", "title", "B. _helloWorld$");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A002", "main", "is_right", "true");

// Answer 3 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "main", "idAnswer", "RPS001-D001-Q002-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "main", "title", "C. true");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A003", "main", "is_right", "false");

// Answer 4 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "main", "idAnswer", "RPS001-D001-Q002-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "main", "title", "D. java.lang");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A004", "main", "is_right", "false");

// Answer 5 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "main", "idAnswer", "RPS001-D001-Q002-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "main", "title", "E. Public");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A005", "main", "is_right", "true");

// Answer 6 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "main", "idAnswer", "RPS001-D001-Q002-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "main", "title", "F. 1980 s");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A006", "main", "is_right", "false");

// Answer 7 for Q002
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "main", "idAnswer", "RPS001-D001-Q002-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "question", "idQuestion", "RPS001-D001-Q002");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "main", "title", "G. _Q2");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q002-A007", "main", "is_right", "true");

// Question 3
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "title", "Which of the following code snippets about var compile without issue when used in a\r\n" + //
        "method? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "description", "Pertanyaan tentang 'var' keyword di Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q003", "main", "examType2", "QUIZ");

// Answer 1 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "main", "idAnswer", "RPS001-D001-Q003-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "main", "title", "A. var spring = null");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A001", "main", "is_right", "false");

// Answer 2 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "main", "idAnswer", "RPS001-D001-Q003-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "main", "title", "B. var fall = \"leaves\";");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A002", "main", "is_right", "true");

// Answer 3 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "main", "idAnswer", "RPS001-D001-Q003-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "main", "title", "C. var evening = 2; evening = null;");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A003", "main", "is_right", "false");

// Answer 4 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "main", "idAnswer", "RPS001-D001-Q003-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "main", "title", "D. var night = Integer.valueOf(3);");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A004", "main", "is_right", "true");

// Answer 5 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "main", "idAnswer", "RPS001-D001-Q003-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "main", "title", "E. var day = 1/0;");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A005", "main", "is_right", "true");

// Answer 6 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "main", "idAnswer", "RPS001-D001-Q003-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "main", "title", "F. var winter = 12, cold;");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A006", "main", "is_right", "false");

// Answer 7 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "main", "idAnswer", "RPS001-D001-Q003-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "main", "title", "G. var fall = 2, autumn = 2;");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A007", "main", "is_right", "false");

// Answer 8 for Q003
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "main", "idAnswer", "RPS001-D001-Q003-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "question", "idQuestion", "RPS001-D001-Q003");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "main", "title", "H. var morning = \"\"; morning = null;");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q003-A008", "main", "is_right", "true");


// Question 4
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "title", "Which of the following are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "description", "Pertanyaan tentang nilai default variabel Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q004", "main", "examType2", "QUIZ");

// Answer 1 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "main", "idAnswer", "RPS001-D001-Q004-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "main", "title", "A. An instance variable of type float defaults to 0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A001", "main", "is_right", "false");

// Answer 2 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "main", "idAnswer", "RPS001-D001-Q004-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "main", "title", "B. An instance variable of type char defaults to null.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A002", "main", "is_right", "false");

// Answer 3 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "main", "idAnswer", "RPS001-D001-Q004-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "main", "title", "C. A local variable of type double defaults to 0.0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A003", "main", "is_right", "false");

// Answer 4 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "main", "idAnswer", "RPS001-D001-Q004-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "main", "title", "D. A local variable of type int defaults to null.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A004", "main", "is_right", "false");

// Answer 5 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "main", "idAnswer", "RPS001-D001-Q004-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "main", "title", "E. A class variable of type String defaults to null.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A005", "main", "is_right", "true");

// Answer 6 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "main", "idAnswer", "RPS001-D001-Q004-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "main", "title", "F. A class variable of type String defaults to the empty string \"\".");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A006", "main", "is_right", "false");

// Answer 7 for Q004
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "main", "idAnswer", "RPS001-D001-Q004-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "question", "idQuestion", "RPS001-D001-Q004");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "main", "title", "G. None of the above.");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q004-A007", "main", "is_right", "false");


// Question 5
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "title", "Which of the following statements about garbage collection are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "description", "Pertanyaan tentang garbage collection");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q005", "main", "examType2", "QUIZ");

// Answer 1 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "main", "idAnswer", "RPS001-D001-Q005-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "main", "title", "A. Calling System.gc() is guaranteed to free up memory by destroying objects eligible\r\n" + //
        "for garbage collection.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A001", "main", "is_right", "false");

// Answer 2 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "main", "idAnswer", "RPS001-D001-Q005-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "main", "title", "B. Garbage collection runs on a set schedule.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A002", "main", "is_right", "false");

// Answer 3 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "main", "idAnswer", "RPS001-D001-Q005-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "main", "title", "C. Garbage collection allows the JVM to reclaim memory for other objects.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A003", "main", "is_right", "true");

// Answer 4 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "main", "idAnswer", "RPS001-D001-Q005-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "main", "title", "D. Garbage collection runs when your program has used up half the available memory.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A004", "main", "is_right", "false");

// Answer 5 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "main", "idAnswer", "RPS001-D001-Q005-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "main", "title", "E. An object may be eligible for garbage collection but never removed from the heap.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A005", "main", "is_right", "true");

// Answer 6 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "main", "idAnswer", "RPS001-D001-Q005-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "main", "title", "F. An object is eligible for garbage collection once no references to it are accessible in the\r\n" + //
        "program.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A006", "main", "is_right", "true");

// Answer 7 for Q005
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "main", "idAnswer", "RPS001-D001-Q005-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "question", "idQuestion", "RPS001-D001-Q005");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "main", "title", "G. Marking a variable final means its associated object will never be garbage collected.");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q005-A007", "main", "is_right", "false");

// Question 6
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "title", "Which of the following statements about var are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "description", "Pertanyaan tentang 'var' keyword");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q006", "main", "examType2", "QUIZ");

// Answer 1 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "main", "idAnswer", "RPS001-D001-Q006-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "main", "title", "A. A var can be used as a constructor parameter.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A001", "main", "is_right", "false");

// Answer 2 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "main", "idAnswer", "RPS001-D001-Q006-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "main", "title", "B. The type of a var is known at compile time.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A002", "main", "is_right", "true");

// Answer 3 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "main", "idAnswer", "RPS001-D001-Q006-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "main", "title", "C. A var cannot be used as an instance variable.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A003", "main", "is_right", "true");

// Answer 4 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "main", "idAnswer", "RPS001-D001-Q006-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "main", "title", "D. A var can be used in a multiple variable assignment statement.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A004", "main", "is_right", "false");

// Answer 5 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "main", "idAnswer", "RPS001-D001-Q006-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "main", "title", "E. The value of a var cannot change at runtime.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A005", "main", "is_right", "false");

// Answer 6 for Q006
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "main", "idAnswer", "RPS001-D001-Q006-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "question", "idQuestion", "RPS001-D001-Q006");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "main", "title", "F. The type of a var cannot change at runtime.");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q006-A006", "main", "is_right", "true");

// Question 8
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "title", "Which answer options represent the order in which the following statements can be assembled\r\n" + //
        "into a program that will compile successfully? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "description", "Pertanyaan tentang urutan kompilasi program Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "file_path", "/images/questions/RPS001-D001-Q007.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q007", "main", "examType2", "QUIZ");

// Answer 1 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "main", "idAnswer", "RPS001-D001-Q007-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "main", "title", "A. X, Y, Z");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A001", "main", "is_right", "false");

// Answer 2 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "main", "idAnswer", "RPS001-D001-Q007-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "main", "title", "B. Y, Z, X");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A002", "main", "is_right", "false");

// Answer 3 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "main", "idAnswer", "RPS001-D001-Q007-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "main", "title", "C. Z, Y, X");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A003", "main", "is_right", "true");

// Answer 4 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "main", "idAnswer", "RPS001-D001-Q007-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "main", "title", "D. Y, X");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A004", "main", "is_right", "true");

// Answer 5 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "main", "idAnswer", "RPS001-D001-Q007-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "main", "title", "E. Z, X");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A005", "main", "is_right", "true");

// Answer 6 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "main", "idAnswer", "RPS001-D001-Q007-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "main", "title", "F. X, Z");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A006", "main", "is_right", "false");

// Answer 7 for Q008
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "main", "idAnswer", "RPS001-D001-Q007-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "question", "idQuestion", "RPS001-D001-Q007");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "main", "title", "G. None of the above");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q007-A007", "main", "is_right", "false");

// Question 9
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "title", "Which of the following are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "description", "Pertanyaan tentang konsep Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "file_path", "/images/questions/RPS001-D001-Q008.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q008", "main", "examType2", "QUIZ");

// Answer 1 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "main", "idAnswer", "RPS001-D001-Q008-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "main", "title", "A. Bunny is a class.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A001", "main", "is_right", "true");

// Answer 2 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "main", "idAnswer", "RPS001-D001-Q008-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "main", "title", "B. bun is a class.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A002", "main", "is_right", "false");

// Answer 3 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "main", "idAnswer", "RPS001-D001-Q008-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "main", "title", "C. main is a class.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A003", "main", "is_right", "false");

// Answer 4 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "main", "idAnswer", "RPS001-D001-Q008-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "main", "title", "D. Bunny is a reference to an object.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A004", "main", "is_right", "true");

// Answer 5 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "main", "idAnswer", "RPS001-D001-Q008-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "main", "title", "E. bun is a reference to an object.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A005", "main", "is_right", "true");

// Answer 6 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "main", "idAnswer", "RPS001-D001-Q008-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "main", "title", "F. main is a reference to an object.");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A006", "main", "is_right", "false");

// Answer 7 for Q009
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "main", "idAnswer", "RPS001-D001-Q008-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "question", "idQuestion", "RPS001-D001-Q008");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "main", "title", "G. The main() method doesn't run because the parameter name is incorrect");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q008-A007", "main", "is_right", "false");


// Question 10
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "idQuestion", "RPS001-D001-Q009");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "title", "Which statements about the following program are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "description", "Pertanyaan tentang garbage collection dan program Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "file_path", "/images/questions/RPS001-D001-Q009.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q009", "main", "examType2", "QUIZ");

// Answer 1 for Q010
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "main", "idAnswer", "RPS001-D001-Q009-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "question", "idQuestion", "RPS001-D001-Q009");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "main", "title", "A. The object created on line 9 is eligible for garbage collection after line 13.");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A001", "main", "is_right", "true");

// Answer 2 for Q010
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "main", "idAnswer", "RPS001-D001-Q009-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "question", "idQuestion", "RPS001-D001-Q009");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "main", "title", "B. The object created on line 9 is eligible for garbage collection after line 14.");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A002", "main", "is_right", "false");

// Answer 3 for Q010
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "main", "idAnswer", "RPS001-D001-Q009-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "question", "idQuestion", "RPS001-D001-Q009");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "main", "title", "C. The object created on line 10 is eligible for garbage collection after line 12.");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A003", "main", "is_right", "false");

// Answer 4 for Q010
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "main", "idAnswer", "RPS001-D001-Q009-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "question", "idQuestion", "RPS001-D001-Q009");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "main", "title", "D. The object created on line 10 is eligible for garbage collection after line 13.");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q009-A004", "main", "is_right", "true");

// Question 12
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "title", "Assuming the following class compiles, how many variables defined in the class or method\r\n" + //
        "are in scope on the line marked on line 14?");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "description", "Pertanyaan tentang scope variabel");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "file_path", "/images/questions/RPS001-D001-Q010.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q010", "main", "examType2", "QUIZ");

// Answer 1 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "main", "idAnswer", "RPS001-D001-Q010-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "main", "title", "A. 2");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A001", "main", "is_right", "false");

// Answer 2 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "main", "idAnswer", "RPS001-D001-Q010-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "main", "title", "B. 3");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A002", "main", "is_right", "false");

// Answer 3 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "main", "idAnswer", "RPS001-D001-Q010-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "main", "title", "C. 4");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A003", "main", "is_right", "false");

// Answer 4 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "main", "idAnswer", "RPS001-D001-Q010-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "main", "title", "D. 5");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A004", "main", "is_right", "false");

// Answer 5 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "main", "idAnswer", "RPS001-D001-Q010-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "main", "title", "E. 6");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A005", "main", "is_right", "false");

// Answer 6 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "main", "idAnswer", "RPS001-D001-Q010-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "main", "title", "F. 7");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A006", "main", "is_right", "true");

// Answer 7 for Q012
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "main", "idAnswer", "RPS001-D001-Q010-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "question", "idQuestion", "RPS001-D001-Q010");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "main", "title", "G. None of the above");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q010-A007", "main", "is_right", "false");


// Question 13
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "title", "Which are true about this code? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "description", "Pertanyaan tentang output kode");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "file_path", "/images/questions/RPS001-D001-Q011.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q011", "main", "examType2", "QUIZ");

// Answer 1 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "main", "idAnswer", "RPS001-D001-Q011-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "main", "title", "A. The output includes: # forks = 0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A001", "main", "is_right", "false");

// Answer 2 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "main", "idAnswer", "RPS001-D001-Q011-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "main", "title", "B. The output includes: # knives = 0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A002", "main", "is_right", "false");

// Answer 3 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "main", "idAnswer", "RPS001-D001-Q011-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "main", "title", "C. The output includes: # cups = 0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A003", "main", "is_right", "true");

// Answer 4 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "main", "idAnswer", "RPS001-D001-Q011-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "main", "title", "D. The output includes a blank line.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A004", "main", "is_right", "false");

// Answer 5 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "main", "idAnswer", "RPS001-D001-Q011-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "main", "title", "E. The output includes one or more lines that begin with whitespace.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A005", "main", "is_right", "true");

// Answer 6 for Q013
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "main", "idAnswer", "RPS001-D001-Q011-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "question", "idQuestion", "RPS001-D001-Q011");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "main", "title", "F. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q011-A006", "main", "is_right", "false");


// Question 14
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "title", "Which of the following expressions, when inserted independently into the blank line, allow\r\n" + //
        "the code to compile? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "description", "Pertanyaan tentang kompilasi kode Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "file_path", "/images/questions/RPS001-D001-Q012.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q012", "main", "examType2", "QUIZ");

// Answer 1 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "main", "idAnswer", "RPS001-D001-Q012-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "main", "title", "A. 3_1");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A001", "main", "is_right", "true");

// Answer 2 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "main", "idAnswer", "RPS001-D001-Q012-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "main", "title", "B. 1_329.0");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A002", "main", "is_right", "false");

// Answer 3 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "main", "idAnswer", "RPS001-D001-Q012-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "main", "title", "C. 3_13.0_");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A003", "main", "is_right", "false");

// Answer 4 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "main", "idAnswer", "RPS001-D001-Q012-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "main", "title", "D. 5_291._2");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A004", "main", "is_right", "false");

// Answer 5 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "main", "idAnswer", "RPS001-D001-Q012-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "main", "title", "E. 2_234.0_0");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A005", "main", "is_right", "true");

// Answer 6 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "main", "idAnswer", "RPS001-D001-Q012-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "main", "title", "F. 9_6");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A006", "main", "is_right", "true");

// Answer 7 for Q014
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "main", "idAnswer", "RPS001-D001-Q012-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "question", "idQuestion", "RPS001-D001-Q012");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "main", "title", "G. 1350");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q012-A007", "main", "is_right", "false");


// Question 15
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "title", "Given the following two class files, what is the maximum number of imports that can be\r\n" + //
        "removed and have the code still compile?");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "description", "Pertanyaan tentang import di Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "file_path", "/images/questions/RPS001-D001-Q013.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q013", "main", "examType2", "QUIZ");

// Answer 1 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "main", "idAnswer", "RPS001-D001-Q013-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "main", "title", "A. 0");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A001", "main", "is_right", "false");

// Answer 2 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "main", "idAnswer", "RPS001-D001-Q013-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "main", "title", "B. 1");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A002", "main", "is_right", "false");

// Answer 3 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "main", "idAnswer", "RPS001-D001-Q013-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "main", "title", "C. 2");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A003", "main", "is_right", "false");

// Answer 4 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "main", "idAnswer", "RPS001-D001-Q013-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "main", "title", "D. 3");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A004", "main", "is_right", "false");

// Answer 5 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "main", "idAnswer", "RPS001-D001-Q013-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "main", "title", "E. 4");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A005", "main", "is_right", "true");

// Answer 6 for Q015
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "main", "idAnswer", "RPS001-D001-Q013-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "question", "idQuestion", "RPS001-D001-Q013");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "main", "title", "F. Does not compile");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q013-A006", "main", "is_right", "false");

// Question 16
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "title", "Which statements about the following class are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "description", "Pertanyaan tentang kelas Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "file_path", "/images/questions/RPS001-D001-Q014.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q014", "main", "examType2", "QUIZ");

// Answer 1 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "main", "idAnswer", "RPS001-D001-Q014-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "main", "title", "A. Line 2 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A001", "main", "is_right", "true");

// Answer 2 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "main", "idAnswer", "RPS001-D001-Q014-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "main", "title", "B. Line 3 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A002", "main", "is_right", "false");

// Answer 3 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "main", "idAnswer", "RPS001-D001-Q014-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "main", "title", "C. Line 4 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A003", "main", "is_right", "true");

// Answer 4 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "main", "idAnswer", "RPS001-D001-Q014-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "main", "title", "D. Line 7 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A004", "main", "is_right", "true");

// Answer 5 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "main", "idAnswer", "RPS001-D001-Q014-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "main", "title", "E. The code prints 0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A005", "main", "is_right", "false");

// Answer 6 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "main", "idAnswer", "RPS001-D001-Q014-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "main", "title", "F. The code prints 2.0.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A006", "main", "is_right", "false");

// Answer 7 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "main", "idAnswer", "RPS001-D001-Q014-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "main", "title", "G. The code prints 2.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A007", "main", "is_right", "false");

// Answer 8 for Q016
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "main", "idAnswer", "RPS001-D001-Q014-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "question", "idQuestion", "RPS001-D001-Q014");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "main", "title", "H. The code prints 3.");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q014-A008", "main", "is_right", "false");


// Question 17
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "title", "Given the following classes, which of the following snippets can independently be inserted in\r\n" + //
        "place of INSERT IMPORTS HERE and have the code compile? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "description", "Pertanyaan tentang import di Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "file_path", "/images/questions/RPS001-D001-Q015.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q015", "main", "examType2", "QUIZ");

// Answer 1 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "main", "idAnswer", "RPS001-D001-Q015-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "main", "title", "A. import aquarium.*;");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A001", "main", "is_right", "true");

// Answer 2 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "main", "idAnswer", "RPS001-D001-Q015-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "main", "title", "B. import aquarium.Water;\r\n" + //
        "import aquarium.jellies.*;");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A002", "main", "is_right", "true");

// Answer 3 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "main", "idAnswer", "RPS001-D001-Q015-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "main", "title", "C. import aquarium.*;\r\n" + //
        "import aquarium.jellies.Water;");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A003", "main", "is_right", "true");

// Answer 4 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "main", "idAnswer", "RPS001-D001-Q015-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "main", "title", "D. import aquarium.*;\r\n" + //
        "import aquarium.jellies.*;");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A004", "main", "is_right", "false");

// Answer 5 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "main", "idAnswer", "RPS001-D001-Q015-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "main", "title", "E. import aquarium.Water;\r\n" + //
        "import aquarium.jellies.Water;");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A005", "main", "is_right", "false");

// Answer 6 for Q017
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "main", "idAnswer", "RPS001-D001-Q015-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "question", "idQuestion", "RPS001-D001-Q015");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "main", "title", "F. None of these imports can make the code compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q015-A006", "main", "is_right", "true");


// Question 18
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "title", "Which of the following statements about the code snippet are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "description", "Pertanyaan tentang kode snippet");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "file_path", "/images/questions/RPS001-D001-Q016.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q016", "main", "examType2", "QUIZ");

// Answer 1 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "main", "idAnswer", "RPS001-D001-Q016-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "main", "title", "A. Line 3 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A001", "main", "is_right", "true");

// Answer 2 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "main", "idAnswer", "RPS001-D001-Q016-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "main", "title", "B. Line 4 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A002", "main", "is_right", "true");

// Answer 3 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "main", "idAnswer", "RPS001-D001-Q016-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "main", "title", "C. Line 5 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A003", "main", "is_right", "false");

// Answer 4 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "main", "idAnswer", "RPS001-D001-Q016-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "main", "title", "D. Line 6 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A004", "main", "is_right", "true");

// Answer 5 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "main", "idAnswer", "RPS001-D001-Q016-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "main", "title", "E. Line 7 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A005", "main", "is_right", "true");

// Answer 6 for Q018
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "main", "idAnswer", "RPS001-D001-Q016-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "question", "idQuestion", "RPS001-D001-Q016");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "main", "title", "F. Line 8 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q016-A006", "main", "is_right", "false");


// Question 19
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "title", "Which are true about this code? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "description", "Pertanyaan tentang output kode");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "file_path", "/images/questions/RPS001-D001-Q017.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q017", "main", "examType2", "QUIZ");

// Answer 1 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "main", "idAnswer", "RPS001-D001-Q017-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "main", "title", "A. It outputs two lines.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A001", "main", "is_right", "true");

// Answer 2 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "main", "idAnswer", "RPS001-D001-Q017-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "main", "title", "B. It outputs three lines.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A002", "main", "is_right", "false");

// Answer 3 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "main", "idAnswer", "RPS001-D001-Q017-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "main", "title", "C. It outputs four lines.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A003", "main", "is_right", "false");

// Answer 4 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "main", "idAnswer", "RPS001-D001-Q017-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "main", "title", "D. There is one line with trailing whitespace.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A004", "main", "is_right", "true");

// Answer 5 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "main", "idAnswer", "RPS001-D001-Q017-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "main", "title", "E. There are two lines with trailing whitespace.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A005", "main", "is_right", "false");

// Answer 6 for Q019
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "main", "idAnswer", "RPS001-D001-Q017-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "question", "idQuestion", "RPS001-D001-Q017");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "main", "title", "F. If we indented each line five characters, it would change the output.");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q017-A006", "main", "is_right", "false");


// Question 20
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "title", "What lines are printed by the following program? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "description", "Pertanyaan tentang output program");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "file_path", "/images/questions/RPS001-D001-Q018.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q018", "main", "examType2", "QUIZ");

// Answer 1 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "main", "idAnswer", "RPS001-D001-Q018-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "main", "title", "A. Line 8 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A001", "main", "is_right", "false");

// Answer 2 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "main", "idAnswer", "RPS001-D001-Q018-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "main", "title", "B. Line 9 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A002", "main", "is_right", "false");

// Answer 3 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "main", "idAnswer", "RPS001-D001-Q018-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "main", "title", "C. Empty =");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A003", "main", "is_right", "false");

// Answer 4 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "main", "idAnswer", "RPS001-D001-Q018-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "main", "title", "D. Empty = false");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A004", "main", "is_right", "true");

// Answer 5 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "main", "idAnswer", "RPS001-D001-Q018-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "main", "title", "E. Brand =");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A005", "main", "is_right", "false");

// Answer 6 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "main", "idAnswer", "RPS001-D001-Q018-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "main", "title", "F. Brand = null");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A006", "main", "is_right", "true");

// Answer 7 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "main", "idAnswer", "RPS001-D001-Q018-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "main", "title", "G. Code = 0.0");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A007", "main", "is_right", "true");

// Answer 8 for Q020
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "main", "idAnswer", "RPS001-D001-Q018-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "question", "idQuestion", "RPS001-D001-Q018");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "main", "title", "H. Code = 0f");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q018-A008", "main", "is_right", "false");


// Question 21
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "title", "Which are true about the following code? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "description", "Pertanyaan tentang kode Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "file_path", "/images/questions/RPS001-D001-Q019.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q019", "main", "examType2", "QUIZ");

// Answer 1 for Q021
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "main", "idAnswer", "RPS001-D001-Q019-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "question", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "main", "title", "A. The output is 100.");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A001", "main", "is_right", "true");

// Answer 2 for Q021
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "main", "idAnswer", "RPS001-D001-Q019-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "question", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "main", "title", "B. The output is 200.");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A002", "main", "is_right", "false");

// Answer 3 for Q021
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "main", "idAnswer", "RPS001-D001-Q019-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "question", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "main", "title", "C. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A003", "main", "is_right", "false");

// Answer 4 for Q021
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "main", "idAnswer", "RPS001-D001-Q019-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "question", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "main", "title", "D. numl is a primitive.");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A004", "main", "is_right", "true");

// Answer 5 for Q021
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "main", "idAnswer", "RPS001-D001-Q019-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "question", "idQuestion", "RPS001-D001-Q019");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "main", "title", "E. num2 is a primitive.");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q019-A005", "main", "is_right", "false");


// Question 22
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "title", "Which statements about the following class are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "description", "Pertanyaan tentang kelas Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "file_path", "/images/questions/RPS001-D001-Q020.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q020", "main", "examType2", "QUIZ");

// Answer 1 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "main", "idAnswer", "RPS001-D001-Q020-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "main", "title", "A. It prints Q1=blue.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A001", "main", "is_right", "false");

// Answer 2 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "main", "idAnswer", "RPS001-D001-Q020-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "main", "title", "B. It prints Q2=1200.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A002", "main", "is_right", "false");

// Answer 3 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "main", "idAnswer", "RPS001-D001-Q020-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "main", "title", "C. It prints P1=null.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A003", "main", "is_right", "true");

// Answer 4 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "main", "idAnswer", "RPS001-D001-Q020-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "main", "title", "D. It prints P2=1400.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A004", "main", "is_right", "false");

// Answer 5 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "main", "idAnswer", "RPS001-D001-Q020-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "main", "title", "E. Line 4 does not compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A005", "main", "is_right", "false");

// Answer 6 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "main", "idAnswer", "RPS001-D001-Q020-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "main", "title", "F. Line 12 does not compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A006", "main", "is_right", "false");

// Answer 7 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "main", "idAnswer", "RPS001-D001-Q020-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "main", "title", "G. Line 13 does not compile.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A007", "main", "is_right", "false");

// Answer 8 for Q022
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "main", "idAnswer", "RPS001-D001-Q020-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "question", "idQuestion", "RPS001-D001-Q020");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "main", "title", "H. None of the above.");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q020-A008", "main", "is_right", "false");


// Question 23
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "title", "What is the output of executing the following class?");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "description", "Pertanyaan tentang output program Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "file_path", "/images/questions/RPS001-D001-Q021.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q021", "main", "examType2", "QUIZ");

// Answer 1 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "main", "idAnswer", "RPS001-D001-Q021-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "main", "title", "A. 7-0-2-1-");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A001", "main", "is_right", "false");

// Answer 2 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "main", "idAnswer", "RPS001-D001-Q021-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "main", "title", "B. 7-0-1-");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A002", "main", "is_right", "false");

// Answer 3 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "main", "idAnswer", "RPS001-D001-Q021-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "main", "title", "C. 0-7-2-1-");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A003", "main", "is_right", "false");

// Answer 4 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "main", "idAnswer", "RPS001-D001-Q021-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "main", "title", "D. 7-0-2-4-");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A004", "main", "is_right", "true");

// Answer 5 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "main", "idAnswer", "RPS001-D001-Q021-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "main", "title", "E. 0-7-1-");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A005", "main", "is_right", "false");

// Answer 6 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "main", "idAnswer", "RPS001-D001-Q021-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "main", "title", "F. The class does not compile because of line 3.");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A006", "main", "is_right", "false");

// Answer 7 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "main", "idAnswer", "RPS001-D001-Q021-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "main", "title", "G. The class does not compile because of line 4.");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A007", "main", "is_right", "false");

// Answer 8 for Q023
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "main", "idAnswer", "RPS001-D001-Q021-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "question", "idQuestion", "RPS001-D001-Q021");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "main", "title", "H. None of the above");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q021-A008", "main", "is_right", "false");


// Question 24
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "title", "Given the following class, which of the following lines of code can independently replace\r\n" + //
        "INSERT CODE HERE to make the code compile? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "description", "Pertanyaan tentang kompilasi kode Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "file_path", "/images/questions/RPS001-D001-Q022.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q022", "main", "examType2", "QUIZ");

// Answer 1 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "main", "idAnswer", "RPS001-D001-Q022-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "main", "title", "A. int Amount = 0b11;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A001", "main", "is_right", "false");

// Answer 2 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "main", "idAnswer", "RPS001-D001-Q022-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "main", "title", "B. int amount = 9L;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A002", "main", "is_right", "false");

// Answer 3 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "main", "idAnswer", "RPS001-D001-Q022-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "main", "title", "C. int amount = 0xE;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A003", "main", "is_right", "true");

// Answer 4 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "main", "idAnswer", "RPS001-D001-Q022-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "main", "title", "D. int amount = 1_2.0;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A004", "main", "is_right", "false");

// Answer 5 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "main", "idAnswer", "RPS001-D001-Q022-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "main", "title", "E. double amount = 1_0_.0;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A005", "main", "is_right", "false");

// Answer 6 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "main", "idAnswer", "RPS001-D001-Q022-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "main", "title", "F. int amount = 0b101;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A006", "main", "is_right", "true");

// Answer 7 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "main", "idAnswer", "RPS001-D001-Q022-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "main", "title", "G. double amount = 9_2.1_2;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A007", "main", "is_right", "true");

// Answer 8 for Q024
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "main", "idAnswer", "RPS001-D001-Q022-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "question", "idQuestion", "RPS001-D001-Q022");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "main", "title", "H. double amount = 1_2.0_0;");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q022-A008", "main", "is_right", "false");

// Question 25
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "rps_detail", "id", "RPS001-D001");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "title", "Which statements about the following class are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "description", "Pertanyaan tentang kelas Java");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "question_type", "IMAGE"); // Karena ada (gambar)
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "file_path", "/images/questions/RPS001-D001-Q023.png");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D001-Q023", "main", "examType2", "QUIZ");

// Answer 1 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "main", "idAnswer", "RPS001-D001-Q023-A001");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "main", "title", "A. Line 3 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A001", "main", "is_right", "true");

// Answer 2 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "main", "idAnswer", "RPS001-D001-Q023-A002");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "main", "title", "B. Line 6 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A002", "main", "is_right", "false");

// Answer 3 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "main", "idAnswer", "RPS001-D001-Q023-A003");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "main", "title", "C. Line 7 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A003", "main", "is_right", "false");

// Answer 4 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "main", "idAnswer", "RPS001-D001-Q023-A004");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "main", "title", "D. Line 10 generates a compiler error.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A004", "main", "is_right", "true");

// Answer 5 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "main", "idAnswer", "RPS001-D001-Q023-A005");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "main", "title", "E. The program prints 3 on line 10.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A005", "main", "is_right", "false");

// Answer 6 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "main", "idAnswer", "RPS001-D001-Q023-A006");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "main", "title", "F. The program prints 4 on line 10.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A006", "main", "is_right", "false");

// Answer 7 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "main", "idAnswer", "RPS001-D001-Q023-A007");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "main", "title", "G. The program prints 50.0 on line 11.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A007", "main", "is_right", "false");

// Answer 8 for Q025
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "main", "idAnswer", "RPS001-D001-Q023-A008");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "question", "idQuestion", "RPS001-D001-Q023");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "main", "title", "H. The program prints 49.0 on line 11.");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D001-Q023-A008", "main", "is_right", "false");

// Question 24
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "title", "Which of the following Java operators can be used with boolean variables? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "description", "Pertanyaan tentang operator Java untuk variabel boolean");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q024", "main", "examType2", "QUIZ");

// Answer for Q024
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "main", "idAnswer", "RPS001-D002-Q024-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "main", "title", "A. &");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "main", "idAnswer", "RPS001-D002-Q024-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "main", "title", "B. +");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "main", "idAnswer", "RPS001-D002-Q024-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "main", "title", "C. --");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "main", "idAnswer", "RPS001-D002-Q024-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "main", "title", "D. !");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "main", "idAnswer", "RPS001-D002-Q024-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "main", "title", "E. %");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "main", "idAnswer", "RPS001-D002-Q024-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "main", "title", "F. ~");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "main", "idAnswer", "RPS001-D002-Q024-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "question", "idQuestion", "RPS001-D002-Q024");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "main", "title", "G. Cast with (boolean)");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q024-A007", "main", "is_right", "true");


// Question 25
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "title", "What data type (or types) will allow the following code snippet to compile? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "description", "Pertanyaan tentang tipe data yang valid");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "file_path", "/images/questions/RPS001-D002-Q025.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q025", "main", "examType2", "QUIZ");

// Answer for Q025
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "main", "idAnswer", "RPS001-D002-Q025-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "main", "title", "A. int");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "main", "idAnswer", "RPS001-D002-Q025-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "main", "title", "B. long");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "main", "idAnswer", "RPS001-D002-Q025-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "main", "title", "C. boolean");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "main", "idAnswer", "RPS001-D002-Q025-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "main", "title", "D. double");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "main", "idAnswer", "RPS001-D002-Q025-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "main", "title", "E. short");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "main", "idAnswer", "RPS001-D002-Q025-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "question", "idQuestion", "RPS001-D002-Q025");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "main", "title", "F. byte");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q025-A006", "main", "is_right", "false");


// Question 26
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "title", "What change, when applied independently, would allow the following code snippet to compile? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "description", "Pertanyaan tentang kompilasi kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "file_path", "/images/questions/RPS001-D002-Q026.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q026", "main", "examType2", "QUIZ");

// Answer for Q026
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "main", "idAnswer", "RPS001-D002-Q026-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "main", "title", "A. No change; it compiles as is.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "main", "idAnswer", "RPS001-D002-Q026-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "main", "title", "B. Cast ear on line 4 to int.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "main", "idAnswer", "RPS001-D002-Q026-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "main", "title", "C. Change the data type of ear on line 3 to short.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A003", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "main", "idAnswer", "RPS001-D002-Q026-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "main", "title", "D. Cast 2 * ear on line 4 to int.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "main", "idAnswer", "RPS001-D002-Q026-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "main", "title", "E. Change the data type of hearing on line 4 to short.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "main", "idAnswer", "RPS001-D002-Q026-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "question", "idQuestion", "RPS001-D002-Q026");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "main", "title", "F. Change the data type of hearing on line 4 to long.");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q026-A006", "main", "is_right", "true");


// Question 27
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "title", "What is the output of the following code snippet?");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "file_path", "/images/questions/RPS001-D002-Q027.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q027", "main", "examType2", "QUIZ");

// Answer for Q027
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "main", "idAnswer", "RPS001-D002-Q027-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "main", "title", "A. true, 20, true");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "main", "idAnswer", "RPS001-D002-Q027-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "main", "title", "B. true, 20, false");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "main", "idAnswer", "RPS001-D002-Q027-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "main", "title", "C. false, 10, true");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "main", "idAnswer", "RPS001-D002-Q027-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "main", "title", "D. false, 20, false");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "main", "idAnswer", "RPS001-D002-Q027-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "main", "title", "E. The code will not compile because of line 5.");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "main", "idAnswer", "RPS001-D002-Q027-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "question", "idQuestion", "RPS001-D002-Q027");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "main", "title", "F. None of the above.");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q027-A006", "main", "is_right", "false");


// Question 28
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "title", "Which of the following operators are ranked in increasing or the same order of precedence? Assume the + operator is binary addition, not the unary form. (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "description", "Pertanyaan tentang operator precedence");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q028", "main", "examType2", "QUIZ");

// Answer for Q028
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "main", "idAnswer", "RPS001-D002-Q028-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "main", "title", "A. +, %, --");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "main", "idAnswer", "RPS001-D002-Q028-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "main", "title", "B. ++, (int), *");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "main", "idAnswer", "RPS001-D002-Q028-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "main", "title", "C. =, !, =");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A003", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "main", "idAnswer", "RPS001-D002-Q028-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "main", "title", "D. (short), =, !, *");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "main", "idAnswer", "RPS001-D002-Q028-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "main", "title", "E. *, /, %, +, =");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "main", "idAnswer", "RPS001-D002-Q028-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "main", "title", "F. !, , &");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "main", "idAnswer", "RPS001-D002-Q028-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "question", "idQuestion", "RPS001-D002-Q028");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "main", "title", "G. ^, +, =, +=");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q028-A007", "main", "is_right", "false");


// Question 29
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "title", "What is the output of the following program?");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "description", "Pertanyaan tentang output program Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "file_path", "/images/questions/RPS001-D002-Q029.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q029", "main", "examType2", "QUIZ");

// Answer for Q029
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "main", "idAnswer", "RPS001-D002-Q029-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "main", "title", "A. 4, 6, 6.0");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "main", "idAnswer", "RPS001-D002-Q029-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "main", "title", "B. 3, 5, 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "main", "idAnswer", "RPS001-D002-Q029-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "main", "title", "C. 3, 6, 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "main", "idAnswer", "RPS001-D002-Q029-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "main", "title", "D. 4, 5, 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "main", "idAnswer", "RPS001-D002-Q029-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "main", "title", "E. The code does not compile because of line 9.");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "main", "idAnswer", "RPS001-D002-Q029-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "question", "idQuestion", "RPS001-D002-Q029");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "main", "title", "F. None of the above.");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q029-A006", "main", "is_right", "true");


// Question 30
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "title", "What is the output of the following code snippet?");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "file_path", "/images/questions/RPS001-D002-Q030.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q030", "main", "examType2", "QUIZ");

// Answer for Q030
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "main", "idAnswer", "RPS001-D002-Q030-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "main", "title", "A. true-true-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "main", "idAnswer", "RPS001-D002-Q030-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "main", "title", "B. true-true-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "main", "idAnswer", "RPS001-D002-Q030-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "main", "title", "C. true-false-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "main", "idAnswer", "RPS001-D002-Q030-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "main", "title", "D. true-false-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "main", "idAnswer", "RPS001-D002-Q030-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "main", "title", "E. false-true-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "main", "idAnswer", "RPS001-D002-Q030-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "main", "title", "F. false-true-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "main", "idAnswer", "RPS001-D002-Q030-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "main", "title", "G. false-false-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A007", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "main", "idAnswer", "RPS001-D002-Q030-A008");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "question", "idQuestion", "RPS001-D002-Q030");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "main", "title", "H. false-false-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q030-A008", "main", "is_right", "false");


// Question 31
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "title", "What is the output of the following code snippet?");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "file_path", "/images/questions/RPS001-D002-Q031.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q031", "main", "examType2", "QUIZ");

// Answer for Q031
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "main", "idAnswer", "RPS001-D002-Q031-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "main", "title", "A. 4-1");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "main", "idAnswer", "RPS001-D002-Q031-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "main", "title", "B. 4-2");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "main", "idAnswer", "RPS001-D002-Q031-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "main", "title", "C. 5-1");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "main", "idAnswer", "RPS001-D002-Q031-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "main", "title", "D. 5-2");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "main", "idAnswer", "RPS001-D002-Q031-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "main", "title", "E. The code does not compile due to line 7.");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "main", "idAnswer", "RPS001-D002-Q031-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "question", "idQuestion", "RPS001-D002-Q031");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "main", "title", "F. None of the above.");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q031-A006", "main", "is_right", "false");


// Question 32
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "title", "What are the unique outputs of the following code snippet? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "file_path", "/images/questions/RPS001-D002-Q032.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q032", "main", "examType2", "QUIZ");

// Answer for Q032
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "main", "idAnswer", "RPS001-D002-Q032-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "main", "title", "A. 1");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "main", "idAnswer", "RPS001-D002-Q032-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "main", "title", "B. 2");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "main", "idAnswer", "RPS001-D002-Q032-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "main", "title", "C. 3");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "main", "idAnswer", "RPS001-D002-Q032-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "main", "title", "D. 4");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "main", "idAnswer", "RPS001-D002-Q032-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "main", "title", "E. 5");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A005", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "main", "idAnswer", "RPS001-D002-Q032-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "main", "title", "F. 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "main", "idAnswer", "RPS001-D002-Q032-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "question", "idQuestion", "RPS001-D002-Q032");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "main", "title", "G. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q032-A007", "main", "is_right", "false");


// Question 33
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "title", "What are the unique outputs of the following code snippet? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "file_path", "/images/questions/RPS001-D002-Q033.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q033", "main", "examType2", "QUIZ");

// Answer for Q033
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "main", "idAnswer", "RPS001-D002-Q033-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "main", "title", "A. 1");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "main", "idAnswer", "RPS001-D002-Q033-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "main", "title", "B. 2");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "main", "idAnswer", "RPS001-D002-Q033-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "main", "title", "C. 3");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "main", "idAnswer", "RPS001-D002-Q033-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "main", "title", "D. 4");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "main", "idAnswer", "RPS001-D002-Q033-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "main", "title", "E. 5");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "main", "idAnswer", "RPS001-D002-Q033-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "main", "title", "F. 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "main", "idAnswer", "RPS001-D002-Q033-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "question", "idQuestion", "RPS001-D002-Q033");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "main", "title", "G. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q033-A007", "main", "is_right", "true");


// Question 34
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "title", "What is the output of the following code?");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "description", "Pertanyaan tentang output program Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "file_path", "/images/questions/RPS001-D002-Q034.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q034", "main", "examType2", "QUIZ");

// Answer for Q034
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "main", "idAnswer", "RPS001-D002-Q034-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "main", "title", "A. 0, 0, 5");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "main", "idAnswer", "RPS001-D002-Q034-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "main", "title", "B. 1, 2, 10");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "main", "idAnswer", "RPS001-D002-Q034-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "main", "title", "C. 2, 1, 5");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "main", "idAnswer", "RPS001-D002-Q034-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "main", "title", "D. 2, 0, 5");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "main", "idAnswer", "RPS001-D002-Q034-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "main", "title", "E. 3, 1, 10");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "main", "idAnswer", "RPS001-D002-Q034-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "main", "title", "F. 3, 2, 6");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "main", "idAnswer", "RPS001-D002-Q034-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "question", "idQuestion", "RPS001-D002-Q034");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "main", "title", "G. The code does not compile");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q034-A007", "main", "is_right", "false");


// Question 35
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "title", "The operator increases a value and returns the original value, while the operator decreases a value and returns the new value.");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "description", "Pertanyaan tentang operator increment dan decrement");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q035", "main", "examType2", "QUIZ");

// Answer for Q035
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "main", "idAnswer", "RPS001-D002-Q035-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "main", "title", "A. post-increment, post-increment");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "main", "idAnswer", "RPS001-D002-Q035-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "main", "title", "B. pre-decrement, post-decrement");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "main", "idAnswer", "RPS001-D002-Q035-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "main", "title", "C. post-increment, post-decrement");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "main", "idAnswer", "RPS001-D002-Q035-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "main", "title", "D. post-increment, pre-decrement");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "main", "idAnswer", "RPS001-D002-Q035-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "main", "title", "E. pre-increment, pre-decrement");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "main", "idAnswer", "RPS001-D002-Q035-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "question", "idQuestion", "RPS001-D002-Q035");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "main", "title", "F. pre-increment, post-decrement");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q035-A006", "main", "is_right", "false");


// Question 36
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "title", "What is the output of the following code snippet?");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "description", "Pertanyaan tentang output kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "file_path", "/images/questions/RPS001-D002-Q036.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q036", "main", "examType2", "QUIZ");

// Answer for Q036
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "main", "idAnswer", "RPS001-D002-Q036-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "main", "title", "A. true-false-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "main", "idAnswer", "RPS001-D002-Q036-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "main", "title", "B. false-true-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "main", "idAnswer", "RPS001-D002-Q036-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "main", "title", "C. true-true-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "main", "idAnswer", "RPS001-D002-Q036-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "main", "title", "D. false-true-true");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "main", "idAnswer", "RPS001-D002-Q036-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "main", "title", "E. false-false-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "main", "idAnswer", "RPS001-D002-Q036-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "main", "title", "F. true-true-false");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A006", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "main", "idAnswer", "RPS001-D002-Q036-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "question", "idQuestion", "RPS001-D002-Q036");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "main", "title", "G. None of the above");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q036-A007", "main", "is_right", "false");


// Question 37
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "title", "Which of the following statements are correct? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "description", "Pertanyaan tentang pernyataan yang benar di Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q037", "main", "examType2", "QUIZ");

// Answer for Q037
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "main", "idAnswer", "RPS001-D002-Q037-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "main", "title", "A. The return value of an assignment operation expression can be void.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "main", "idAnswer", "RPS001-D002-Q037-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "main", "title", "B. The inequality operator (!=) can be used to compare objects.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "main", "idAnswer", "RPS001-D002-Q037-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "main", "title", "C. The equality operator (=) can be used to compare a boolean value with a numeric value.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "main", "idAnswer", "RPS001-D002-Q037-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "main", "title", "D. During runtime, the & and operators may cause only the left side of the expression to be evaluated.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "main", "idAnswer", "RPS001-D002-Q037-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "main", "title", "E. The return value of an assignment operation expression is the value of the newly assigned variable.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A005", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "main", "idAnswer", "RPS001-D002-Q037-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "main", "title", "F. In Java, 0 and false may be used interchangeably.");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "main", "idAnswer", "RPS001-D002-Q037-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "question", "idQuestion", "RPS001-D002-Q037");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "main", "title", "G. The logical complement operator (!) cannot be used to flip numeric values");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q037-A007", "main", "is_right", "true");


// Question 38
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "title", "Which operators take three operands or values? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "description", "Pertanyaan tentang operator Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q038", "main", "examType2", "QUIZ");

// Answer for Q038
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "main", "idAnswer", "RPS001-D002-Q038-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "main", "title", "A. =");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "main", "idAnswer", "RPS001-D002-Q038-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "main", "title", "B. &&");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "main", "idAnswer", "RPS001-D002-Q038-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "main", "title", "C. *=");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "main", "idAnswer", "RPS001-D002-Q038-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "main", "title", "D. ?:");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "main", "idAnswer", "RPS001-D002-Q038-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "main", "title", "E. &");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "main", "idAnswer", "RPS001-D002-Q038-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "main", "title", "F. ++");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A006", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "main", "idAnswer", "RPS001-D002-Q038-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "question", "idQuestion", "RPS001-D002-Q038");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "main", "title", "G. /");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q038-A007", "main", "is_right", "false");


// Question 39
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "title", "How many lines of the following code contain compiler errors?");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "description", "Pertanyaan tentang error kompilasi");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "file_path", "/images/questions/RPS001-D002-Q039.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q039", "main", "examType2", "QUIZ");

// Answer for Q039
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "main", "idAnswer", "RPS001-D002-Q039-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "question", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "main", "title", "A. 0");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "main", "idAnswer", "RPS001-D002-Q039-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "question", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "main", "title", "B. 1");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "main", "idAnswer", "RPS001-D002-Q039-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "question", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "main", "title", "C. 2");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "main", "idAnswer", "RPS001-D002-Q039-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "question", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "main", "title", "D. 3");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "main", "idAnswer", "RPS001-D002-Q039-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "question", "idQuestion", "RPS001-D002-Q039");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "main", "title", "E. 4");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q039-A005", "main", "is_right", "false");


// Question 40
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "title", "Given the following code snippet, what are the values of the variables after it is executed? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "description", "Pertanyaan tentang nilai variabel setelah eksekusi kode");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "file_path", "/images/questions/RPS001-D002-Q040.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q040", "main", "examType2", "QUIZ");

// Answer for Q040
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "main", "idAnswer", "RPS001-D002-Q040-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "main", "title", "A. ticketsSold is 8.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "main", "idAnswer", "RPS001-D002-Q040-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "main", "title", "B. ticketsTaken is 2.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "main", "idAnswer", "RPS001-D002-Q040-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "main", "title", "C. ticketsSold is 6.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A003", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "main", "idAnswer", "RPS001-D002-Q040-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "main", "title", "D. ticketsTaken is 6.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "main", "idAnswer", "RPS001-D002-Q040-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "main", "title", "E. ticketsSold is 7.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "main", "idAnswer", "RPS001-D002-Q040-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "main", "title", "F. ticketsTaken is 4.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A006", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "main", "idAnswer", "RPS001-D002-Q040-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "question", "idQuestion", "RPS001-D002-Q040");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "main", "title", "G. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q040-A007", "main", "is_right", "false");


// Question 41
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "title", "Which of the following can be used to change the order of operation in an expression? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "description", "Pertanyaan tentang urutan operasi ekspresi");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q041", "main", "examType2", "QUIZ");

// Answer for Q041
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "main", "idAnswer", "RPS001-D002-Q041-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "main", "title", "A. []");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "main", "idAnswer", "RPS001-D002-Q041-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "main", "title", "B. <>");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "main", "idAnswer", "RPS001-D002-Q041-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "main", "title", "C. ()");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A003", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "main", "idAnswer", "RPS001-D002-Q041-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "main", "title", "D. /");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "main", "idAnswer", "RPS001-D002-Q041-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "main", "title", "E. {}");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "main", "idAnswer", "RPS001-D002-Q041-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "question", "idQuestion", "RPS001-D002-Q041");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "main", "title", "F. \"\"");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q041-A006", "main", "is_right", "false");


// Question 42
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "title", "What is the result of executing the following code snippet? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "description", "Pertanyaan tentang hasil eksekusi kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "file_path", "/images/questions/RPS001-D002-Q042.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q042", "main", "examType2", "QUIZ");

// Answer for Q042
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "main", "idAnswer", "RPS001-D002-Q042-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "main", "title", "A. start is 0.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "main", "idAnswer", "RPS001-D002-Q042-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "main", "title", "B. start is -128.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A002", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "main", "idAnswer", "RPS001-D002-Q042-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "main", "title", "C. start is 127.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "main", "idAnswer", "RPS001-D002-Q042-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "main", "title", "D. end is 8.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "main", "idAnswer", "RPS001-D002-Q042-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "main", "title", "E. end is 11.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A005", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "main", "idAnswer", "RPS001-D002-Q042-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "main", "title", "F. end is 12.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A006", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "main", "idAnswer", "RPS001-D002-Q042-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "main", "title", "G. The code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A007", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "main", "idAnswer", "RPS001-D002-Q042-A008");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "question", "idQuestion", "RPS001-D002-Q042");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "main", "title", "H. The code compiles but throws an exception at runtime");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q042-A008", "main", "is_right", "false");


// Question 43
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "title", "Which of the following statements about unary operators are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "description", "Pertanyaan tentang operator unary");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q043", "main", "examType2", "QUIZ");

// Answer for Q043
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "main", "idAnswer", "RPS001-D002-Q043-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "main", "title", "A. Unary operators are always executed before any surrounding numeric binary or ternary operators.");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A001", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "main", "idAnswer", "RPS001-D002-Q043-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "main", "title", "B. The ~ operator can be used to flip a boolean value.");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "main", "idAnswer", "RPS001-D002-Q043-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "main", "title", "C. The pre-increment operator (++) returns the value of the variable before the increment is applied.");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "main", "idAnswer", "RPS001-D002-Q043-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "main", "title", "D. The post-decrement operator (--) returns the value of the variable before the decrement is applied.");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A004", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "main", "idAnswer", "RPS001-D002-Q043-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "main", "title", "E. The ! operator cannot be used on numeric values.");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A005", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "main", "idAnswer", "RPS001-D002-Q043-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "question", "idQuestion", "RPS001-D002-Q043");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "main", "title", "F. None of the above");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q043-A006", "main", "is_right", "false");


// Question 44
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "title", "What is the result of executing the following code snippet?");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "description", "Pertanyaan tentang hasil eksekusi kode Java");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "file_path", "/images/questions/RPS001-D002-Q044.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q044", "main", "examType2", "QUIZ");

// Answer for Q044
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "main", "idAnswer", "RPS001-D002-Q044-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "main", "title", "A. -7,-8,9");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A001", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "main", "idAnswer", "RPS001-D002-Q044-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "main", "title", "B. -7,-8,10");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A002", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "main", "idAnswer", "RPS001-D002-Q044-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "main", "title", "C. -8,-8,4");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A003", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "main", "idAnswer", "RPS001-D002-Q044-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "main", "title", "D. -8,-8,5");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A004", "main", "is_right", "false");

client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "main", "idAnswer", "RPS001-D002-Q044-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "main", "title", "E. -9,-8,9");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A005", "main", "is_right", "true");

client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "main", "idAnswer", "RPS001-D002-Q044-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "question", "idQuestion", "RPS001-D002-Q044");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "main", "title", "F. -9,-8,10");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q044-A006", "main", "is_right", "false");

// Question 45 
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "title", "Which of the following data types can be used in a switch expression? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "description", "Pertanyaan tentang tipe data yang dapat digunakan dalam ekspresi switch.");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q045", "main", "examType2", "QUIZ");

// Answer for Q045
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "main", "idAnswer", "RPS001-D002-Q045-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "main", "title", "A. enum");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A001", "main", "is_right", "true"); // Marked as true based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "main", "idAnswer", "RPS001-D002-Q045-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "main", "title", "B. int");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A002", "main", "is_right", "true"); // Marked as true based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "main", "idAnswer", "RPS001-D002-Q045-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "main", "title", "C. Byte");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A003", "main", "is_right", "true"); // Marked as true based on source 2

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "main", "idAnswer", "RPS001-D002-Q045-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "main", "title", "D. long");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A004", "main", "is_right", "false"); // Marked as false based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "main", "idAnswer", "RPS001-D002-Q045-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "main", "title", "E. String");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A005", "main", "is_right", "true"); // Marked as true based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "main", "idAnswer", "RPS001-D002-Q045-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "main", "title", "F. char");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A006", "main", "is_right", "true"); // Marked as true based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "main", "idAnswer", "RPS001-D002-Q045-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "main", "title", "G. var");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A007", "main", "is_right", "true"); // Marked as true based on source 1

client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "main", "idAnswer", "RPS001-D002-Q045-A008");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "question", "idQuestion", "RPS001-D002-Q045");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "main", "title", "H. double");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q045-A008", "main", "is_right", "false"); // Marked as false based on source 1


// Question 46
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "title", "What is the output of the following code snippet? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "description", "Pertanyaan tentang output kode Java.");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "file_path", "/images/questions/RPS001-D002-Q046.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q046", "main", "examType2", "QUIZ");

// Answer for Q046
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "main", "idAnswer", "RPS001-D002-Q046-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "main", "title", "A. Too Low");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A001", "main", "is_right", "false"); // Marked as false based on source 3

client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "main", "idAnswer", "RPS001-D002-Q046-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "main", "title", "B. Just Right");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A002", "main", "is_right", "true"); // Marked as true based on source 3

client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "main", "idAnswer", "RPS001-D002-Q046-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "main", "title", "C. Too High");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A003", "main", "is_right", "false"); // Marked as false based on source 3

client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "main", "idAnswer", "RPS001-D002-Q046-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "main", "title", "D. A NullPointerException is thrown at runtime.");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A004", "main", "is_right", "false"); // Marked as false based on source 3

client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "main", "idAnswer", "RPS001-D002-Q046-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "main", "title", "E. The code will not compile because of line 7.");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A005", "main", "is_right", "false"); // Marked as false based on source 4

client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "main", "idAnswer", "RPS001-D002-Q046-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "question", "idQuestion", "RPS001-D002-Q046");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "main", "title", "F. The code will not compile because of line 8.");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q046-A006", "main", "is_right", "false"); // Marked as false based on source 4


// Question 47
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "title", "Which of the following data types are permitted on the right side of a for-each expression? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "description", "Pertanyaan tentang tipe data yang diizinkan dalam for-each loop.");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q047", "main", "examType2", "QUIZ");

// Answer for Q047
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "main", "idAnswer", "RPS001-D002-Q047-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "main", "title", "A. Double[][]");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A001", "main", "is_right", "true"); // Marked as true based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "main", "idAnswer", "RPS001-D002-Q047-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "main", "title", "B. Object");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A002", "main", "is_right", "false"); // Marked as false based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "main", "idAnswer", "RPS001-D002-Q047-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "main", "title", "C. Map");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A003", "main", "is_right", "false"); // Marked as false based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "main", "idAnswer", "RPS001-D002-Q047-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "main", "title", "D. List");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A004", "main", "is_right", "true"); // Marked as true based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "main", "idAnswer", "RPS001-D002-Q047-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "main", "title", "E. String");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A005", "main", "is_right", "false"); // Marked as false based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "main", "idAnswer", "RPS001-D002-Q047-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "main", "title", "F. char[]");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A006", "main", "is_right", "true"); // Marked as true based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "main", "idAnswer", "RPS001-D002-Q047-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "main", "title", "G. Exception");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A007", "main", "is_right", "false"); // Marked as false based on source 5

client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "main", "idAnswer", "RPS001-D002-Q047-A008");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "question", "idQuestion", "RPS001-D002-Q047");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "main", "title", "H. Set");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q047-A008", "main", "is_right", "true"); // Marked as true based on source 5


// Question 48
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "title", "What is the output of calling printReptile(6)?");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "description", "Pertanyaan tentang output dari pemanggilan method.");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "file_path", "/images/questions/RPS001-D002-Q048.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q048", "main", "examType2", "QUIZ");

// Answer for Q048
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "main", "idAnswer", "RPS001-D002-Q048-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "main", "title", "A. Snake");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A001", "main", "is_right", "false"); // Marked as false based on source 6

client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "main", "idAnswer", "RPS001-D002-Q048-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "main", "title", "B. Lizard");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A002", "main", "is_right", "false"); // Marked as false based on source 6

client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "main", "idAnswer", "RPS001-D002-Q048-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "main", "title", "C. Turtle");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A003", "main", "is_right", "false"); // Marked as false based on source 6

client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "main", "idAnswer", "RPS001-D002-Q048-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "main", "title", "D. Alligator");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A004", "main", "is_right", "false"); // Marked as false based on source 6

client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "main", "idAnswer", "RPS001-D002-Q048-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "main", "title", "E. TurtleAlligator");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A005", "main", "is_right", "false"); // Marked as false based on source 6

client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "main", "idAnswer", "RPS001-D002-Q048-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "question", "idQuestion", "RPS001-D002-Q048");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "main", "title", "F. None of the above");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q048-A006", "main", "is_right", "true"); // Marked as true based on source 7


// Question 49
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "title", "What is the output of the following code snippet? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "description", "Pertanyaan tentang output kode Java.");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "question_type", "IMAGE");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "file_path", "/images/questions/RPS001-D002-Q049.png");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q049", "main", "examType2", "QUIZ");

// Answer for Q049
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "main", "idAnswer", "RPS001-D002-Q049-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "main", "title", "A. It compiles and runs without issue but does not produce any output.");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A001", "main", "is_right", "false"); // Marked as false based on source 8

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "main", "idAnswer", "RPS001-D002-Q049-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "main", "title", "B. 10, 14,");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A002", "main", "is_right", "false"); // Marked as false based on source 9

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "main", "idAnswer", "RPS001-D002-Q049-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "main", "title", "C. 10, 10, 14,");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A003", "main", "is_right", "false"); // Marked as false based on source 9

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "main", "idAnswer", "RPS001-D002-Q049-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "main", "title", "D. 10, 10, 14, 10, 14,");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A004", "main", "is_right", "false"); // Marked as false based on source 9

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "main", "idAnswer", "RPS001-D002-Q049-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "main", "title", "E. Exactly one line of code does not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A005", "main", "is_right", "true"); // Marked as true based on source 9

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "main", "idAnswer", "RPS001-D002-Q049-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "main", "title", "F. Exactly two lines of code do not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A006", "main", "is_right", "false"); // Marked as false based on source 10

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "main", "idAnswer", "RPS001-D002-Q049-A007");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "main", "title", "G. Three or more lines of code do not compile.");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "main", "description", "G");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A007", "main", "is_right", "false"); // Marked as false based on source 10

client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "main", "idAnswer", "RPS001-D002-Q049-A008");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "question", "idQuestion", "RPS001-D002-Q049");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "main", "title", "H. The code contains an infinite loop and does not terminate.");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "main", "description", "H");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q049-A008", "main", "is_right", "false"); // Marked as false based on source 11


// Question 50
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "rps", "idRps", "RPS001");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "rps_detail", "id", "RPS001-D002");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "title", "Which statements about decision structures are true? (Choose all that apply.)");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "description", "Pertanyaan tentang struktur keputusan dalam pemrograman.");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "question_type", "NORMAL");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "answer_type", "MULTIPLE_CHOICE");
client.insertRecord(tableQuestion, "RPS001-D002-Q050", "main", "examType2", "QUIZ");

// Answer for Q050
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "main", "idAnswer", "RPS001-D002-Q050-A001");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "main", "title", "A. A for-each loop can be executed on any Collections Framework object.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "main", "description", "A");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A001", "main", "is_right", "false"); // Marked as false based on source 12

client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "main", "idAnswer", "RPS001-D002-Q050-A002");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "main", "title", "B. The body of a while loop is guaranteed to be executed at least once.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "main", "description", "B");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A002", "main", "is_right", "false"); // Marked as false based on source 13

client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "main", "idAnswer", "RPS001-D002-Q050-A003");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "main", "title", "C. The conditional expression of a for loop is evaluated before the first execution of the loop body.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "main", "description", "C");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A003", "main", "is_right", "true"); // Marked as true based on source 14

client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "main", "idAnswer", "RPS001-D002-Q050-A004");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "main", "title", "D. A switch expression that takes a String and assigns the result to a variable requires a default branch.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "main", "description", "D");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A004", "main", "is_right", "true"); // Marked as true based on source 15

client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "main", "idAnswer", "RPS001-D002-Q050-A005");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "main", "title", "E. The body of a do/while loop is guaranteed to be executed at least once.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "main", "description", "E");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A005", "main", "is_right", "true"); // Marked as true based on source 16

client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "main", "idAnswer", "RPS001-D002-Q050-A006");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "question", "idQuestion", "RPS001-D002-Q050");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "main", "title", "F. An if statement can have multiple corresponding else statements.");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "main", "description", "F");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "main", "type", "NORMAL");
client.insertRecord(tableAnswer, "RPS001-D002-Q050-A006", "main", "is_right", "false"); // Marked as false based on source 17
    }
}