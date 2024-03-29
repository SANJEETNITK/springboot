package com.springboot.ehcache3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ehcache3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ehcache3Application.class, args);
	}

}
