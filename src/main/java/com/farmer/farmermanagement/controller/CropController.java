package com.farmer.farmermanagement.controller;

import com.farmer.farmermanagement.dto.CropDTO;
import com.farmer.farmermanagement.service.CropService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping
    public ResponseEntity<CropDTO> addCrop(@Valid @RequestBody CropDTO cropDTO) {
        return ResponseEntity.ok(cropService.addCrop(cropDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropDTO> getCropById(@PathVariable Long id) {
        return ResponseEntity.ok(cropService.getCropById(id));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<CropDTO>> getCropsByFarmerId(@PathVariable Long farmerId) {
        return ResponseEntity.ok(cropService.getCropsByFarmerId(farmerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}
