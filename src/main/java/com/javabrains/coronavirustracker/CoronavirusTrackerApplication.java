package com.javabrains.coronavirustracker;

import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.javabrains.coronavirustracker.services.CoronavirusDataService;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackerApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(CoronavirusTrackerApplication.class, args);
	}

}
