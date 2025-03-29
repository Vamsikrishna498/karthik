package com.farmer.farmermanagement.controller;

import com.farmer.farmermanagement.dto.FarmerDTO;
import com.farmer.farmermanagement.service.FarmerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

  
    @PostMapping
    public ResponseEntity<FarmerDTO> registerFarmer(@Valid @RequestBody FarmerDTO farmerDTO) {
        FarmerDTO savedFarmer = farmerService.registerFarmer(farmerDTO);
        return ResponseEntity.ok(savedFarmer);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<FarmerDTO> getFarmerById(@PathVariable Long id) {
        FarmerDTO farmer = farmerService.getFarmerById(id);
        return ResponseEntity.ok(farmer);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<FarmerDTO> updateFarmer(@PathVariable Long id, @Valid @RequestBody FarmerDTO farmerDTO) {
        FarmerDTO updatedFarmer = farmerService.updateFarmer(id, farmerDTO);
        return ResponseEntity.ok(updatedFarmer);
    }

   
    @GetMapping
    public ResponseEntity<List<FarmerDTO>> getAllFarmers() {
        List<FarmerDTO> farmers = farmerService.getAllFarmers();
        return ResponseEntity.ok(farmers);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}
