package com.kafka.playaround.kafkaplayaround;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class KafkaPlayaroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPlayaroundApplication.class, args);
	}

	@Bean
	public KafkaAdmin getKafkaAdmin(@Value("${spring.kafka.consumer.bootstrap-servers}") String bootstrapServers) {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(configs);
	}

}
