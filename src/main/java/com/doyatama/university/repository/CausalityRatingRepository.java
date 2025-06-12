package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.CausalityRating;
import com.doyatama.university.model.LinguisticValue; // Import LinguisticValue
import com.doyatama.university.repository.LinguisticValueRepository; // Import LinguisticValueRepository

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; // Tambahkan ini jika LinguisticValueRepository di-autowire
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;

@Repository
public class CausalityRatingRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "causality_rating";

    private static final Logger logger = LoggerFactory.getLogger(CausalityRatingRepository.class);

    @Autowired // Autowire LinguisticValueRepository jika Anda ingin menggunakannya di sini
    private LinguisticValueRepository linguisticValueRepository;

    public CausalityRating save(CausalityRating causalityRating) throws IOException {
        logger.info("CausalityRatingRepository - save: Processing rating for taskId={}, reviewerId={}, ratingValue={}",
                causalityRating.getCausalityTaskId(), causalityRating.getReviewerId(), causalityRating.getRatingValue()); // <-- Log nilai-nilai kunci

        HBaseCustomClient client = new HBaseCustomClient(conf);

        if (causalityRating.getIdRating() == null || causalityRating.getIdRating().isEmpty()) {
            causalityRating.setIdRating(UUID.randomUUID().toString());
        }
        String rowKey = causalityRating.getIdRating();

        TableName tableCausalityRating = TableName.valueOf(tableName);

        // Hitung numericRatingValue sebelum disimpan
        if (causalityRating.getRatingValue() != null && linguisticValueRepository != null) {
            LinguisticValue selectedLinguistic = null;
            try {
                selectedLinguistic = linguisticValueRepository.findById(causalityRating.getRatingValue());
                logger.debug("LinguisticValueRepository.findById({}) returned: {}", causalityRating.getRatingValue(), selectedLinguistic != null ? selectedLinguistic.getName() : "null"); // <-- Log hasil pencarian LinguisticValue

                if (selectedLinguistic != null && selectedLinguistic.getAverageValue() != null) {
                    causalityRating.setNumericRatingValue(selectedLinguistic.getAverageValue().doubleValue());
                    logger.info("Calculated numericRatingValue for {} is {}", causalityRating.getRatingValue(), causalityRating.getNumericRatingValue()); // <-- Log nilai numerik yang dihitung
                } else {
                    causalityRating.setNumericRatingValue(0.0);
                    // Gunakan logger.warn agar lebih mudah terlihat
                    logger.warn("Linguistic value with ID {} not found or has invalid average value for rating {}. Setting numericRatingValue to 0.0.", causalityRating.getRatingValue(), rowKey);
                }
            } catch (IOException e) {
                logger.error("Error looking up LinguisticValue with ID {}: {}", causalityRating.getRatingValue(), e.getMessage(), e); // <-- Log error jika pencarian LV gagal
                causalityRating.setNumericRatingValue(0.0);
            }
        } else {
            causalityRating.setNumericRatingValue(0.0);
            logger.warn("RatingValue (Linguistic ID) is null for rating {}. Setting numericRatingValue to 0.0.", rowKey);
        }

        client.insertRecord(tableCausalityRating, rowKey, "main", "idRating", rowKey);
        client.insertRecord(tableCausalityRating, rowKey, "main", "causalityTaskId", causalityRating.getCausalityTaskId());
        client.insertRecord(tableCausalityRating, rowKey, "main", "reviewerId", causalityRating.getReviewerId());
        client.insertRecord(tableCausalityRating, rowKey, "main", "influencingCriteriaId", causalityRating.getInfluencingCriteriaId());
        client.insertRecord(tableCausalityRating, rowKey, "main", "influencedCriteriaId", causalityRating.getInfluencedCriteriaId());

        if (causalityRating.getRatingValue() != null) {
            client.insertRecord(tableCausalityRating, rowKey, "main", "ratingValue", causalityRating.getRatingValue());
        } else {
            client.insertRecord(tableCausalityRating, rowKey, "main", "ratingValue", "");
        }

        client.insertRecord(tableCausalityRating, rowKey, "main", "numericRatingValue", String.valueOf(causalityRating.getNumericRatingValue()));

        if (causalityRating.getDateRated() != null) {
            client.insertRecord(tableCausalityRating, rowKey, "main", "dateRated", causalityRating.getDateRated().toString());
        } else {
            client.insertRecord(tableCausalityRating, rowKey, "main", "dateRated", "");
        }

        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        client.insertRecord(tableCausalityRating, rowKey, "detail", "modified_at", instant.toString());

        logger.info("CausalityRatingRepository - save: Successfully saved rating ID {} for task {}", rowKey, causalityRating.getCausalityTaskId());
        return causalityRating;
    }

    public CausalityRating findById(String ratingId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableCausalityRating = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idRating", "idRating");
        columnMapping.put("causalityTaskId", "causalityTaskId");
        columnMapping.put("reviewerId", "reviewerId");
        columnMapping.put("influencingCriteriaId", "influencingCriteriaId");
        columnMapping.put("influencedCriteriaId", "influencedCriteriaId");
        columnMapping.put("ratingValue", "ratingValue");
        columnMapping.put("numericRatingValue", "numericRatingValue"); // <-- Tambahkan ini
        columnMapping.put("dateRated", "dateRated");

        // HBaseCustomClient akan otomatis mengonversi String ke double (primitif)
        CausalityRating result = client.showDataTable(tableCausalityRating.toString(), columnMapping, ratingId, CausalityRating.class);

        if (result != null) {
            logger.info("CausalityRatingRepository - findById: Retrieved rating ID {} for task {}", result.getIdRating(), result.getCausalityTaskId());
        } else {
            logger.warn("CausalityRatingRepository - findById: Rating with ID {} not found.", ratingId);
        }
        return result;
    }

    public List<CausalityRating> findByCausalityTaskId(String causalityTaskId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableCausalityRating = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("idRating", "idRating");
        columnMapping.put("causalityTaskId", "causalityTaskId");
        columnMapping.put("reviewerId", "reviewerId");
        columnMapping.put("influencingCriteriaId", "influencingCriteriaId");
        columnMapping.put("influencedCriteriaId", "influencedCriteriaId");
        columnMapping.put("ratingValue", "ratingValue");
        columnMapping.put("numericRatingValue", "numericRatingValue");
        columnMapping.put("dateRated", "dateRated");

        List<CausalityRating> allRatings = client.showListTable(tableCausalityRating.toString(), columnMapping, CausalityRating.class, Integer.MAX_VALUE);

        List<CausalityRating> filteredRatings = new ArrayList<>();
        if (allRatings != null) {
            for (CausalityRating rating : allRatings) {
                if (rating.getCausalityTaskId() != null && rating.getCausalityTaskId().equals(causalityTaskId)) {
                    filteredRatings.add(rating);
                }
            }
        }
        logger.info("CausalityRatingRepository - findByCausalityTaskId: Retrieved {} ratings for task {}", filteredRatings.size(), causalityTaskId);
        return filteredRatings;
    }

    public List<CausalityRating> findByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId(
            String causalityTaskId, String reviewerId, String influencingCriteriaId, String influencedCriteriaId) throws IOException {

        List<CausalityRating> allRatingsForTask = findByCausalityTaskId(causalityTaskId);

        List<CausalityRating> matchingRatings = new ArrayList<>();
        for (CausalityRating r : allRatingsForTask) {
            if (r.getReviewerId() != null && r.getReviewerId().equals(reviewerId) &&
                r.getInfluencingCriteriaId() != null && r.getInfluencingCriteriaId().equals(influencingCriteriaId) &&
                r.getInfluencedCriteriaId() != null && r.getInfluencedCriteriaId().equals(influencedCriteriaId)) {
                matchingRatings.add(r);
            }
        }
        
        logger.debug("CausalityRatingRepository - findByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId: Found {} matching ratings.", matchingRatings.size());
        return matchingRatings;
    }

    public boolean existsByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId(
            String causalityTaskId, String reviewerId, String influencingCriteriaId, String influencedCriteriaId) throws IOException {
        List<CausalityRating> matchingRatings = findByCausalityTaskIdAndReviewerIdAndInfluencingCriteriaIdAndInfluencedCriteriaId(
                causalityTaskId, reviewerId, influencingCriteriaId, influencedCriteriaId
        );
        return !matchingRatings.isEmpty();
    }

    public boolean deleteById(String ratingId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, ratingId);
        logger.info("CausalityRatingRepository - deleteById: Deleted rating with ID {}", ratingId);
        return true;
    }
}