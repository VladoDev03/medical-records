package org.example.doctorservice.service.contracts;

import org.example.doctorservice.dto.speciality.CreateSpecialityDto;
import org.example.doctorservice.dto.speciality.SpecialityDto;

import java.util.List;

public interface SpecialityService {
    List<SpecialityDto> getAllSpecialities();
    SpecialityDto getSpecialityById(long id);
    SpecialityDto createSpeciality(CreateSpecialityDto specialityDto);
}
