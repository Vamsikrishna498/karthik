package com.farmer.farmermanagement.dto;

import com.farmer.farmermanagement.enums.CropType;
import com.farmer.farmermanagement.enums.IrrigationSource;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropDTO {

    private Long id;

    @NotBlank(message = "Photo is required")
    private String photo;

    @NotBlank(message = "Crop name is required")
    private String cropName;

    @NotNull(message = "Crop type is required")
    private CropType cropType;

    @Positive(message = "Area in acres must be positive")
    private double areaInAcres;

    @NotBlank(message = "Survey number is required")
    private String surveyNumber;

    @NotBlank(message = "Soil test is required")
    private String soilTest;

    @NotBlank(message = "Soil test certificate is required")
    private String soilTestCertificate;

    @NotNull(message = "Irrigation source is required")
    private IrrigationSource irrigationSource;

    @NotBlank(message = "Geotag (Address) is required")
    private String geoTag; 
    @PositiveOrZero(message = "Net income cannot be negative")
    private double netIncome;

    private Double latitude;
    private Double longitude;

    @NotNull(message = "Farmer ID is required")
    private Long farmerId;
}
