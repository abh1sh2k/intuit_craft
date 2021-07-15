package org.example.intuit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumerService {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    @KafkaListener(topics = "topic_test", groupId = "kafka.consumer.group")
    public void listen(String intuitEvent) {
        logger.info("Consumed JSON Message: %s ", intuitEvent);
        logger.info("Trigger Onboarding Event");
    }

    Map<String, Object> getEventData(Map<String, Object> event) {
        return (Map<String, Object>) event.get("data");
    }
}
