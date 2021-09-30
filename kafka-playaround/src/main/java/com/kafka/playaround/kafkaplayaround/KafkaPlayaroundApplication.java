package com.kafka.playaround.kafkaplayaround;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaPlayaroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPlayaroundApplication.class, args);
	}

}
