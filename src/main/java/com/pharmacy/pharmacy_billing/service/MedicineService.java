package com.pharmacy.pharmacy_billing.service;

import com.pharmacy.pharmacy_billing.entity.Medicine;
import com.pharmacy.pharmacy_billing.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public Medicine addMedicine(Medicine medicine) {
        return repository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return repository.findAll();
    }
    public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
    Medicine medicine = repository.findById(id).orElseThrow();
    medicine.setName(updatedMedicine.getName());
    medicine.setManufacturer(updatedMedicine.getManufacturer());
    medicine.setPrice(updatedMedicine.getPrice());
    medicine.setQuantity(updatedMedicine.getQuantity());
    return repository.save(medicine);
}

public void deleteMedicine(Long id) {
    repository.deleteById(id);
}
}