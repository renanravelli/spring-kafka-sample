package br.com.renanravelli.springkafkasample.kafka.consumers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renanravelli
 */
@Configuration
public class ExampleConsumerConfiguration {

  @Bean
  public ExampleConsumer exampleConsumer() {
    return new ExampleConsumer();
  }

}
