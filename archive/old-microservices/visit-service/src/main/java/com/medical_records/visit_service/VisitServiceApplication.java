package com.medical_records.visit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VisitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitServiceApplication.class, args);
	}

}
