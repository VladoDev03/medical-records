package org.example.visitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.DoctorVisitCountDto;
import org.example.visitservice.dto.visit.UpdateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<List<VisitDto>> getVisits() {
        List<VisitDto> visits = visitService.getVisits();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<VisitDto>> getVisitsByPatientId(@PathVariable long id) {
        List<VisitDto> visits = visitService.getVisitsByPatientId(id);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> getVisit(@PathVariable long id) {
        VisitDto visit = visitService.getVisitById(id);
        return ResponseEntity.ok(visit);
    }

    @PreAuthorize("hasAuthority('patient')")
    @PostMapping
    public ResponseEntity<VisitDto> createVisit(@RequestBody @Valid CreateVisitDto visitDto) {
        VisitDto createVisit = visitService.createVisit(visitDto);
        return new ResponseEntity<>(createVisit, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PutMapping("/{id}")
    public ResponseEntity<VisitDto> updateVisit(@PathVariable long id, @RequestBody @Valid UpdateVisitDto visitDto) {
        VisitDto updatedVisit = visitService.updateVisit(id, visitDto);
        return ResponseEntity.ok(updatedVisit);
    }

    @GetMapping("/doctor/counts")
    public ResponseEntity<List<DoctorVisitCountDto>> getVisitCountsByDoctor() {
        List<DoctorVisitCountDto> counts = visitService.getVisitCountsForAllDoctors();
        return ResponseEntity.ok(counts);
    }

    @GetMapping("/period")
    public ResponseEntity<List<VisitDto>> getVisitsInPeriod(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return ResponseEntity.ok(
                visitService.getVisitsInPeriod(startDate, endDate)
        );
    }

    @GetMapping("/doctor/{doctorId}/period")
    public ResponseEntity<List<VisitDto>> getVisitsForDoctorInPeriod(
            @PathVariable long doctorId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return ResponseEntity.ok(
                visitService.getVisitsForDoctorInPeriod(doctorId, startDate, endDate)
        );
    }
}
