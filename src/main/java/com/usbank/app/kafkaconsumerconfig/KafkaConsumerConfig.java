package com.usbank.app.kafkaconsumerconfig;

import java.util.HashMap;
import java.util.Map;
//////
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

//////
import com.usbank.app.dbmodel.UserRequest;
//////
public class KafkaConsumerConfig {
	
	
	
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
       props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.usbank.app.dbmodel");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    // 2. Consume user objects from Kafka
    public ConsumerFactory<String, UserRequest> userConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
       props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.usbank.app.dbmodel");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(UserRequest.class));
    }
    

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserRequest> userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }
	
}
