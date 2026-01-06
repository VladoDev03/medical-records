package org.example.prescriptionservice.client;

import org.example.prescriptionservice.dto.visit.VisitDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class VisitClient {
    private final WebClient webClient;

    public VisitClient(
            WebClient webClient,
            @Value("${visit.service.url}") String baseUrl
    ) {
        this.webClient = webClient
                .mutate()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<VisitDto> getVisitById(Long id) {
        return webClient.get()
                .uri("/api/visits/{id}", id)
                .retrieve()
                .bodyToMono(VisitDto.class);
    }
}
