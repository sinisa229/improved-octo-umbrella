package com.kafka.playaround.kafkaplayaround;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConditionalOnExpression("${kafka-playground.my-sender.active:true}")
public class MySender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySender.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void run() throws Exception {
        LOGGER.info("Sending");
        this.template.send("myTopic", "foo1"+ new Date());
    }

}
