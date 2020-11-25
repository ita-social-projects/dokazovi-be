package com.softserveinc.dokazovi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DokazoviApplication {

	public static void main(String[] args) {
		SpringApplication.run(DokazoviApplication.class, args);
	}

}