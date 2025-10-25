package org.example.doctorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.doctorservice.config.ModelMapperConfig;
import org.example.doctorservice.data.entity.Doctor;
import org.example.doctorservice.data.repo.DoctorRepository;
import org.example.doctorservice.dto.doctor.CreateDoctorDto;
import org.example.doctorservice.dto.doctor.DoctorDto;
import org.example.doctorservice.exception.EntityNotFoundException;
import org.example.doctorservice.service.contracts.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public List<DoctorDto> getAllDoctors() {
        return mapperConfig.mapList(doctorRepository.findAll(), DoctorDto.class);
    }

    @Override
    public DoctorDto getDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + id));

        return mapperConfig.getModelMapper().map(doctor, DoctorDto.class);
    }

    @Override
    public DoctorDto createDoctor(CreateDoctorDto doctor) {
        Doctor newDoctor = mapperConfig.getModelMapper().map(doctor, Doctor.class);
        Doctor savedDoctor = doctorRepository.save(newDoctor);

        return mapperConfig.getModelMapper().map(savedDoctor, DoctorDto.class);
    }
}
