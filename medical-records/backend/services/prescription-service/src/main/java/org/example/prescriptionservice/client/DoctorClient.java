package org.example.prescriptionservice.client;

import org.example.prescriptionservice.dto.doctor.DoctorDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DoctorClient {
    private final WebClient webClient;

    public DoctorClient(
            WebClient.Builder builder,
            @Value("${doctor.service.url}") String doctorServiceUrl
    ) {
        this.webClient = builder.baseUrl(doctorServiceUrl).build();
    }

    public Mono<DoctorDto> getDoctorById(Long id) {
        return webClient.get()
                .uri("/api/doctors/{id}", id)
                .retrieve()
                .bodyToMono(DoctorDto.class);
    }
}
