package org.example.visitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.sickleave.DoctorSickLeaveCountDto;
import org.example.visitservice.dto.sickleave.SickLeaveDto;
import org.example.visitservice.dto.sickleave.SickLeaveMonthCountDto;
import org.example.visitservice.service.contracts.SickLeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('admin')")
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

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping
    public ResponseEntity<SickLeaveDto> createSickLeave(@RequestBody @Valid CreateSickLeaveDto sickLeaveDto) {
        SickLeaveDto createdSickLeave = sickLeaveService.createSickLeave(sickLeaveDto);
        return new ResponseEntity<>(createdSickLeave, HttpStatus.CREATED);
    }

    @GetMapping("/most-active-months")
    public ResponseEntity<List<SickLeaveMonthCountDto>> getMostActiveMonths(@RequestParam int year) {
        return ResponseEntity.ok(
                sickLeaveService.getMostActiveSickLeaveMonths(year)
        );
    }

    @GetMapping("/top-doctors")
    public ResponseEntity<List<DoctorSickLeaveCountDto>> getTopDoctors() {
        return ResponseEntity.ok(
                sickLeaveService.getDoctorsWithMostSickLeaves()
        );
    }
}
