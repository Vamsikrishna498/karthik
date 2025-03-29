package com.farmer.farmermanagement.entity;

import com.farmer.farmermanagement.enums.BankName;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Bank name is required")
    @Column(nullable = false)
    private BankName bankName;

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "\\d{9,18}", message = "Account number must be between 9 and 18 digits")
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @NotNull(message = "Branch name is required")
    @Column(nullable = false)
    private String branchName;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code format")
    @Column(nullable = false)
    private String ifscCode;

    private String passbookAttachment;

    // 🔹 Fix circular dependency using mappedBy & avoiding direct reference
    @OneToOne(mappedBy = "bankDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Farmer farmer;
}
