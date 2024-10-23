package com.exam.pro.final_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinalFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalFrontendApplication.class, args);
	}

}
