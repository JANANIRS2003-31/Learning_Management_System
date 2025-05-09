package com.cts.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class ProgressTrackingReportModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressTrackingReportModuleApplication.class, args);
	}

}
