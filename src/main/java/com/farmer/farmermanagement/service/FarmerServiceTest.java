package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.FarmerDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;
import com.farmer.farmermanagement.enums.Gender;
import com.farmer.farmermanagement.enums.PortalAccess;
import com.farmer.farmermanagement.enums.PortalRole;
import com.farmer.farmermanagement.enums.Education;
import com.farmer.farmermanagement.enums.Document;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.mapper.FarmerMapper;
import com.farmer.farmermanagement.repository.BankDetailsRepository;
import com.farmer.farmermanagement.repository.CropRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;
import com.farmer.farmermanagement.repository.LandDetailsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmerServiceTest {

    @InjectMocks
    private FarmerService farmerService;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @Mock
    private LandDetailsRepository landDetailsRepository;

    @Mock
    private CropRepository cropRepository;

    private Farmer farmer;
    private FarmerDTO farmerDTO;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();
        farmer.setId(1L);
        farmer.setSalutation("Mr.");
        farmer.setFirstName("John");
        farmer.setLastName("Doe");
        farmer.setGender(Gender.MALE);
        farmer.setNationality("Indian");
        farmer.setDob(new Date());
        farmer.setContactNumber("1234567890");
        farmer.setCountry("India");
        farmer.setState("Karnataka");
        farmer.setDistrict("Bangalore");
        farmer.setVillage("XYZ");
        farmer.setZipcode("560001");
        farmer.setEducation(Education.GRADUATE);
        farmer.setFarmingExperience(5);
        farmer.setNetIncome(50000);
        farmer.setPortalRole(PortalRole.USER);
        farmer.setPortalAccess(PortalAccess.ACTIVE);
        farmer.setDocument(Document.AADHAR_NUMBER);

        farmerDTO = FarmerMapper.toFarmerDTO(farmer);
    }

    @Test
    void testRegisterFarmer() {
        when(farmerRepository.save(any(Farmer.class))).thenReturn(farmer);
        FarmerDTO savedFarmer = farmerService.registerFarmer(farmerDTO);
        assertNotNull(savedFarmer);
        assertEquals("John", savedFarmer.getFirstName());
        verify(farmerRepository, times(1)).save(any(Farmer.class));
    }

    @Test
    void testGetFarmerById() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        FarmerDTO foundFarmer = farmerService.getFarmerById(1L);
        assertNotNull(foundFarmer);
        assertEquals("John", foundFarmer.getFirstName());
        verify(farmerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFarmerByIdNotFound() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FarmerNotFoundException.class, () -> farmerService.getFarmerById(1L));
    }

    @Test
    void testUpdateFarmer() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        when(farmerRepository.save(any(Farmer.class))).thenReturn(farmer);
        
        farmerDTO.setFirstName("Updated Name");
        FarmerDTO updatedFarmer = farmerService.updateFarmer(1L, farmerDTO);

        assertNotNull(updatedFarmer);
        assertEquals("Updated Name", updatedFarmer.getFirstName());
        verify(farmerRepository, times(1)).save(any(Farmer.class));
    }

    @Test
    void testGetAllFarmers() {
        List<Farmer> farmerList = Arrays.asList(farmer);
        when(farmerRepository.findAll()).thenReturn(farmerList);
        List<FarmerDTO> farmers = farmerService.getAllFarmers();
        assertFalse(farmers.isEmpty());
        assertEquals(1, farmers.size());
        verify(farmerRepository, times(1)).findAll();
    }

    @Test
    void testDeleteFarmer() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        doNothing().when(farmerRepository).delete(farmer);
        farmerService.deleteFarmer(1L);
        verify(farmerRepository, times(1)).delete(farmer);
    }

    @Test
    void testDeleteFarmerNotFound() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FarmerNotFoundException.class, () -> farmerService.deleteFarmer(1L));
    }
}
