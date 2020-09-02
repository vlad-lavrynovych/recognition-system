package com.demo.java_recognition_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class JavaRecognitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaRecognitionApplication.class, args);
    }
}
