package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.BankDetailsDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.enums.BankName;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.BankDetailsMapper;
import com.farmer.farmermanagement.repository.BankDetailsRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class BankDetailsServiceTest {

    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private BankDetailsMapper bankDetailsMapper;

    @InjectMocks
    private BankDetailsService bankDetailsService;

    private Farmer farmer;
    private BankDetails bankDetails;
    private BankDetailsDTO bankDetailsDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        farmer = new Farmer();
        farmer.setId(1L);

        bankDetails = new BankDetails();
        bankDetails.setId(1L);
        bankDetails.setBankName(BankName.HDFC_BANK);
        bankDetails.setAccountNumber("1234567890");
        bankDetails.setBranchName("Branch A");
        bankDetails.setIfscCode("IFSC0001");
        bankDetails.setFarmer(farmer);

        bankDetailsDTO = new BankDetailsDTO();
        bankDetailsDTO.setBankName(BankName.HDFC_BANK);
        bankDetailsDTO.setAccountNumber("1234567890");
        bankDetailsDTO.setBranchName("Branch A");
        bankDetailsDTO.setIfscCode("IFSC0001");
        bankDetailsDTO.setFarmerId(farmer.getId());
    }

    @Test
    void addOrUpdateBankDetailsShouldReturnBankDetailsDTO() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.of(farmer));
        when(bankDetailsRepository.findByFarmerId(farmer.getId())).thenReturn(Optional.empty());
        when(bankDetailsRepository.save(any(BankDetails.class))).thenReturn(bankDetails);
        when(bankDetailsMapper.toDTO(bankDetails)).thenReturn(bankDetailsDTO);

        BankDetailsDTO result = bankDetailsService.addOrUpdateBankDetails(bankDetailsDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bankDetailsDTO.getBankName(), result.getBankName());
        Assertions.assertEquals(bankDetailsDTO.getAccountNumber(), result.getAccountNumber());
        verify(bankDetailsRepository).save(any(BankDetails.class));
    }

    @Test
    void addOrUpdateBankDetailsFarmerNotFoundShouldThrowFarmerNotFoundException() {
        when(farmerRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(FarmerNotFoundException.class, () -> bankDetailsService.addOrUpdateBankDetails(bankDetailsDTO));
    }

    @Test
    void getBankDetailsByFarmerIdShouldReturnBankDetailsDTO() {
        when(bankDetailsRepository.findByFarmerId(1L)).thenReturn(Optional.of(bankDetails));
        when(bankDetailsMapper.toDTO(bankDetails)).thenReturn(bankDetailsDTO);

        BankDetailsDTO result = bankDetailsService.getBankDetailsByFarmerId(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bankDetailsDTO.getBankName(), result.getBankName());
        verify(bankDetailsRepository).findByFarmerId(1L);
    }

    @Test
    void getBankDetailsByFarmerIdBankDetailsNotFoundShouldThrowResourceNotFoundException() {
        when(bankDetailsRepository.findByFarmerId(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> bankDetailsService.getBankDetailsByFarmerId(1L));
    }

    @Test
    void deleteBankDetailsShouldDeleteBankDetails() {
        when(bankDetailsRepository.findById(1L)).thenReturn(Optional.of(bankDetails));

        bankDetailsService.deleteBankDetails(1L);

        verify(bankDetailsRepository).delete(bankDetails);
    }

    @Test
    void deleteBankDetailsBankDetailsNotFoundShouldThrowResourceNotFoundException() {
        when(bankDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> bankDetailsService.deleteBankDetails(1L));
    }
}

