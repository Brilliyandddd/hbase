package com.doyatama.university.controller;

// Hapus import untuk DTO request
// import com.doyatama.university.dto.SaveDematelWeightsRequest;

import com.doyatama.university.model.DematelCriteriaWeight; // Model untuk response GET bobot
import com.doyatama.university.service.DematelWeightService; // Service yang akan dipanggil

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map; // Import Map untuk menerima payload generik
import java.util.stream.Collectors; // Untuk Stream API
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/causality")
public class DematelWeightController {

    private final DematelWeightService dematelWeightService;

    @Autowired
    public DematelWeightController(DematelWeightService dematelWeightService) {
        this.dematelWeightService = dematelWeightService;
    }

    /**
     * Endpoint untuk menyimpan atau memperbarui bobot DEMATEL yang dihitung dari frontend.
     * Menerima payload JSON sebagai Map<String, Object> karena tidak menggunakan DTO.
     * URL: PUT /api/causality/dematel-weights
     *
     * @param requestBody Map yang berisi "causalityId", "subjectId", dan "weights".
     * @return ResponseEntity dengan pesan sukses atau error.
     */
    @PutMapping("/dematel-weights")
    public ResponseEntity<Map<String, String>> saveDematelWeights(@RequestBody Map<String, Object> requestBody) {
        try {
            String causalityId = (String) requestBody.get("causalityId");
            String subjectIdFromFrontend = (String) requestBody.get("subjectId");
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> weightsMapList = (List<Map<String, Object>>) requestBody.get("weights");

            if (causalityId == null || causalityId.isEmpty() || subjectIdFromFrontend == null || subjectIdFromFrontend.isEmpty() || weightsMapList == null || weightsMapList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Payload tidak valid. causalityId, subjectId, dan weights tidak boleh kosong."));
            }

            dematelWeightService.saveOrUpdateDematelWeights(causalityId, weightsMapList); 
            
            return ResponseEntity.ok(Map.of("message", "Bobot DEMATEL berhasil disimpan."));
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Format payload tidak sesuai dengan yang diharapkan. Detail: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Terjadi kesalahan I/O saat menyimpan bobot: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Terjadi kesalahan internal server: " + e.getMessage()));
        }
    }

    /**
     * Endpoint untuk mengambil bobot DEMATEL berdasarkan Subject ID.
     * URL: GET /api/causality/dematel-weights/by-subject/{subjectId}
     *
     * @param subjectId ID mata kuliah yang diambil dari path URL.
     * @return ResponseEntity dengan list bobot kriteria atau 404 jika tidak ditemukan.
     */
    @GetMapping("/dematel-weights/by-subject/{subjectId}")
    public ResponseEntity<List<DematelCriteriaWeight>> getDematelWeightsBySubject(@PathVariable String subjectId) {
        try {
            if (subjectId == null || subjectId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
            }
            List<DematelCriteriaWeight> weights = dematelWeightService.getDematelWeightsBySubjectId(subjectId);
            
            if (weights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
            }
            return ResponseEntity.ok(weights);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}