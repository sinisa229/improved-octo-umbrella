package com.kafka.playaround.kafkaplayaround;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaListener.class);

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) {
        LOGGER.info(cr.toString());
    }

}
