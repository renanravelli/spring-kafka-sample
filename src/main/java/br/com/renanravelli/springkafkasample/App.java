package br.com.renanravelli.springkafkasample;

import br.com.renanravelli.springkafkasample.kafka.consumers.ExampleConsumer;
import br.com.renanravelli.springkafkasample.kafka.dtos.EventDto;
import br.com.renanravelli.springkafkasample.kafka.dtos.PersonDto;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(KafkaTemplate<String, EventDto<?>> kafkaTemplate) {
    return args -> IntStream.rangeClosed(1, 10)
        .forEach(v -> kafkaTemplate.send(ExampleConsumer.TOPIC_NAME,
            new EventDto<>(UUID.randomUUID(), new PersonDto("Event " + v))));
  }
}
