package org.example.prescriptionservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {
    @LoadBalanced
    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.build();
    }
}
