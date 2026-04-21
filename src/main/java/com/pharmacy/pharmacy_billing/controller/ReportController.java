package com.pharmacy.pharmacy_billing.controller;


import com.pharmacy.pharmacy_billing.entity.Bill;
import com.pharmacy.pharmacy_billing.entity.BillItem;
import com.pharmacy.pharmacy_billing.entity.Medicine;
import com.pharmacy.pharmacy_billing.repository.BillRepository;
import com.pharmacy.pharmacy_billing.repository.MedicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/summary")
public Map<String, Object> getSummary() {

    double totalSales = billRepository.findAll()
            .stream()
            .mapToDouble(Bill::getTotal)
            .sum();

    int totalItemsSold = billRepository.findAll()
            .stream()
            .flatMap(b -> b.getItems() != null ? b.getItems().stream() : java.util.stream.Stream.empty())
            .mapToInt(BillItem::getQuantity)
            .sum();

    long totalBills = billRepository.count();

    List<Medicine> lowStockList = medicineRepository.findAll()
        .stream()
        .filter(m -> m.getQuantity() < 10)
        .toList();


    Map<String, Object> res = new HashMap<>();
    res.put("totalSales", totalSales);
    res.put("totalItemsSold", totalItemsSold);
    res.put("totalBills", totalBills);
    res.put("lowStock", lowStockList.size());
    res.put("lowStockItems", lowStockList);

    return res;
}
}
