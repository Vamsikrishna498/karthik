package com.farmer.farmermanagement.repository;

import com.farmer.farmermanagement.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    // Use correct JPQL query to find OTP by emailOrPhone and otpCode
    @Query("SELECT o FROM Otp o WHERE o.emailOrPhone = :emailOrPhone AND o.otpCode = :otpCode")
    Optional<Otp> findByEmailOrPhoneAndOtpCode(@Param("emailOrPhone") String emailOrPhone,
                                               @Param("otpCode") String otpCode);

    // Ensure transactional context for delete operation
    @Transactional
    @Modifying
    @Query("DELETE FROM Otp o WHERE o.emailOrPhone = :emailOrPhone")
    void deleteByEmailOrPhone(@Param("emailOrPhone") String emailOrPhone);
}


