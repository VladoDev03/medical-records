package org.example.patientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.patient.BatchPatientDto;
import org.example.patientservice.dto.patient.CreatePatientDto;
import org.example.patientservice.dto.patient.GpPatientCountDto;
import org.example.patientservice.dto.patient.PatientDto;
import org.example.patientservice.service.contracts.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable long id) {
        PatientDto patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping("/batch")
    public ResponseEntity<BatchPatientDto> getPatientsBatch(@RequestParam List<Long> ids) {
        BatchPatientDto patients = patientService.getPatientsBatch(ids);
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody @Valid CreatePatientDto patientDto) {
        PatientDto createdPatient = patientService.createPatient(patientDto);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping("/gp/{gpId}")
    public ResponseEntity<List<PatientDto>> getPatientsByGp(@PathVariable long gpId) {
        return ResponseEntity.ok(
                patientService.getPatientsByGpId(gpId)
        );
    }

    @GetMapping("/gp/counts")
    public ResponseEntity<List<GpPatientCountDto>> getPatientCountsForAllGp() {
        return ResponseEntity.ok(
                patientService.getPatientCountsForAllGp()
        );
    }
}
