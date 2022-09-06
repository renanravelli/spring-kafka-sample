package br.com.renanravelli.springkafkasample.kafka;

import br.com.renanravelli.springkafkasample.kafka.dtos.EventDto;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * @author renanravelli
 */

@EnableKafka
@Configuration
public class KafkaConfiguration {

  @Bean
  KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EventDto<?>>> kafkaListenerContainerFactory(
      ConsumerFactory<String, EventDto<?>> consumerFactory) {

    ConcurrentKafkaListenerContainerFactory<String, EventDto<?>> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setRecordInterceptor(new ExampleRecordInterceptor());
    factory.setConsumerFactory(consumerFactory);
    factory.setConcurrency(1);
    factory.setCommonErrorHandler(new ExampleDefaultErrorHandler());
    factory.setMessageConverter(new ExampleMessageConverter());

    return factory;
  }

  @Bean
  public ConsumerFactory<String, EventDto<?>> consumerFactory(KafkaProperties kafkaProperties) {
    Map<String, Object> properties = kafkaProperties.getConsumer().buildProperties();

    return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(),
        new JsonDeserializer<>(new TypeReference<>() {
        }, false));
  }

  @Bean
  public ProducerFactory<String, EventDto<?>> producerFactory(KafkaProperties kafkaProperties) {
    return new DefaultKafkaProducerFactory<>(kafkaProperties.getProducer().buildProperties());
  }

  @Bean
  public KafkaTemplate<String, EventDto<?>> kafkaTemplate(
      ProducerFactory<String, EventDto<?>> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}
