package org.example.visitservice.client;

import org.example.visitservice.config.FeignClientConfig;
import org.example.visitservice.dto.doctor.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "doctor-service", configuration = FeignClientConfig.class)
@Component
public interface DoctorClient {
    @GetMapping("/api/doctors/{id}")
    DoctorDto getDoctorById(@PathVariable("id") long id);

    @GetMapping("/api/doctors")
    List<DoctorDto> getAllDoctors();
}
