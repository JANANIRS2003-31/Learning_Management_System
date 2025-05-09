package com.cts.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizAssessmentModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAssessmentModuleApplication.class, args);
	}

}
