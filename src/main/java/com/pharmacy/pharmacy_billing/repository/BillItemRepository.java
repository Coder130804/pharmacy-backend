package com.pharmacy.pharmacy_billing.repository;

import com.pharmacy.pharmacy_billing.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {
}