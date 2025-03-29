package com.farmer.farmermanagement.mapper;

import com.farmer.farmermanagement.dto.FarmerDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;

import java.util.List;
import java.util.stream.Collectors;

public class FarmerMapper {

  
    public static FarmerDTO toFarmerDTO(Farmer farmer) {
        FarmerDTO dto = new FarmerDTO();
        dto.setId(farmer.getId());
        dto.setSalutation(farmer.getSalutation());
        dto.setFirstName(farmer.getFirstName());
        dto.setMiddleName(farmer.getMiddleName());
        dto.setLastName(farmer.getLastName());
        dto.setGender(farmer.getGender());
        dto.setNationality(farmer.getNationality());
        dto.setDob(farmer.getDob());
        dto.setContactNumber(farmer.getContactNumber());
        dto.setCountry(farmer.getCountry());
        dto.setState(farmer.getState());
        dto.setDistrict(farmer.getDistrict());
        dto.setBlock(farmer.getBlock());
        dto.setVillage(farmer.getVillage());
        dto.setZipcode(farmer.getZipcode());
        dto.setEducation(farmer.getEducation());
        dto.setFarmingExperience(farmer.getFarmingExperience());
        dto.setNetIncome(farmer.getNetIncome());
        dto.setPortalRole(farmer.getPortalRole());
        dto.setPortalAccess(farmer.getPortalAccess());

        if (farmer.getBankDetails() != null) {
            dto.setBankDetailsId(farmer.getBankDetails().getId()); 
        }
        if (farmer.getLandDetails() != null) {
            dto.setLandDetailsId(farmer.getLandDetails().getId()); 
        }
        if (farmer.getCrops() != null) {
            dto.setCropIds(farmer.getCrops()
                .stream()
                .map(crop -> crop.getId())  
                .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Farmer toFarmerEntity(FarmerDTO dto) {
        Farmer farmer = new Farmer();
        farmer.setId(dto.getId());
        farmer.setSalutation(dto.getSalutation());
        farmer.setFirstName(dto.getFirstName());
        farmer.setMiddleName(dto.getMiddleName());
        farmer.setLastName(dto.getLastName());
        farmer.setGender(dto.getGender());
        farmer.setNationality(dto.getNationality());
        farmer.setDob(dto.getDob());
        farmer.setContactNumber(dto.getContactNumber());
        farmer.setCountry(dto.getCountry());
        farmer.setState(dto.getState());
        farmer.setDistrict(dto.getDistrict());
        farmer.setBlock(dto.getBlock());
        farmer.setVillage(dto.getVillage());
        farmer.setZipcode(dto.getZipcode());
        farmer.setEducation(dto.getEducation());
        farmer.setFarmingExperience(dto.getFarmingExperience());
        farmer.setNetIncome(dto.getNetIncome());
        farmer.setPortalRole(dto.getPortalRole());
        farmer.setPortalAccess(dto.getPortalAccess());

        if (dto.getBankDetailsId() != null) {
            BankDetails bankDetails = new BankDetails();
            bankDetails.setId(dto.getBankDetailsId());
            farmer.setBankDetails(bankDetails);
        }
        if (dto.getLandDetailsId() != null) {
            LandDetails landDetails = new LandDetails();
            landDetails.setId(dto.getLandDetailsId());
            farmer.setLandDetails(landDetails);
        }
        if (dto.getCropIds() != null) {
            List<Crop> crops = dto.getCropIds()
                .stream()
                .map(id -> {
                    Crop crop = new Crop();
                    crop.setId(id);
                    return crop;
                })
                .collect(Collectors.toList());
            farmer.setCrops(crops);
        }

        return farmer;
    }
}
