package org.example.prescriptionservice.config;

import org.example.prescriptionservice.data.document.MedicineItem;
import org.example.prescriptionservice.dto.medicine.MedicineItemDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        mapper.addMappings(new PropertyMap<MedicineItem, MedicineItemDto>() {
            @Override
            protected void configure() {
                map().setMedicineName(source.getMedicine().getName());
            }
        });

        return mapper;
    }
}
