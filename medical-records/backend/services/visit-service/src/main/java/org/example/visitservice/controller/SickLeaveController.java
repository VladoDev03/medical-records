package org.example.visitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.sickleave.SickLeaveDto;
import org.example.visitservice.service.contracts.SickLeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sick-leaves")
public class SickLeaveController {
    private final SickLeaveService sickLeaveService;

    @GetMapping
    public ResponseEntity<List<SickLeaveDto>> getSickLeaves() {
        List<SickLeaveDto> sickLeaves = sickLeaveService.getSickLeaves();
        return ResponseEntity.ok(sickLeaves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SickLeaveDto> getSickLeave(@PathVariable long id) {
        SickLeaveDto sickLeave = sickLeaveService.getSickLeaveById(id);
        return ResponseEntity.ok(sickLeave);
    }

    @PostMapping
    public ResponseEntity<SickLeaveDto> createSickLeave(@RequestBody @Valid CreateSickLeaveDto sickLeaveDto) {
        SickLeaveDto createdSickLeave = sickLeaveService.createSickLeave(sickLeaveDto);
        return new ResponseEntity<>(createdSickLeave, HttpStatus.CREATED);
    }
}
