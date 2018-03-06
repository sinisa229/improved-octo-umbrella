package com.kafka.playaround.kafkaplayaround;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
public class KafkaPlayaroundApplication {

	public static Logger logger = LoggerFactory.getLogger(KafkaPlayaroundApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(KafkaPlayaroundApplication.class, args);
	}

}
