package org.vb.practice.kafka.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.vb.practice.kafka.model.Greet;

/**
 * This class defines all consumers.
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${greet.topic.name}")
    private String greetTopicName;


    /**
     * Listens to "foo" topic group for string message.
     *
     * @param message the message
     */
    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void fooGroupListener(String message) {
        logger.info(String.format("#### -> Received Messasge in group 'foo': %s", message));
    }

    /**
     * Listens to "bar" topic group for string message.
     *
     * @param message the message
     */
    @KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
    public void barGroupListener(String message) {
        logger.info(String.format("#### -> Received Messasge in group 'bar': %s", message));
    }

    /**
     * Listens to greet message type.
     *
     * @param greet instance of {@link Greet}
     */
    @KafkaListener(topics = "${greet.topic.name}", containerFactory = "greetKafkaListenerContainerFactory")
    public void greetListener(Greet greet) {
        logger.info(String.format("#### -> Received greet message: %s", greet));
    }

}
