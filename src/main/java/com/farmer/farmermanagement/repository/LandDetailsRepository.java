package com.farmer.farmermanagement.repository;

import com.farmer.farmermanagement.entity.LandDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandDetailsRepository extends JpaRepository<LandDetails, Long> {
    Optional<LandDetails> findByFarmerId(Long farmerId);
}
