package com.pharmacy.pharmacy_billing.controller;

import com.pharmacy.pharmacy_billing.entity.Medicine;
import com.pharmacy.pharmacy_billing.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return service.addMedicine(medicine);
    }

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return service.getAllMedicines();
    }
    
    @PutMapping("/{id}")
public Medicine updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine) {
    return service.updateMedicine(id, medicine);
}

@DeleteMapping("/{id}")
public void deleteMedicine(@PathVariable Long id) {
    service.deleteMedicine(id);
}
}