package com.kafka.playaround.kafkaplayaround;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class MySender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySender.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void run() throws Exception {
        LOGGER.info("Sending");
        this.template.send("myTopic", "foo1");
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        LOGGER.info("All received");
    }

    public void countDown() {
        latch.countDown();
    }

}
