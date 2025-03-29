package com.farmer.farmermanagement.controller;

import com.farmer.farmermanagement.dto.LandDetailsDTO;
import com.farmer.farmermanagement.service.LandDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/land-details")
@RequiredArgsConstructor
public class LandDetailsController {

    private final LandDetailsService landDetailsService;

    @PostMapping
    public ResponseEntity<LandDetailsDTO> addOrUpdateLandDetails(@Valid @RequestBody LandDetailsDTO landDetailsDTO) {
        return ResponseEntity.ok(landDetailsService.addOrUpdateLandDetails(landDetailsDTO));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<LandDetailsDTO> getLandDetailsByFarmerId(@PathVariable Long farmerId) {
        return ResponseEntity.ok(landDetailsService.getLandDetailsByFarmerId(farmerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandDetails(@PathVariable Long id) {
        landDetailsService.deleteLandDetails(id);
        return ResponseEntity.noContent().build();
    }
}
