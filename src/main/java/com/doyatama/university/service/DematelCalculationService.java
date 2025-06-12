package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.Causality;
import com.doyatama.university.model.CausalityRating;
import com.doyatama.university.model.QuestionCriteria;
import com.doyatama.university.repository.CausalityRepository;
import com.doyatama.university.repository.CausalityRatingRepository;
import com.doyatama.university.repository.QuestionCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DematelCalculationService {

    @Autowired
    private CausalityRepository causalityRepository;

    @Autowired
    private CausalityRatingRepository causalityRatingRepository;

    @Autowired
    private QuestionCriteriaRepository questionCriteriaRepository;

    private static final Logger logger = LoggerFactory.getLogger(DematelCalculationService.class);

    public Map<String, Double> calculateAndApplyWeights(String causalityTaskId) throws IOException {
        logger.info("Starting DEMATEL calculation for Causality Task ID: {}", causalityTaskId);

        Causality causalityTask = causalityRepository.findById(causalityTaskId);
        if (causalityTask == null) {
            throw new ResourceNotFoundException("Causality Task", "id", causalityTaskId);
        }
        if (!"Completed".equals(causalityTask.getStatus())) {
            throw new BadRequestException("Causality Task with ID " + causalityTaskId + " is not 'Completed'. Please ensure all ratings are submitted.");
        }

        List<CausalityRating> ratings = causalityRatingRepository.findByCausalityTaskId(causalityTaskId);
        if (ratings == null || ratings.isEmpty()) {
            throw new BadRequestException("No ratings found for Causality Task ID: " + causalityTaskId);
        }

        List<String> criteriaIds = causalityTask.getCriteriaIds();
        if (criteriaIds == null || criteriaIds.isEmpty()) {
            throw new BadRequestException("Causality Task ID " + causalityTaskId + " has no criteria defined.");
        }
        criteriaIds.sort(String::compareTo);

        Map<String, Map<String, Double>> directRelationMatrixMap = buildDirectRelationMatrix(criteriaIds, ratings);

        // --- Contoh Placeholder untuk Prominence Scores tanpa perhitungan DEMATEL sesungguhnya ---
        Map<String, Double> prominenceScores = new HashMap<>();
        for (String id : criteriaIds) {
            prominenceScores.put(id, Math.random() * 100);
        }
        logger.info("Generated dummy prominence scores: {}", prominenceScores);
        // --- END Placeholder ---

        Map<String, Double> calculatedWeights = calculateWeightsFromProminence(prominenceScores);
        logger.info("Calculated weights for criteria: {}", calculatedWeights);

        applyWeightsToQuestionCriteria(calculatedWeights);

        logger.info("DEMATEL calculation and weight application completed for Causality Task ID: {}", causalityTaskId);
        return calculatedWeights;
    }

    private Map<String, Map<String, Double>> buildDirectRelationMatrix(List<String> criteriaIds, List<CausalityRating> ratings) {
        Map<String, Map<String, Double>> directMatrixSum = new HashMap<>();
        Map<String, Map<String, Integer>> directMatrixCount = new HashMap<>();

        for (String rowId : criteriaIds) {
            directMatrixSum.put(rowId, new HashMap<>());
            directMatrixCount.put(rowId, new HashMap<>());
            for (String colId : criteriaIds) {
                if (!rowId.equals(colId)) {
                    directMatrixSum.get(rowId).put(colId, 0.0);
                    directMatrixCount.get(rowId).put(colId, 0);
                }
            }
        }

        for (CausalityRating rating : ratings) {
            String influencing = rating.getInfluencingCriteriaId();
            String influenced = rating.getInfluencedCriteriaId();
            Double value = rating.getNumericRatingValue(); // <-- PERBAIKAN: Mengambil numericRatingValue

            if (value != null && influencing != null && influenced != null &&
                directMatrixSum.containsKey(influencing) && directMatrixSum.get(influencing).containsKey(influenced)) {
                directMatrixSum.get(influencing).put(influenced, directMatrixSum.get(influencing).get(influenced) + value);
                directMatrixCount.get(influencing).put(influenced, directMatrixCount.get(influencing).get(influenced) + 1);
            }
        }

        Map<String, Map<String, Double>> averagedMatrix = new HashMap<>();
        for (String rowId : criteriaIds) {
            averagedMatrix.put(rowId, new HashMap<>());
            for (String colId : criteriaIds) {
                if (!rowId.equals(colId)) {
                    double sum = directMatrixSum.get(rowId).get(colId);
                    int count = directMatrixCount.get(rowId).get(colId);
                    averagedMatrix.get(rowId).put(colId, count > 0 ? sum / count : 0.0);
                } else {
                    averagedMatrix.get(rowId).put(colId, 0.0); // Kriteria terhadap dirinya sendiri selalu 0
                }
            }
        }
        logger.debug("Built averaged direct relation matrix: {}", averagedMatrix);
        return averagedMatrix;
    }

    private Map<String, Double> calculateWeightsFromProminence(Map<String, Double> prominenceScores) {
        double totalProminence = prominenceScores.values().stream().mapToDouble(Double::doubleValue).sum();

        Map<String, Double> weights = new HashMap<>();
        if (totalProminence > 0) {
            for (Map.Entry<String, Double> entry : prominenceScores.entrySet()) {
                weights.put(entry.getKey(), entry.getValue() / totalProminence);
            }
        } else {
            double equalWeight = 1.0 / prominenceScores.size();
            for (String criteriaId : prominenceScores.keySet()) {
                weights.put(criteriaId, equalWeight);
            }
        }
        return weights;
    }

    private void applyWeightsToQuestionCriteria(Map<String, Double> calculatedWeights) throws IOException {
        for (Map.Entry<String, Double> entry : calculatedWeights.entrySet()) {
            String criteriaId = entry.getKey();
            Double weight = entry.getValue();

            QuestionCriteria criteria = questionCriteriaRepository.findById(criteriaId);
            if (criteria != null) {
                criteria.setWeight(weight);
                questionCriteriaRepository.save(criteria);
                logger.info("Updated QuestionCriteria ID {} with new weight: {}", criteriaId, weight);
            } else {
                logger.warn("QuestionCriteria with ID {} not found. Cannot apply weight.", criteriaId);
            }
        }
    }
}