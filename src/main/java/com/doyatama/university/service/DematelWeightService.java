package com.doyatama.university.service;

import com.doyatama.university.model.DematelCriteriaWeight;
import com.doyatama.university.repository.DematelCriteriaWeightRepository;
import com.doyatama.university.repository.CausalityRepository;
import com.doyatama.university.model.Causality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map; // Import Map
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DematelWeightService {

    private final DematelCriteriaWeightRepository dematelWeightRepository;
    private final CausalityRepository causalityRepository;

    @Autowired
    public DematelWeightService(DematelCriteriaWeightRepository dematelWeightRepository, CausalityRepository causalityRepository) {
        this.dematelWeightRepository = dematelWeightRepository;
        this.causalityRepository = causalityRepository;
    }

    /**
     * Menyimpan atau memperbarui bobot DEMATEL untuk suatu Causality.
     * Menerima list Map<String, Object> sebagai pengganti list DTO.
     *
     * @param causalityId ID kausalitas.
     * @param weightEntriesMapList List Map yang berisi "criterionId" dan "normalizedWeight".
     * @return List objek DematelCriteriaWeight yang sudah disimpan.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     * @throws RuntimeException Jika kausalitas tidak ditemukan atau data bobot tidak valid.
     */
    @Transactional
    public List<DematelCriteriaWeight> saveOrUpdateDematelWeights(String causalityId, List<Map<String, Object>> weightEntriesMapList) throws IOException {
        Causality causality = causalityRepository.findById(causalityId);
        if (causality == null) {
            throw new RuntimeException("Causality not found for ID: " + causalityId);
        }

        // getSubject() mengembalikan String yang sudah merupakan subject ID
        String subjectId = causality.getSubject();

        dematelWeightRepository.deleteByCausalityId(causalityId);

        List<DematelCriteriaWeight> savedWeights = weightEntriesMapList.stream()
                .map(entryMap -> {
                    // Ekstrak data secara manual dari Map
                    String criterionId = (String) entryMap.get("criterionId");
                    Double normalizedWeight = null;
                    Object weightObj = entryMap.get("normalizedWeight");
                    if (weightObj instanceof Number) {
                        normalizedWeight = ((Number) weightObj).doubleValue();
                    } else if (weightObj instanceof String) {
                        try {
                            normalizedWeight = Double.parseDouble((String) weightObj);
                        } catch (NumberFormatException e) {
                            // Log error atau throw exception jika format tidak valid
                            throw new IllegalArgumentException("Normalized weight format is invalid for criterion: " + criterionId);
                        }
                    }

                    if (criterionId == null || criterionId.isEmpty() || normalizedWeight == null) {
                        throw new IllegalArgumentException("Criterion ID or Normalized Weight cannot be null or empty in entry.");
                    }

                    DematelCriteriaWeight dematelWeight = new DematelCriteriaWeight(
                            null, // ID akan diisi di repository (dari RowKey)
                            causalityId,
                            subjectId,
                            criterionId,
                            normalizedWeight
                    );
                    try {
                        return dematelWeightRepository.save(dematelWeight);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save weight for criterion " + criterionId + ": " + e.getMessage(), e);
                    }
                })
                .collect(Collectors.toList());

        return savedWeights;
    }

    /**
     * Mengambil bobot DEMATEL berdasarkan Subject ID.
     *
     * @param subjectId ID mata kuliah.
     * @return List bobot kriteria untuk subjek tersebut.
     * @throws IOException Jika terjadi kesalahan I/O saat berinteraksi dengan HBase.
     */
    public List<DematelCriteriaWeight> getDematelWeightsBySubjectId(String subjectId) throws IOException {
        return dematelWeightRepository.findBySubjectId(subjectId);
    }
}