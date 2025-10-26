package org.example.patientservice.client;

import org.example.patientservice.dto.doctor.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
@Component
public interface DoctorClient {
    @GetMapping("/api/doctors/{id}")
    DoctorDto getDoctorById(@PathVariable("id") long id);
}
