package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.CropDTO;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.CropMapper;
import com.farmer.farmermanagement.repository.CropRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CropServiceTest {

    @Mock
    private CropRepository cropRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private CropMapper cropMapper;

    @InjectMocks
    private CropService cropService;

    private CropDTO cropDTO;
    private Crop crop;
    private Farmer farmer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Updated to use firstName, middleName, and lastName
        farmer = new Farmer();
        farmer.setId(1L);
        farmer.setFirstName("John");
        farmer.setMiddleName("Doe");
        farmer.setLastName("Smith");

        cropDTO = new CropDTO();
        cropDTO.setId(1L);
        cropDTO.setFarmerId(1L);
        cropDTO.setCropName("Cotton");

        crop = new Crop();
        crop.setId(1L);
        crop.setFarmer(farmer);
        crop.setCropName("Cotton");
    }

    @Test
    void addCropshouldReturnCropDTO() {
        // Arrange
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        when(cropMapper.toCropEntity(cropDTO, farmer)).thenReturn(crop);
        when(cropRepository.save(crop)).thenReturn(crop);
        when(cropMapper.toCropDTO(crop)).thenReturn(cropDTO);

        // Act
        CropDTO result = cropService.addCrop(cropDTO);

        // Assert
        assertNotNull(result);
        assertEquals(cropDTO.getCropName(), result.getCropName());
        verify(farmerRepository).findById(1L);
        verify(cropRepository).save(crop);
    }

    @Test
    void addCropFarmerNotFoundShouldThrowFarmerNotFoundException() {
        // Arrange
        when(farmerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FarmerNotFoundException.class, () -> cropService.addCrop(cropDTO));
    }

    @Test
    void getCropByIdShouldReturnCropDTO() {
        // Arrange
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));
        when(cropMapper.toCropDTO(crop)).thenReturn(cropDTO);

        // Act
        CropDTO result = cropService.getCropById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(cropDTO.getId(), result.getId());
        verify(cropRepository).findById(1L);
    }

    @Test
    void getCropByIdCropNotFoundShouldThrowResourceNotFoundException() {
        // Arrange
        when(cropRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> cropService.getCropById(1L));
    }

    @Test
    void getCropsByFarmerIdShouldReturnListOfCropDTOs() {
        // Arrange
        when(cropRepository.findByFarmerId(1L)).thenReturn(List.of(crop));
        when(cropMapper.toCropDTO(crop)).thenReturn(cropDTO);

        // Act
        List<CropDTO> result = cropService.getCropsByFarmerId(1L);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(cropRepository).findByFarmerId(1L);
    }

    @Test
    void deleteCropShouldDeleteCrop() {
        // Arrange
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));

        // Act
        cropService.deleteCrop(1L);

        // Assert
        verify(cropRepository).delete(crop);
    }

    @Test
    void deleteCropCropNotFoundShouldThrowResourceNotFoundException() {
        // Arrange
        when(cropRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> cropService.deleteCrop(1L));
    }
}
