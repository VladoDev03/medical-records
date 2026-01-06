package org.example.prescriptionservice.client;

import org.example.prescriptionservice.dto.patient.PatientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PatientClient {
    private final WebClient webClient;

    public PatientClient(
            WebClient webClient,
            @Value("${patient.service.url}") String baseUrl
    ) {
        this.webClient = webClient
                .mutate()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<PatientDto> getPatientById(Long id) {
        return webClient.get()
                .uri("/api/patients/{id}", id)
                .retrieve()
                .bodyToMono(PatientDto.class);
    }
}
