package org.example.doctorservice.service.contracts;

import org.example.doctorservice.dto.doctor.CreateDoctorDto;
import org.example.doctorservice.dto.doctor.DoctorDto;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(long id);
    DoctorDto createDoctor(CreateDoctorDto doctor);
}
