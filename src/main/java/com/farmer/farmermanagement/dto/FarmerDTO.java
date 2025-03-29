package com.farmer.farmermanagement.dto;

import com.farmer.farmermanagement.enums.Document;
import com.farmer.farmermanagement.enums.Education;
import com.farmer.farmermanagement.enums.Gender;
import com.farmer.farmermanagement.enums.PortalAccess;
import com.farmer.farmermanagement.enums.PortalRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FarmerDTO {
    private Long id;

    @NotBlank(message = "Photo is required")
    private String photo;

    @NotBlank(message = "Salutation is required")
    private String salutation;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;
    private String lastName;

    @NotNull(message = "Gender is required") 
    private Gender gender;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
    private String contactNumber;

    private String relationshipType;

    @NotBlank(message = "Father name is required")
    private String fatherName;

    @NotBlank(message = "Alternative number is required")
    @Pattern(regexp = "\\d{10}", message = "Alternative number must be exactly 10 digits")
    private String alternativeNumber;

    @NotBlank(message = "Alternative no. type is required")
    private String alternativeNoType;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "Block is required")
    private String block;

    @NotBlank(message = "Village is required")
    private String village;

    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "\\d{5,6}", message = "Zipcode must be 5 or 6 digits")
    private String zipcode;

    @NotNull(message = "Education is required") 
    private Education education;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document type is required") 
    private Document document;

    @Min(value = 0, message = "Farming experience cannot be negative")
    private int farmingExperience;

    @DecimalMin(value = "0.0", inclusive = true, message = "Net income cannot be negative")
    private double netIncome;

    private PortalRole portalRole;
    private PortalAccess portalAccess;

    private List<Long> cropIds; 
    private Long bankDetailsId; 
    private Long landDetailsId; 

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        this.gender = gender;
    }

    public void setEducation(Education education) {
        if (education == null) {
            throw new IllegalArgumentException("Education cannot be null");
        }
        this.education = education;
    }

    public void setDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document type cannot be null");
        }
        this.document = document;
    }
}
