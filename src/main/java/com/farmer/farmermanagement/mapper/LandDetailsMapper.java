package com.farmer.farmermanagement.mapper;

import com.farmer.farmermanagement.dto.LandDetailsDTO;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.entity.LandDetails;
import org.springframework.stereotype.Component;

@Component
public class LandDetailsMapper {

    public LandDetails toEntity(LandDetailsDTO dto, Farmer farmer) {
        LandDetails landDetails = new LandDetails();
        landDetails.setId(dto.getId());
        landDetails.setSurveyNumber(dto.getSurveyNumber());
        landDetails.setLandSize(dto.getLandSize());
        landDetails.setCropType(dto.getCropType());
        landDetails.setSoilTest(dto.getSoilTest());
        landDetails.setSoilTestCertificate(dto.getSoilTestCertificate());
        landDetails.setGeoTag(dto.getGeoTag());
        landDetails.setLatitude(dto.getLatitude());
        landDetails.setLongitude(dto.getLongitude());
        landDetails.setIrrigationSource(dto.getIrrigationSource());
        landDetails.setBorewellDischarge(dto.getBorewellDischarge());
        landDetails.setBorewellLocation(dto.getBorewellLocation());
        landDetails.setFarmer(farmer);
        return landDetails;
    }

    public LandDetailsDTO toDTO(LandDetails landDetails) {
        LandDetailsDTO dto = new LandDetailsDTO();
        dto.setId(landDetails.getId());
        dto.setSurveyNumber(landDetails.getSurveyNumber());
        dto.setLandSize(landDetails.getLandSize());
        dto.setCropType(landDetails.getCropType());
        dto.setSoilTest(landDetails.getSoilTest());
        dto.setSoilTestCertificate(landDetails.getSoilTestCertificate());
        dto.setGeoTag(landDetails.getGeoTag());
        dto.setLatitude(landDetails.getLatitude());
        dto.setLongitude(landDetails.getLongitude());
        dto.setIrrigationSource(landDetails.getIrrigationSource());
        dto.setBorewellDischarge(landDetails.getBorewellDischarge());
        dto.setBorewellLocation(landDetails.getBorewellLocation());
        dto.setFarmerId(landDetails.getFarmer().getId());
        return dto;
    }
}
