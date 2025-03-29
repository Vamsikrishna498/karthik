package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.LandDetailsDTO;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;
import com.farmer.farmermanagement.exception.FarmerNotFoundException;
import com.farmer.farmermanagement.exception.ResourceNotFoundException;
import com.farmer.farmermanagement.mapper.LandDetailsMapper;
import com.farmer.farmermanagement.repository.FarmerRepository;
import com.farmer.farmermanagement.repository.LandDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LandDetailsService {

    private final LandDetailsRepository landDetailsRepository;
    private final FarmerRepository farmerRepository;
    private final LandDetailsMapper landDetailsMapper;

    @Transactional
    public LandDetailsDTO addOrUpdateLandDetails(LandDetailsDTO landDetailsDTO) {
        Farmer farmer = farmerRepository.findById(landDetailsDTO.getFarmerId())
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with ID: " + landDetailsDTO.getFarmerId()));

        LandDetails landDetails = landDetailsRepository.findByFarmerId(farmer.getId())
                .orElse(new LandDetails());

        landDetails.setSurveyNumber(landDetailsDTO.getSurveyNumber());
        landDetails.setLandSize(landDetailsDTO.getLandSize());
        landDetails.setCropType(landDetailsDTO.getCropType());
        landDetails.setSoilTest(landDetailsDTO.getSoilTest());
        landDetails.setSoilTestCertificate(landDetailsDTO.getSoilTestCertificate());
        landDetails.setGeoTag(landDetailsDTO.getGeoTag());
        landDetails.setLatitude(landDetailsDTO.getLatitude());
        landDetails.setLongitude(landDetailsDTO.getLongitude());
        landDetails.setIrrigationSource(landDetailsDTO.getIrrigationSource());
        landDetails.setBorewellDischarge(landDetailsDTO.getBorewellDischarge());
        landDetails.setBorewellLocation(landDetailsDTO.getBorewellLocation());
        landDetails.setFarmer(farmer);

        landDetails = landDetailsRepository.save(landDetails);
        return landDetailsMapper.toDTO(landDetails);
    }

    public LandDetailsDTO getLandDetailsByFarmerId(Long farmerId) {
        LandDetails landDetails = landDetailsRepository.findByFarmerId(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Land details not found for farmer ID: " + farmerId));
        return landDetailsMapper.toDTO(landDetails);
    }

    public void deleteLandDetails(Long id) {
        LandDetails landDetails = landDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Land details not found with ID: " + id));
        landDetailsRepository.delete(landDetails);
    }
}
