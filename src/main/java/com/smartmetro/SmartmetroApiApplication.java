package com.smartmetro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
public class SmartmetroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartmetroApiApplication.class, args);
        log.info("Smartmetro API Application started successfully.");
	}

    @Bean
    public Date getDate(){
        return new Date();
    }

}
