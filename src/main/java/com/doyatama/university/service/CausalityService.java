package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.Causality;
import com.doyatama.university.model.CausalityRating;
import com.doyatama.university.model.QuestionCriteria;
import com.doyatama.university.model.LinguisticValue; // Import LinguisticValue
import com.doyatama.university.payload.CausalityRatingRequest;
import com.doyatama.university.payload.CausalityRequest; // <-- TAMBAHKAN IMPORT INI
import com.doyatama.university.payload.DefaultResponse; // Tambahkan jika belum
import com.doyatama.university.payload.PagedResponse; // Tambahkan jika belum
import com.doyatama.university.repository.CausalityRepository;
import com.doyatama.university.repository.CausalityRatingRepository;
import com.doyatama.university.repository.QuestionCriteriaRepository;
import com.doyatama.university.repository.LinguisticValueRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CausalityService {
    @Autowired
    private CausalityRepository causalityRepository;

    @Autowired
    private QuestionCriteriaRepository questionCriteriaRepository;

    @Autowired
    private CausalityRatingRepository causalityRatingRepository;

    @Autowired
    private LinguisticValueRepository linguisticValueRepository;

    private static final Logger logger = LoggerFactory.getLogger(CausalityService.class);

    public PagedResponse<Causality> getAllCausality(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Causality> causalities = causalityRepository.findAll(size);

        long totalElements = (long) causalities.size();
        return new PagedResponse<>(causalities, totalElements, "Successfully retrieved data", 200);
    }

    public Causality createCausality(CausalityRequest causalityRequest) throws IOException {
        if (causalityRequest.getSubject() == null ||
            causalityRequest.getDescription() == null ||
            causalityRequest.getSemester() == null) {
            throw new BadRequestException("Subject, description, and semester are required");
        }

        Causality causality = new Causality();
        causality.setDateCreated(Instant.now());

        causality.setDescription(causalityRequest.getDescription());
        causality.setSubject(causalityRequest.getSubject());
        causality.setSemester(causalityRequest.getSemester());

        causality.setTeamTeaching1(causalityRequest.getTeamTeaching1());
        causality.setTeamTeaching2(causalityRequest.getTeamTeaching2());
        causality.setTeamTeaching3(causalityRequest.getTeamTeaching3());

        causality.setStatus("Pending");

        // Perbaikan: Panggil findAll dengan size (Integer.MAX_VALUE) lalu filter
        List<QuestionCriteria> allQCs = questionCriteriaRepository.findAll(Integer.MAX_VALUE);
        List<String> allCriteriaIds = allQCs.stream()
                                                .map(QuestionCriteria::getId)
                                                .collect(Collectors.toList());
        if (allCriteriaIds.isEmpty()) {
            throw new BadRequestException("No QuestionCriteria found to create a causality task. Please add criteria first.");
        }
        causality.setCriteriaIds(allCriteriaIds); // Set List<String> ke setter yang akan mengonversi ke criteriaIdsRaw

        logger.info("CausalityService - createCausality: Attempting to save new task for subject '{}' with {} criteria.",
                causality.getSubject(), allCriteriaIds.size());

        return causalityRepository.save(causality);
    }

    public DefaultResponse<Causality> getCausalityById(String idCausality) throws IOException {
        Causality causality = causalityRepository.findById(idCausality);
        if (causality == null) {
            throw new ResourceNotFoundException("Causality", "idCausality", idCausality);
        }
        logger.info("CausalityService - getCausalityById: Retrieved ID {} with Semester = {}, Status = {}",
                causality.getIdCausality(), causality.getSemester(), causality.getStatus());
        return new DefaultResponse<>(causality, 1L, "Successfully retrieved data");
    }

    public Causality updateCausality(String idCausality, CausalityRequest causalityRequest) throws IOException {
        Causality existingCausality = causalityRepository.findById(idCausality);
        if (existingCausality == null) {
            throw new ResourceNotFoundException("Causality", "idCausality", idCausality);
        }

        existingCausality.setDescription(causalityRequest.getDescription());
        existingCausality.setSubject(causalityRequest.getSubject());
        existingCausality.setSemester(causalityRequest.getSemester());

        existingCausality.setTeamTeaching1(causalityRequest.getTeamTeaching1());
        existingCausality.setTeamTeaching2(causalityRequest.getTeamTeaching2());
        existingCausality.setTeamTeaching3(causalityRequest.getTeamTeaching3());

        logger.info("CausalityService - updateCausality: Updating task ID {} for subject '{}' with new semester {}",
                idCausality, existingCausality.getSubject(), existingCausality.getSemester());

        return causalityRepository.update(idCausality, existingCausality);
    }

    public boolean deleteCausality(String idCausality) throws IOException {
        Causality causality = causalityRepository.findById(idCausality);
        if (causality == null) {
            throw new ResourceNotFoundException("Causality", "idCausality", idCausality);
        }
        logger.info("CausalityService - deleteCausality: Deleting task ID {}", idCausality);
        return causalityRepository.deleteById(idCausality);
    }

    public Causality updateCausalityStatus(String idCausality, String newStatus) throws IOException {
        Causality existingCausality = causalityRepository.findById(idCausality);
        if (existingCausality == null) {
            throw new ResourceNotFoundException("Causality", "idCausality", idCausality);
        }

        if (!Arrays.asList("Pending", "InProgress", "Completed", "Cancelled").contains(newStatus)) {
            throw new BadRequestException("Invalid status provided: " + newStatus);
        }

        existingCausality.setStatus(newStatus);
        logger.info("CausalityService - updateCausalityStatus: Updating status for task ID {} to {}", idCausality, newStatus);
        return causalityRepository.update(idCausality, existingCausality);
    }

    public List<Causality> getTasksForTeacher(String teacherId) throws IOException {
        logger.info("CausalityService - getTasksForTeacher: Fetching tasks for teacher ID {}", teacherId);
        return causalityRepository.findByTeamTeaching(teacherId);
    }

    public static class CausalityRatingPair {
        private String influencingCriteriaId;
        private String influencingCriteriaName;
        private String influencedCriteriaId;
        private String influencedCriteriaName;
        private boolean alreadyRated;

        public CausalityRatingPair(String influencingCriteriaId, String influencingCriteriaName, String influencedCriteriaId, String influencedCriteriaName, boolean alreadyRated) {
            this.influencingCriteriaId = influencingCriteriaId;
            this.influencingCriteriaName = influencingCriteriaName;
            this.influencedCriteriaId = influencedCriteriaId;
            this.influencedCriteriaName = influencedCriteriaName;
            this.alreadyRated = alreadyRated;
        }

        public String getInfluencingCriteriaId() { return influencingCriteriaId; }
        public String getInfluencingCriteriaName() { return influencingCriteriaName; }
        public String getInfluencedCriteriaId() { return influencedCriteriaId; }
        public String getInfluencedCriteriaName() { return influencedCriteriaName; }
        public boolean isAlreadyRated() { return alreadyRated; }

        public void setInfluencingCriteriaId(String influencingCriteriaId) { this.influencingCriteriaId = influencingCriteriaId; }
        public void setInfluencingCriteriaName(String influencingCriteriaName) { this.influencingCriteriaName = influencingCriteriaName; }
        public void setInfluencedCriteriaId(String influencedCriteriaId) { this.influencedCriteriaId = influencedCriteriaId; }
        public void setInfluencedCriteriaName(String influencedCriteriaName) { this.influencedCriteriaName = influencedCriteriaName; }
        public void setAlreadyRated(boolean alreadyRated) { this.alreadyRated = alreadyRated; }
    }

    public List<CausalityRatingPair> getCriteriaPairsToRate(String taskId, String teacherId) throws IOException {
        Causality task = causalityRepository.findById(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("Causality Task", "id", taskId);
        }

        if (!teacherId.equals(task.getTeamTeaching1()) &&
            !teacherId.equals(task.getTeamTeaching2()) &&
            !teacherId.equals(task.getTeamTeaching3())) {
            throw new BadRequestException("Dosen tidak berwenang untuk mengakses tugas ini.");
        }

        List<String> criteriaIds = task.getCriteriaIds();
        if (criteriaIds == null || criteriaIds.isEmpty()) {
            throw new BadRequestException("Causality task has no criteria defined.");
        }

        List<CausalityRatingPair> pairsToRate = new ArrayList<>();

        // Perbaikan: Panggil findAll dengan size (Integer.MAX_VALUE) lalu filter
        List<QuestionCriteria> allQCs = questionCriteriaRepository.findAll(Integer.MAX_VALUE);
        List<QuestionCriteria> sortedCriteria = allQCs.stream()
                                                        .filter(qc -> criteriaIds.contains(qc.getId()))
                                                        .sorted(Comparator.comparing(QuestionCriteria::getId)) // Urutkan berdasarkan ID
                                                        .collect(Collectors.toList());

        if (sortedCriteria.isEmpty()) {
            throw new BadRequestException("Tidak ada kriteria yang relevan ditemukan di Master Soal untuk tugas ini.");
        }


        for (int i = 0; i < sortedCriteria.size(); i++) {
            for (int j = 0; j < sortedCriteria.size(); j++) {
                if (i == j) {
                    continue;
                }
                QuestionCriteria influencing = sortedCriteria.get(i);
                QuestionCriteria influenced = sortedCriteria.get(j);

                boolean alreadyRated = causalityRatingRepository.existsByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId(
                    taskId, teacherId, influencing.getId(), influenced.getId()
                );

                pairsToRate.add(new CausalityRatingPair(influencing.getId(), influencing.getName(),
                                                        influenced.getId(), influenced.getName(), alreadyRated));
            }
        }
        logger.info("CausalityService - getCriteriaPairsToRate: Generated {} pairs for task ID {} by teacher {}",
                pairsToRate.size(), taskId, teacherId);
        return pairsToRate;
    }

    public CausalityRating submitCausalityRating(CausalityRatingRequest request) throws IOException { // <-- UBAH DI SINI
        logger.info("CausalityService - submitCausalityRating: Processing request: {}", request.toString()); // Log request yang diterima

        // 1. Konversi CausalityRatingRequest ke CausalityRating
        CausalityRating ratingData = new CausalityRating();
        // ID Rating akan dibuat di sini atau di repository
        ratingData.setIdRating(UUID.randomUUID().toString()); // Generate ID di sini
        ratingData.setCausalityTaskId(request.getCausalityTaskId());
        ratingData.setReviewerId(request.getReviewerId());
        ratingData.setInfluencingCriteriaId(request.getInfluencingCriteriaId());
        ratingData.setInfluencedCriteriaId(request.getInfluencedCriteriaId());
        ratingData.setRatingValue(request.getRatingValue()); // ID Linguistik (misal "LV002")

        // Tanggal dinilai
        ratingData.setDateRated(Instant.now());

        // 2. Validasi tugas dan reviewer (logika yang sudah ada)
        Causality task = causalityRepository.findById(ratingData.getCausalityTaskId());
        if (task == null) {
            logger.warn("Causality Task with ID {} not found.", ratingData.getCausalityTaskId());
            throw new ResourceNotFoundException("Causality Task", "id", ratingData.getCausalityTaskId());
        }

        if (!ratingData.getReviewerId().equals(task.getTeamTeaching1()) &&
            !ratingData.getReviewerId().equals(task.getTeamTeaching2()) &&
            !ratingData.getReviewerId().equals(task.getTeamTeaching3())) {
            logger.warn("Reviewer ID {} is not authorized for task {}.", ratingData.getReviewerId(), ratingData.getCausalityTaskId());
            throw new BadRequestException("Dosen tidak berwenang untuk menilai tugas ini.");
        }

        List<String> criteriaIds = task.getCriteriaIds();
        if (criteriaIds == null ||
            !criteriaIds.contains(ratingData.getInfluencingCriteriaId()) ||
            !criteriaIds.contains(ratingData.getInfluencedCriteriaId())) {
            logger.warn("Criteria {} and/or {} are not part of task {}.", ratingData.getInfluencingCriteriaId(), ratingData.getInfluencedCriteriaId(), ratingData.getCausalityTaskId());
            throw new BadRequestException("Kriteria yang dinilai tidak termasuk dalam tugas ini atau tugas tidak memiliki kriteria.");
        }

        // 3. Validasi dan pemrosesan LinguisticValue (logika yang sudah ada)
        if (ratingData.getRatingValue() == null || ratingData.getRatingValue().isEmpty()) {
            logger.warn("Linguistic value (ID) for rating is empty for task {}.", ratingData.getCausalityTaskId());
            throw new BadRequestException("Nilai linguistik (ID) untuk rating tidak boleh kosong.");
        }

        LinguisticValue selectedLinguistic = linguisticValueRepository.findById(ratingData.getRatingValue());
        if (selectedLinguistic == null) {
            logger.warn("Linguistic value with ID {} not found in repository.", ratingData.getRatingValue());
            throw new BadRequestException("Nilai linguistik dengan ID " + ratingData.getRatingValue() + " tidak ditemukan.");
        }
        if (selectedLinguistic.getAverageValue() == null) {
            logger.warn("Linguistic value {} (ID: {}) has no valid average value.", selectedLinguistic.getName(), ratingData.getRatingValue());
            throw new BadRequestException("Nilai linguistik " + selectedLinguistic.getName() + " tidak memiliki nilai rata-rata yang valid.");
        }
        
        // Set nilai numerik rata-rata ke field di CausalityRating
        ratingData.setNumericRatingValue(selectedLinguistic.getAverageValue().doubleValue()); // <-- Konversi Float ke Double

        logger.debug("CausalityService - submitCausalityRating: Linguistic value {} (ID: {}) found. Numeric value set to: {}",
                     selectedLinguistic.getName(), ratingData.getRatingValue(), ratingData.getNumericRatingValue());

        // 4. Periksa rating yang sudah ada dan simpan/perbarui
        List<CausalityRating> existingRatings = causalityRatingRepository.findByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId(
            ratingData.getCausalityTaskId(), ratingData.getReviewerId(), ratingData.getInfluencingCriteriaId(), ratingData.getInfluencedCriteriaId()
        );

        if (existingRatings != null && !existingRatings.isEmpty()) {
            CausalityRating existingRating = existingRatings.get(0);
            existingRating.setRatingValue(ratingData.getRatingValue());
            existingRating.setNumericRatingValue(ratingData.getNumericRatingValue());
            existingRating.setDateRated(Instant.now());
            logger.info("CausalityService - submitCausalityRating: Updating existing rating ID {} for task {}",
                         existingRating.getIdRating(), ratingData.getCausalityTaskId());
            return causalityRatingRepository.save(existingRating);
        } else {
            // ID sudah dibuat di awal method
            logger.info("CausalityService - submitCausalityRating: Saving new rating ID {} for task {}", ratingData.getIdRating(), ratingData.getCausalityTaskId());
            return causalityRatingRepository.save(ratingData);
        }
    }

    public List<CausalityRating> getAllRatingsForTask(String taskId) throws IOException {
        logger.info("CausalityService - getAllRatingsForTask: Fetching all ratings for task ID {}", taskId);
        return causalityRatingRepository.findByCausalityTaskId(taskId);
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