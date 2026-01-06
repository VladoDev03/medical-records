package org.example.patientservice.config;

import feign.RequestInterceptor;
import org.example.patientservice.interceptor.FeignClientInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }
}
