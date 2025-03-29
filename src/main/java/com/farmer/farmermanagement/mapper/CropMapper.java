package com.farmer.farmermanagement.mapper;

import com.farmer.farmermanagement.dto.CropDTO;
import com.farmer.farmermanagement.entity.Crop;
import com.farmer.farmermanagement.entity.Farmer;
import com.farmer.farmermanagement.enums.SoilTest;
import org.springframework.stereotype.Component;

@Component
public class CropMapper {

    public Crop toCropEntity(CropDTO dto, Farmer farmer) {
        Crop crop = new Crop();
        crop.setId(dto.getId());
        crop.setPhoto(dto.getPhoto());
        crop.setCropName(dto.getCropName());
        crop.setCropType(dto.getCropType());
        crop.setAreaInAcres(dto.getAreaInAcres());
        crop.setSurveyNumber(dto.getSurveyNumber());

        // Convert String to SoilTest enum
        if (dto.getSoilTest() != null && !dto.getSoilTest().isEmpty()) {
            try {
                crop.setSoilTest(SoilTest.valueOf(dto.getSoilTest().toUpperCase())); // Convert String to Enum
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid SoilTest value: " + dto.getSoilTest());
            }
        }

        crop.setSoilTestCertificate(dto.getSoilTestCertificate());
        crop.setIrrigationSource(dto.getIrrigationSource());
        crop.setGeoTag(dto.getGeoTag());
        crop.setNetIncome(dto.getNetIncome());
        crop.setLatitude(dto.getLatitude());
        crop.setLongitude(dto.getLongitude());
        crop.setFarmer(farmer);
        return crop;
    }

    public CropDTO toCropDTO(Crop crop) {
        CropDTO dto = new CropDTO();
        dto.setId(crop.getId());
        dto.setPhoto(crop.getPhoto());
        dto.setCropName(crop.getCropName());
        dto.setCropType(crop.getCropType());
        dto.setAreaInAcres(crop.getAreaInAcres());
        dto.setSurveyNumber(crop.getSurveyNumber());

        // Convert SoilTest enum to String
        if (crop.getSoilTest() != null) {
            dto.setSoilTest(crop.getSoilTest().name()); // Enum to String
        }

        dto.setSoilTestCertificate(crop.getSoilTestCertificate());
        dto.setIrrigationSource(crop.getIrrigationSource());
        dto.setGeoTag(crop.getGeoTag());
        dto.setNetIncome(crop.getNetIncome());
        dto.setLatitude(crop.getLatitude());
        dto.setLongitude(crop.getLongitude());
        dto.setFarmerId(crop.getFarmer().getId());
        return dto;
    }
}
