package org.example.doctorservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doctorservice.dto.speciality.CreateSpecialityDto;
import org.example.doctorservice.dto.speciality.SpecialityDto;
import org.example.doctorservice.service.contracts.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('admin')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialities")
public class SpecialityController {
    private final SpecialityService specialityService;

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient')")
    @GetMapping
    public ResponseEntity<List<SpecialityDto>> getSpecialities() {
        List<SpecialityDto> speciality = specialityService.getAllSpecialities();
        return ResponseEntity.ok(speciality);
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient')")
    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDto> getSpeciality(@PathVariable long id) {
        SpecialityDto speciality = specialityService.getSpecialityById(id);
        return ResponseEntity.ok(speciality);
    }

    @PostMapping
    public ResponseEntity<SpecialityDto> createSpeciality(@RequestBody @Valid CreateSpecialityDto specialityDto) {
        SpecialityDto createdSpeciality = specialityService.createSpeciality(specialityDto);
        return new ResponseEntity<>(createdSpeciality, HttpStatus.CREATED);
    }
}
