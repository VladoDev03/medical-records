package org.example.visitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.dto.diagnose.CreateDiagnoseDto;
import org.example.visitservice.dto.diagnose.DiagnoseDto;
import org.example.visitservice.service.contracts.DiagnoseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnoses")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;

    @GetMapping
    public ResponseEntity<List<DiagnoseDto>> getDiagnoses() {
        List<DiagnoseDto> diagnoses = diagnoseService.getDiagnoses();
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnoseDto> getDiagnoseById(@PathVariable long id) {
        DiagnoseDto diagnose = diagnoseService.getDiagnoseById(id);
        return ResponseEntity.ok(diagnose);
    }

    @PostMapping
    public ResponseEntity<DiagnoseDto> createDiagnose(@RequestBody @Valid CreateDiagnoseDto diagnoseDto) {
        DiagnoseDto createdDiagnose = diagnoseService.createDiagnose(diagnoseDto);
        return new ResponseEntity<>(createdDiagnose, HttpStatus.CREATED);
    }
}
