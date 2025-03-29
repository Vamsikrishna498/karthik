package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.FarmerDTO;
import com.farmer.farmermanagement.entity.BankDetails;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.mapper.FarmerMapper;
import com.farmer.farmermanagement.repository.BankDetailsRepository;
import com.farmer.farmermanagement.repository.CropRepository;
import com.farmer.farmermanagement.repository.FarmerRepository;
import com.farmer.farmermanagement.repository.LandDetailsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final BankDetailsRepository bankDetailsRepository;
    private final LandDetailsRepository landDetailsRepository;
    private final CropRepository cropRepository;

    
    public FarmerDTO registerFarmer(@Valid FarmerDTO farmerDTO) {
        Farmer farmer = FarmerMapper.toFarmerEntity(farmerDTO);

        if (farmerDTO.getBankDetailsId() != null) {
            BankDetails bankDetails = bankDetailsRepository.findById(farmerDTO.getBankDetailsId())
                    .orElseThrow(() -> new FarmerNotFoundException("Bank details not found"));
            farmer.setBankDetails(bankDetails);
        }

        if (farmerDTO.getLandDetailsId() != null) {
            LandDetails landDetails = landDetailsRepository.findById(farmerDTO.getLandDetailsId())
                    .orElseThrow(() -> new FarmerNotFoundException("Land details not found"));
            farmer.setLandDetails(landDetails);
        }

        if (farmerDTO.getCropIds() != null) {
            List<Crop> crops = cropRepository.findAllById(farmerDTO.getCropIds());
            farmer.setCrops(crops);
        }

        Farmer savedFarmer = farmerRepository.save(farmer);
        return FarmerMapper.toFarmerDTO(savedFarmer);
    }

   
    public FarmerDTO getFarmerById(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + id));
        return FarmerMapper.toFarmerDTO(farmer);
    }

   
    public FarmerDTO updateFarmer(Long id, @Valid FarmerDTO farmerDTO) {
        Farmer existingFarmer = farmerRepository.findById(id)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + id));

       
        existingFarmer.setSalutation(farmerDTO.getSalutation());
        existingFarmer.setFirstName(farmerDTO.getFirstName());
        existingFarmer.setMiddleName(farmerDTO.getMiddleName());
        existingFarmer.setLastName(farmerDTO.getLastName());
        existingFarmer.setGender(farmerDTO.getGender());
        existingFarmer.setNationality(farmerDTO.getNationality());
        existingFarmer.setDob(farmerDTO.getDob());
        existingFarmer.setContactNumber(farmerDTO.getContactNumber());
        existingFarmer.setCountry(farmerDTO.getCountry());
        existingFarmer.setState(farmerDTO.getState());
        existingFarmer.setDistrict(farmerDTO.getDistrict());
        existingFarmer.setBlock(farmerDTO.getBlock());
        existingFarmer.setVillage(farmerDTO.getVillage());
        existingFarmer.setZipcode(farmerDTO.getZipcode());
        existingFarmer.setEducation(farmerDTO.getEducation());
        existingFarmer.setFarmingExperience(farmerDTO.getFarmingExperience());
        existingFarmer.setNetIncome(farmerDTO.getNetIncome());
        existingFarmer.setPortalRole(farmerDTO.getPortalRole());
        existingFarmer.setPortalAccess(farmerDTO.getPortalAccess());

        if (farmerDTO.getBankDetailsId() != null) {
            BankDetails bankDetails = bankDetailsRepository.findById(farmerDTO.getBankDetailsId())
                    .orElseThrow(() -> new FarmerNotFoundException("Bank details not found"));
            existingFarmer.setBankDetails(bankDetails);
        }

        if (farmerDTO.getLandDetailsId() != null) {
            LandDetails landDetails = landDetailsRepository.findById(farmerDTO.getLandDetailsId())
                    .orElseThrow(() -> new FarmerNotFoundException("Land details not found"));
            existingFarmer.setLandDetails(landDetails);
        }

        if (farmerDTO.getCropIds() != null) {
            List<Crop> crops = cropRepository.findAllById(farmerDTO.getCropIds());
            existingFarmer.setCrops(crops);
        }

        Farmer updatedFarmer = farmerRepository.save(existingFarmer);
        return FarmerMapper.toFarmerDTO(updatedFarmer);
    }

    public List<FarmerDTO> getAllFarmers() {
        List<Farmer> farmers = farmerRepository.findAll();
        return farmers.stream()
                .map(FarmerMapper::toFarmerDTO)
                .collect(Collectors.toList());
    }

    public void deleteFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + id));
        farmerRepository.delete(farmer);
    }
}
