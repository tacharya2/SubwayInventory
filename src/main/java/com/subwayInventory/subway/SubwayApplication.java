package com.subwayInventory.subway;

import com.subwayInventory.subway.entity.CurrentTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubwayApplication.class, args);
		CurrentTime ct = new CurrentTime();
		ct.getCurrentTimeStamp();
	}
}
//TODO: Logging(logback), Object level test cases, Spring Security, Chaos Monkey, Performance Testing, Dynatrace monitoring, Kafka/sns-topic, Splunk, Deploying it dev/test in AWS

//Ping: http://localhost:8083/api/products/ping
// Swagger: http://localhost:8083/swagger-ui/index.html#/
//Actuator Health: http://localhost:8083/actuator/health