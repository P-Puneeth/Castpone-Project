package com.wipro.castpone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NetworkMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkMonitoringApplication.class, args);
	}

}
