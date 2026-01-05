package org.example.doctorservice.config;

import org.example.doctorservice.data.entity.*;
import org.example.doctorservice.dto.doctor.CreateDoctorDto;
import org.example.doctorservice.dto.doctor.DoctorDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        mapper.typeMap(CreateDoctorDto.class, Doctor.class)
                .addMappings(m -> m.skip(Doctor::setId));

        mapper.typeMap(Doctor.class, DoctorDto.class)
                .addMappings(m -> {
                    m.map(Doctor::getId, DoctorDto::setId);
                    m.map(Doctor::getKeycloakId, DoctorDto::setKeycloakId);
                    m.map(Doctor::isGp, DoctorDto::setGp);
                    m.using(specialitiesToNamesConverter())
                            .map(Doctor::getSpecialities, DoctorDto::setSpecialityNames);
                });

        return mapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> getModelMapper().map(element, targetClass))
                .collect(Collectors.toList());
    }

    private Converter<Set<Speciality>, List<String>> specialitiesToNamesConverter() {
        return ctx -> {
            Set<Speciality> specialities = ctx.getSource();
            if (specialities == null) return List.of();
            return specialities.stream()
                    .map(Speciality::getName)
                    .toList();
        };
    }
}
