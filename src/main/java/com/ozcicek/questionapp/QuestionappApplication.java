package com.ozcicek.questionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication
public class QuestionappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionappApplication.class, args);
	}

}
