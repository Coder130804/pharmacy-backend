package com.pharmacy.pharmacy_billing.repository;

import com.pharmacy.pharmacy_billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}