package com.pharmacy.pharmacy_billing.service;

import com.pharmacy.pharmacy_billing.dto.BillRequest;
import com.pharmacy.pharmacy_billing.entity.Bill;
import com.pharmacy.pharmacy_billing.entity.BillItem;
import com.pharmacy.pharmacy_billing.entity.Medicine;
import com.pharmacy.pharmacy_billing.repository.BillRepository;
import com.pharmacy.pharmacy_billing.repository.MedicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public Map<String, Object> saveBill(BillRequest request) {

        Bill bill = new Bill();
        bill.setTotal(request.getTotal());

        List<BillItem> items = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        for (BillRequest.Item reqItem : request.getItems()) {

            Medicine med = medicineRepository.findById(reqItem.getMedicineId())
                    .orElseThrow(() -> new RuntimeException(
                            "Medicine not found: " + reqItem.getMedicineId()
                    ));

            int requestedQty = reqItem.getQuantity();
            int availableQty = med.getQuantity();

            int finalQty = requestedQty;

            if (requestedQty > availableQty) {
                finalQty = availableQty;

                warnings.add(
                        med.getName() + " only " + availableQty + " available. Adjusted."
                );
            }

            // safe stock update
            med.setQuantity(Math.max(availableQty - finalQty, 0));
            medicineRepository.save(med);

            BillItem item = new BillItem();
            item.setMedicine(med);
            item.setQuantity(finalQty);

            // SAFE PRICE HANDLING
           item.setPrice(med.getPrice());

            item.setBill(bill);

            items.add(item);
        }

        bill.setItems(items);

        Bill savedBill = billRepository.save(bill);

        // ❌ IMPORTANT: DO NOT RETURN FULL ENTITY (prevents 500 JSON recursion)
        Map<String, Object> response = new HashMap<>();
        response.put("billId", savedBill.getId());
        response.put("total", savedBill.getTotal());
        response.put("warnings", warnings);

        return response;
    }
}