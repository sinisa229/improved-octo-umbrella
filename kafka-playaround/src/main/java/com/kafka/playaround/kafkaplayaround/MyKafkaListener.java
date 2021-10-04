package com.kafka.playaround.kafkaplayaround;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaListener.class);

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) {
//        final Map<String, TopicDescription> map = kafkaAdmin.describeTopics("myTopic");
        LOGGER.info(cr.toString());
    }

}
