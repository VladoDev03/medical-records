package org.example.visitservice.config;

import org.example.visitservice.data.entity.Diagnose;
import org.example.visitservice.data.entity.SickLeave;
import org.example.visitservice.dto.sickleave.SickLeaveDto;
import org.example.visitservice.data.entity.Visit;
import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
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

        mapper.typeMap(CreateVisitDto.class, Visit.class)
                .addMappings(m -> m.skip(Visit::setId));

        mapper.typeMap(CreateSickLeaveDto.class, SickLeave.class)
                .addMappings(m -> m.skip(SickLeave::setId));

        mapper.typeMap(SickLeave.class, SickLeaveDto.class)
                .addMappings(m -> m.map(src -> src.getVisit().getId(), SickLeaveDto::setVisitId));

        mapper.typeMap(Visit.class, VisitDto.class)
                .addMappings(m -> m.using(diagnosesToNamesConverter())
                        .map(Visit::getDiagnoses, VisitDto::setDiagnosesNames));

        return mapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> getModelMapper().map(element, targetClass))
                .collect(Collectors.toList());
    }

    private Converter<Set<Diagnose>, List<String>> diagnosesToNamesConverter() {
        return ctx -> {
            Set<Diagnose> diagnoses = ctx.getSource();
            if (diagnoses == null) return List.of();
            return diagnoses.stream()
                    .map(Diagnose::getName)
                    .collect(Collectors.toList());
        };
    }
}
