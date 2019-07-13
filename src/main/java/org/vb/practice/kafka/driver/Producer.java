package org.vb.practice.kafka.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.vb.practice.kafka.model.Greet;

/**
 * This class defines all producers.
 */
@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greet> greetKafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${greet.topic.name}")
    private String greetTopicName;


    /**
     * Produces a string message.
     *
     * @param message the message
     */
    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info(String.format("#### -> Producing message -> %s with offset -> %s", message, result.getRecordMetadata().offset()));
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info(String.format("#### -> Unable to produce message -> %s with offset -> %s", message, ex.getMessage()));
            }
        });
    }

    /**
     * Produces a {@link Greet} message.
     *
     * @param greet instance of {@link Greet}
     */
    public void sendGreetMessage(Greet greet) {
        logger.info(String.format("#### -> Producing greet message -> %s", greet));
        greetKafkaTemplate.send(greetTopicName, greet);
    }
}
