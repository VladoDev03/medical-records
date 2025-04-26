package com.medical_records.prescription_service.controller;

import com.medical_records.prescription_service.data.document.Medicine;
import com.medical_records.prescription_service.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping
    public Flux<Medicine> getMedicines() {
        return medicineService.getAllMedicines();
    }
}
