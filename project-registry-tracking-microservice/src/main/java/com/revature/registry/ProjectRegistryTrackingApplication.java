package com.revature.registry;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ProjectRegistryTrackingApplication {
    //contains the controllers for Iteration, Phase, and Status components.


    public static void main(String[] args) {
        SpringApplication.run(ProjectRegistryTrackingApplication.class, args);
    }
    
  //for DTO conversion, needed since using the entities themselves leaves them open for malicious input attacks.
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
