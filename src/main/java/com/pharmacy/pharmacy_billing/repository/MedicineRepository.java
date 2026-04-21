package com.pharmacy.pharmacy_billing.repository;

import com.pharmacy.pharmacy_billing.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}