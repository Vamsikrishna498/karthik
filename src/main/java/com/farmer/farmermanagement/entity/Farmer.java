package com.farmer.farmermanagement.entity;

import java.util.Date;
import java.util.List;
import com.farmer.farmermanagement.enums.Gender;
import com.farmer.farmermanagement.enums.PortalAccess;
import com.farmer.farmermanagement.enums.PortalRole;
import com.farmer.farmermanagement.enums.Education;
import com.farmer.farmermanagement.enums.Document;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Photo is required")
    @Column(nullable = false)
    private String photo;

    @NotBlank(message = "Salutation is required")
    @Column(nullable = false)
    private String salutation;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    private String middleName;
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")  
    @Column(nullable = false)
    private Gender gender;

    @NotBlank(message = "Nationality is required")
    @Column(nullable = false)
    private String nationality;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
    @Column(nullable = false, unique = true)
    private String contactNumber;

    private String relationshipType;

    @NotBlank(message = "Father name is required")
    @Column(nullable = false)
    private String fatherName;

    @NotBlank(message = "Alternative number is required")
    @Column(nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Alternative number must be exactly 10 digits")
    private String alternativeNumber;

    @NotBlank(message = "Alternative no. type is required")
    @Column(nullable = false)
    private String alternativeNoType;

    @NotBlank(message = "Country is required")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "State is required")
    @Column(nullable = false)
    private String state;

    @NotBlank(message = "District is required")
    @Column(nullable = false)
    private String district;

    @NotBlank(message = "Block is required")
    @Column(nullable = false)
    private String block;

    @NotBlank(message = "Village is required")
    @Column(nullable = false)
    private String village;

    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "\\d{5,6}", message = "Zipcode must be 5 or 6 digits")
    @Column(nullable = false)
    private String zipcode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Education is required") 
    @Column(nullable = false)
    private Education education;

    @Min(value = 0, message = "Farming experience cannot be negative")
    private int farmingExperience;

    @DecimalMin(value = "0.0", inclusive = true, message = "Net income cannot be negative")
    private double netIncome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Document is required")
    @Column(nullable = false)
    private Document document;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Portal role is required")
    @Column(nullable = false)
    private PortalRole portalRole;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Portal access is required")
    @Column(nullable = false)
    private PortalAccess portalAccess;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Crop> crops;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "land_details_id")
    private LandDetails landDetails;



}
