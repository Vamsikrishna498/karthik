package com.farmer.farmermanagement.repository;

import com.farmer.farmermanagement.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
    Optional<BankDetails> findByFarmerId(Long farmerId);
}
