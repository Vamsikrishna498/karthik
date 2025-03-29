package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.CropDTO;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.CropMapper;
import com.farmer.farmermanagement.repository.CropRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropService {

    private final CropRepository cropRepository;
    private final FarmerRepository farmerRepository;
    private final CropMapper cropMapper;

    public CropDTO addCrop(CropDTO cropDTO) {
        Farmer farmer = farmerRepository.findById(cropDTO.getFarmerId())
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + cropDTO.getFarmerId()));

        Crop crop = cropMapper.toCropEntity(cropDTO, farmer);
        crop = cropRepository.save(crop);
        return cropMapper.toCropDTO(crop);
    }

    public CropDTO getCropById(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with ID: " + id));
        return cropMapper.toCropDTO(crop);
    }

    public List<CropDTO> getCropsByFarmerId(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId)
                .stream()
                .map(cropMapper::toCropDTO)
                .collect(Collectors.toList());
    }

    public void deleteCrop(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with ID: " + id));
        cropRepository.delete(crop);
    }
}
