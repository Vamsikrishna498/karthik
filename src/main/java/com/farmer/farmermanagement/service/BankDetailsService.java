package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.BankDetailsDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.BankDetailsMapper;
import com.farmer.farmermanagement.repository.BankDetailsRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;
    private final FarmerRepository farmerRepository;
    private final BankDetailsMapper bankDetailsMapper;

    @Transactional
    public BankDetailsDTO addOrUpdateBankDetails(BankDetailsDTO bankDetailsDTO) {
        Farmer farmer = farmerRepository.findById(bankDetailsDTO.getFarmerId())
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + bankDetailsDTO.getFarmerId()));

        BankDetails bankDetails = bankDetailsRepository.findByFarmerId(farmer.getId())
                .orElse(new BankDetails());

        bankDetails.setBankName(bankDetailsDTO.getBankName());
        bankDetails.setAccountNumber(bankDetailsDTO.getAccountNumber());
        bankDetails.setBranchName(bankDetailsDTO.getBranchName());
        bankDetails.setIfscCode(bankDetailsDTO.getIfscCode());
        bankDetails.setPassbookAttachment(bankDetailsDTO.getPassbookAttachment());
        bankDetails.setFarmer(farmer);

        bankDetails = bankDetailsRepository.save(bankDetails);
        return bankDetailsMapper.toDTO(bankDetails);
    }

    public BankDetailsDTO getBankDetailsByFarmerId(Long farmerId) {
        BankDetails bankDetails = bankDetailsRepository.findByFarmerId(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found for farmer ID: " + farmerId));
        return bankDetailsMapper.toDTO(bankDetails);
    }

    public void deleteBankDetails(Long id) {
        BankDetails bankDetails = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank details not found with ID: " + id));
        bankDetailsRepository.delete(bankDetails);
    }
}
