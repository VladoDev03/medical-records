package org.example.doctorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.doctorservice.config.ModelMapperConfig;
import org.example.doctorservice.data.entity.Doctor;
import org.example.doctorservice.data.entity.Speciality;
import org.example.doctorservice.data.repo.DoctorRepository;
import org.example.doctorservice.dto.doctor.CreateDoctorDto;
import org.example.doctorservice.dto.doctor.DoctorDto;
import org.example.doctorservice.dto.doctor.UpdateDoctorDto;
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
        Doctor doctor = findDoctorOrThrow(id);

        return mapperConfig.getModelMapper().map(doctor, DoctorDto.class);
    }

    @Override
    public DoctorDto createDoctor(CreateDoctorDto doctorDto) {
        Set<Speciality> specialities = getSpecialitiesFromIds(doctorDto.getSpecialityIds());

        Doctor newDoctor = mapperConfig.getModelMapper().map(doctorDto, Doctor.class);
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

    @Override
    public DoctorDto updateDoctor(long id, UpdateDoctorDto doctorDto) {
        Doctor doctor = findDoctorOrThrow(id);
        Set<Speciality> specialities = getSpecialitiesFromIds(doctorDto.getSpecialityIds());

        doctor.setSpecialities(specialities);
        DoctorDto updatedDoctor = mapperConfig.getModelMapper().map(doctorRepository.save(doctor), DoctorDto.class);

        updatedDoctor.setSpecialityNames(specialities
                .stream()
                .map(Speciality::getName)
                .toList()
        );

        return updatedDoctor;
    }

    private Set<Speciality> getSpecialitiesFromIds(List<Long> specialitiesIds) {
        return specialityService
                .getSpecialitiesByIds(specialitiesIds)
                .stream()
                .map(speciality -> mapperConfig.getModelMapper().map(speciality, Speciality.class))
                .collect(Collectors.toSet());
    }

    private Doctor findDoctorOrThrow(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + id));
    }
}
