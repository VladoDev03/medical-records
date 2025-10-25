package org.example.doctorservice.service.contracts;

import org.example.doctorservice.dto.CreateDoctorDto;
import org.example.doctorservice.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(Long id);
    DoctorDto createDoctor(CreateDoctorDto doctor);
}
