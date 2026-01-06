package org.example.prescriptionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .filter((request, next) ->
                        ReactiveSecurityContextHolder.getContext()
                                .map(SecurityContext::getAuthentication)
                                .cast(JwtAuthenticationToken.class)
                                .map(auth -> auth.getToken().getTokenValue())
                                .map(token -> ClientRequest.from(request)
                                        .header("Authorization", "Bearer " + token)
                                        .build()
                                )
                                .flatMap(next::exchange)
                                .switchIfEmpty(next.exchange(request))
                )
                .build();
    }
}
