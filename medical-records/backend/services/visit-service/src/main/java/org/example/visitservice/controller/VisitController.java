package org.example.visitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> getVisit(@PathVariable long id) {
        VisitDto visit = visitService.getVisitById(id);
        return ResponseEntity.ok(visit);
    }

    @PostMapping
    public ResponseEntity<VisitDto> createVisit(@RequestBody @Valid CreateVisitDto visitDto) {
        VisitDto createVisit = visitService.createVisit(visitDto);
        return new ResponseEntity<>(createVisit, HttpStatus.CREATED);
    }
}
