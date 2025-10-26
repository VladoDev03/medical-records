package org.example.visitservice.client;

import org.example.visitservice.dto.patient.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
@Component
public interface PatientClient {
    @GetMapping("/api/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") String id);
}
