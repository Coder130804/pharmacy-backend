package com.pharmacy.pharmacy_billing.controller;

import com.pharmacy.pharmacy_billing.dto.BillRequest;
import com.pharmacy.pharmacy_billing.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody BillRequest request) {
        return ResponseEntity.ok(billService.saveBill(request));
    }
}