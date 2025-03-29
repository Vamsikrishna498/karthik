package com.farmer.farmermanagement.dto;

import com.farmer.farmermanagement.enums.CropType;
import com.farmer.farmermanagement.enums.IrrigationSource;
import com.farmer.farmermanagement.enums.SoilTest;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandDetailsDTO {

    private Long id;

    @NotBlank(message = "Survey number is required")
    private String surveyNumber;

    @Positive(message = "Land size must be greater than zero")
    private double landSize;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Crop type is required")
    private CropType cropType;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Soil test is required")
    private SoilTest soilTest;

    @NotBlank(message = "Soil test certificate is required")
    private String soilTestCertificate;

    @NotBlank(message = "Geotag (Address) is required")
    private String geoTag;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Irrigation source is required")
    private IrrigationSource irrigationSource;

    private String borewellDischarge;
    private String borewellLocation;

    @NotNull(message = "Farmer ID is required")
    private Long farmerId;
}
