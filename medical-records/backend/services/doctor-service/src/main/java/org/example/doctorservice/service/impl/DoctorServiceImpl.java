package org.example.doctorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.doctorservice.config.ModelMapperConfig;
import org.example.doctorservice.data.entity.Doctor;
import org.example.doctorservice.data.entity.Speciality;
import org.example.doctorservice.data.repo.DoctorRepository;
import org.example.doctorservice.dto.doctor.CreateDoctorDto;
import org.example.doctorservice.dto.doctor.DoctorDto;
import org.example.doctorservice.exception.EntityNotFoundException;
import org.example.doctorservice.service.contracts.DoctorService;
import org.example.doctorservice.service.contracts.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapperConfig mapperConfig;
    private final SpecialityService specialityService;

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
        Set<Speciality> specialities = doctor.getSpecialityIds()
                .stream()
                .map(id -> mapperConfig
                        .getModelMapper()
                        .map(specialityService.getSpecialityById(id), Speciality.class)
                )
                .collect(Collectors.toSet());

        Doctor newDoctor = mapperConfig.getModelMapper().map(doctor, Doctor.class);
        newDoctor.setSpecialities(specialities);

        DoctorDto result = mapperConfig
                .getModelMapper()
                .map(doctorRepository.save(newDoctor), DoctorDto.class);

        result.setSpecialityNames(specialities
                .stream()
                .map(Speciality::getName)
                .toList()
        );

        return result;
    }
}
