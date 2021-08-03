package com.revature.registry;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class ProjectMicroServiceApplication {
    //contains the controllers for Organization, Project, and Tag components.

	public static void main(String[] args) {
		SpringApplication.run(ProjectMicroServiceApplication.class, args);
	}
	
	//for DTO conversion, needed since using the entities themselves leaves them open for malicious input attacks.
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
