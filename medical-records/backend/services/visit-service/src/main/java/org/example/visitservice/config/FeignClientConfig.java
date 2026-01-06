package org.example.visitservice.config;

import feign.RequestInterceptor;
import org.example.visitservice.interceptor.FeignClientInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();
    }
}
