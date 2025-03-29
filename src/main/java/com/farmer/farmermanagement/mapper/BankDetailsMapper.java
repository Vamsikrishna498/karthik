package com.farmer.farmermanagement.mapper;

import com.farmer.farmermanagement.dto.BankDetailsDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Farmer;
import org.springframework.stereotype.Component;

@Component
public class BankDetailsMapper {

    public BankDetails toEntity(BankDetailsDTO dto, Farmer farmer) {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(dto.getId());
        bankDetails.setBankName(dto.getBankName());
        bankDetails.setAccountNumber(dto.getAccountNumber());
        bankDetails.setBranchName(dto.getBranchName());
        bankDetails.setIfscCode(dto.getIfscCode());
        bankDetails.setPassbookAttachment(dto.getPassbookAttachment());
        bankDetails.setFarmer(farmer);
        return bankDetails;
    }

    public BankDetailsDTO toDTO(BankDetails bankDetails) {
        BankDetailsDTO dto = new BankDetailsDTO();
        dto.setId(bankDetails.getId());
        dto.setBankName(bankDetails.getBankName());
        dto.setAccountNumber(bankDetails.getAccountNumber());
        dto.setBranchName(bankDetails.getBranchName());
        dto.setIfscCode(bankDetails.getIfscCode());
        dto.setPassbookAttachment(bankDetails.getPassbookAttachment());
        dto.setFarmerId(bankDetails.getFarmer().getId());
        return dto;
    }
}
