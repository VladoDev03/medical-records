package org.example.prescriptionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.dto.medicine.CreateMedicineDto;
import org.example.prescriptionservice.dto.medicine.MedicineDto;
import org.example.prescriptionservice.dto.medicine.UpdateMedicineDto;
import org.example.prescriptionservice.service.contracts.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PreAuthorize("hasAuthority('admin')")
@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping
    public Flux<MedicineDto> getMedicines() {
        return medicineService.getMedicines();
    }

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping("/{id}")
    public Mono<MedicineDto> getMedicineById(@PathVariable String id) {
        return medicineService.getMedicineById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<MedicineDto>> createMedicine(@RequestBody @Valid CreateMedicineDto medicineDto) {
        return medicineService.createMedicine(medicineDto)
                .map(createdMedicine -> new ResponseEntity<>(createdMedicine, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<MedicineDto>> updateMedicine(
            @PathVariable String id,
            @RequestBody @Valid UpdateMedicineDto medicineDto
    ) {
        return medicineService.updateMedicine(id, medicineDto)
                .map(ResponseEntity::ok);
    }
}
