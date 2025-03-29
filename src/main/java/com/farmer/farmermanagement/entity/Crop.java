package com.farmer.farmermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.farmer.farmermanagement.dto.FarmerDTO;
import com.farmer.farmermanagement.enums.CropType;
import com.farmer.farmermanagement.enums.IrrigationSource;
import com.farmer.farmermanagement.enums.SoilTest;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Photo is required")
    @Column(nullable = false)
    private String photo;

    @NotBlank(message = "Crop name is required")
    @Column(nullable = false)
    private String cropName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Crop type is required")
    @Column(nullable = false)
    private CropType cropType;

    @Positive(message = "Area in acres must be positive")
    @Column(nullable = false)
    private double areaInAcres;
    
    @NotBlank(message = "Survey number is required")
    @Column(nullable = false, unique = true)
    private String surveyNumber;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Soil test is required")
    @Column(nullable = false)
    private SoilTest soilTest;

    public void setSoilTest(SoilTest soilTest) {
        this.soilTest = soilTest;
    }
    
    @NotBlank(message = "Soil test certificate is required")
    @Column(nullable = true)
    private String soilTestCertificate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Irrigation source is required")
    @Column(nullable = false)
    private IrrigationSource irrigationSource;
    
    @NotBlank(message = "Geotag (Address) is required")
    @Column(nullable = false)
    private String geoTag;  

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    @PositiveOrZero(message = "Net income cannot be negative")
    @Column(nullable = false)
    private double netIncome;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    @JsonIgnore
    private Farmer farmer;
}
