package org.example.intuit.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import org.slf4j.Logger;

@Service
public class KafkaProducerService  {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);


        @Autowired
        private KafkaTemplate kafkaTemplate;


        public void send(Object key, Object data, String topic) {
            ListenableFuture future = kafkaTemplate.send(topic, key, data);

            future.addCallback(
                    new ListenableFutureCallback<SendResult<Integer, String>>() {

                        @Override
                        public void onSuccess(SendResult<Integer, String> result) {
                            LOGGER.info("Push to kafka successful");
                        }

                        @Override
                        public void onFailure(Throwable ex) {
                            if (ex instanceof KafkaProducerException) {
                                KafkaProducerException pex = (KafkaProducerException) ex;
                                ProducerRecord<?, ?> producerRecord = pex.getFailedProducerRecord();
                                LOGGER.error("kafka write failed: {}", producerRecord);
                            }
                            LOGGER.error("Push to kafka failed", ex);
                        }
                    });
        }

        public void send(Message<String> message) {
            ListenableFuture future = kafkaTemplate.send(message);

            future.addCallback(
                    new ListenableFutureCallback<SendResult<Integer, String>>() {

                        @Override
                        public void onSuccess(SendResult<Integer, String> result) {
                            LOGGER.info("Push to kafka successful");
                        }

                        @Override
                        public void onFailure(Throwable ex) {
                            if (ex instanceof KafkaProducerException) {
                                KafkaProducerException pex = (KafkaProducerException) ex;
                                ProducerRecord<?, ?> producerRecord = pex.getFailedProducerRecord();
                                LOGGER.error("kafka write failed: {}", producerRecord);
                            }
                            LOGGER.error("Push to kafka failed", ex);
                        }
                    });
        }
}
