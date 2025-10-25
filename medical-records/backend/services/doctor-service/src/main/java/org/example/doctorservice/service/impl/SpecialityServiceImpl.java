package org.example.doctorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.doctorservice.config.ModelMapperConfig;
import org.example.doctorservice.data.entity.Speciality;
import org.example.doctorservice.data.repo.SpecialityRepository;
import org.example.doctorservice.dto.speciality.CreateSpecialityDto;
import org.example.doctorservice.dto.speciality.SpecialityDto;
import org.example.doctorservice.exception.EntityNotFoundException;
import org.example.doctorservice.service.contracts.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public List<SpecialityDto> getAllSpecialities() {
        return mapperConfig.mapList(specialityRepository.findAll(), SpecialityDto.class);
    }

    @Override
    public SpecialityDto getSpecialityById(long id) {
        Speciality speciality = specialityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality not found with id: " + id));

        return mapperConfig.getModelMapper().map(speciality, SpecialityDto.class);
    }

    @Override
    public SpecialityDto createSpeciality(CreateSpecialityDto specialityDto) {
        Speciality newSpeciality = mapperConfig.getModelMapper().map(specialityDto, Speciality.class);
        Speciality savedSpeciality = specialityRepository.save(newSpeciality);

        return mapperConfig.getModelMapper().map(savedSpeciality, SpecialityDto.class);
    }
}
