package com.as0824.rentaltool.repository;


import com.as0824.rentaltool.entity.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {
    // You can define custom database queries here if needed
}

