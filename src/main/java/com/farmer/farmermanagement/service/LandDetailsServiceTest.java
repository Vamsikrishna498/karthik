package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.LandDetailsDTO;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;
import com.farmer.farmermanagement.enums.CropType;
import com.farmer.farmermanagement.enums.IrrigationSource;
import com.farmer.farmermanagement.enums.SoilTest;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.LandDetailsMapper;
import com.farmer.farmermanagement.repository.FarmerRepository;
import com.farmer.farmermanagement.repository.LandDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LandDetailsServiceTest {

    @Mock
    private LandDetailsRepository landDetailsRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private LandDetailsMapper landDetailsMapper;

    @InjectMocks
    private LandDetailsService landDetailsService;

    private LandDetailsDTO landDetailsDTO;
    private LandDetails landDetails;
    private Farmer farmer;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(1L);
        farmer.setFirstName("John");
        farmer.setMiddleName("A.");
        farmer.setLastName("Doe");

        landDetailsDTO = new LandDetailsDTO();
        landDetailsDTO.setSurveyNumber("1234");
        landDetailsDTO.setLandSize(5.0);
        landDetailsDTO.setCropType(CropType.COTTON);
        landDetailsDTO.setSoilTest(SoilTest.YES);
        landDetailsDTO.setSoilTestCertificate("Certificate123");
        landDetailsDTO.setGeoTag("GeoTag123");
        landDetailsDTO.setLatitude(12.34);
        landDetailsDTO.setLongitude(56.78);
        landDetailsDTO.setIrrigationSource(IrrigationSource.CANAL);
        landDetailsDTO.setBorewellDischarge("10L");
        landDetailsDTO.setBorewellLocation("Near Pond");
        landDetailsDTO.setFarmerId(1L);

        landDetails = new LandDetails();
        landDetails.setSurveyNumber("1234");
        landDetails.setLandSize(5.0);
        landDetails.setCropType(CropType.COTTON);
        landDetails.setSoilTest(SoilTest.YES);
        landDetails.setSoilTestCertificate("Certificate123");
        landDetails.setGeoTag("GeoTag123");
        landDetails.setLatitude(12.34);
        landDetails.setLongitude(56.78);
        landDetails.setIrrigationSource(IrrigationSource.CANAL);
        landDetails.setBorewellDischarge("10L");
        landDetails.setBorewellLocation("Near Pond");
        landDetails.setFarmer(farmer);
    }

    @Test
    void addOrUpdateLandDetailsShouldReturnLandDetailsDTO() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        when(landDetailsRepository.findByFarmerId(1L)).thenReturn(Optional.empty());
        when(landDetailsRepository.save(any(LandDetails.class))).thenReturn(landDetails);
        when(landDetailsMapper.toDTO(landDetails)).thenReturn(landDetailsDTO);

        LandDetailsDTO result = landDetailsService.addOrUpdateLandDetails(landDetailsDTO);

        assertNotNull(result);
        assertEquals(CropType.COTTON, result.getCropType());
        assertEquals(SoilTest.YES, result.getSoilTest());
        assertEquals(IrrigationSource.CANAL, result.getIrrigationSource());
        verify(landDetailsRepository).save(any(LandDetails.class));
    }

    @Test
    void addOrUpdateLandDetailsFarmerNotFoundShouldThrowFarmerNotFoundException() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FarmerNotFoundException.class, () -> landDetailsService.addOrUpdateLandDetails(landDetailsDTO));
    }

    @Test
    void getLandDetailsByFarmerIdShouldReturnLandDetailsDTO() {
        when(landDetailsRepository.findByFarmerId(1L)).thenReturn(Optional.of(landDetails));
        when(landDetailsMapper.toDTO(landDetails)).thenReturn(landDetailsDTO);

        LandDetailsDTO result = landDetailsService.getLandDetailsByFarmerId(1L);

        assertNotNull(result);
        assertEquals(landDetailsDTO.getSurveyNumber(), result.getSurveyNumber());
        verify(landDetailsRepository).findByFarmerId(1L);
    }

    @Test
    void getLandDetailsByFarmerIdLandDetailsNotFoundShouldThrowResourceNotFoundException() {
        when(landDetailsRepository.findByFarmerId(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> landDetailsService.getLandDetailsByFarmerId(1L));
    }

    @Test
    void deleteLandDetailsShouldDeleteLandDetails() {
        when(landDetailsRepository.findById(1L)).thenReturn(Optional.of(landDetails));

        landDetailsService.deleteLandDetails(1L);

        verify(landDetailsRepository).delete(landDetails);
    }

    @Test
    void deleteLandDetailsLandDetailsNotFoundShouldThrowResourceNotFoundException() {
        when(landDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> landDetailsService.deleteLandDetails(1L));
    }
}
