package org.example.doctorservice.service.contracts;

import org.example.doctorservice.dto.speciality.CreateSpecialityDto;
import org.example.doctorservice.dto.speciality.SpecialityDto;
import org.example.doctorservice.dto.speciality.UpdateSpecialityDto;

import java.util.List;

public interface SpecialityService {
    List<SpecialityDto> getAllSpecialities();
    List<SpecialityDto> getSpecialitiesByIds(List<Long> specialitiesIds);
    SpecialityDto getSpecialityById(long id);
    SpecialityDto createSpeciality(CreateSpecialityDto specialityDto);
    SpecialityDto updateSpeciality(long id, UpdateSpecialityDto specialityDto);
}
