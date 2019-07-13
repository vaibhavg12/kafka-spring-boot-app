package org.vb.practice.kafka.factory;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.vb.practice.kafka.model.Greet;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for creating consumers.
 */
@EnableKafka
@Configuration
public class KafkaConsumerCreator {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;


    private org.springframework.kafka.core.ConsumerFactory<String, String> createConsumer(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * Creates a consumer for "foo" group.
     *
     * @return instance of {@link ConcurrentKafkaListenerContainerFactory}
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createConsumer("foo"));
        return factory;
    }

    /**
     * Creates a consumer for "bar" group.
     *
     * @return instance of {@link ConcurrentKafkaListenerContainerFactory}
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> barKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createConsumer("bar"));
        return factory;
    }

    /**
     * Creates a consumer for {@link Greet} message.
     *
     * @return instance of {@link ConcurrentKafkaListenerContainerFactory}
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Greet> greetKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Greet> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createGreetConsumer());
        return factory;
    }

    private org.springframework.kafka.core.ConsumerFactory<String, Greet> createGreetConsumer() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "greet");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Greet.class));
    }

}
