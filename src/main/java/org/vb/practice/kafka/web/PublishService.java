package org.vb.practice.kafka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vb.practice.kafka.driver.Producer;
import org.vb.practice.kafka.model.Greet;

/**
 * REST controller.
 */
@RestController
@RequestMapping(value = "/kafka/publish")
public class PublishService {

    private final Producer producer;

    /**
     * Constructor.
     *
     * @param producer the producer
     */
    @Autowired
    public PublishService(Producer producer) {
        this.producer = producer;
    }

    /**
     * Defines REST end point to publish a string message.
     *
     * @param message the message
     */
    @PostMapping(value = "/message")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        producer.sendMessage(message);
    }

    /**
     * Defines REST end point to publish a {@link Greet} message.
     *
     * @param msg  the message
     * @param name the name
     */
    @PostMapping(value = "/greet")
    public void sendGreetToKafkaTopic(@RequestParam("message") String msg, @RequestParam("name") String name) {
        producer.sendGreetMessage(new Greet(msg, name));
    }

}
