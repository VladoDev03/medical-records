package org.example.visitservice.client;

import org.example.visitservice.config.FeignClientConfig;
import org.example.visitservice.dto.patient.BatchPatientDto;
import org.example.visitservice.dto.patient.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "patient-service", configuration = FeignClientConfig.class)
@Component
public interface PatientClient {
    @GetMapping("/api/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") long id);

    @GetMapping
    List<PatientDto> getAllPatients();

    @GetMapping("/api/patients/batch")
    BatchPatientDto getPatientsBatch(@RequestParam("ids") List<Long> ids);
}
