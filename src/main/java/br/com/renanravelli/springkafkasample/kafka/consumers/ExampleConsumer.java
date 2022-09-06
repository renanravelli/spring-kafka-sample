package br.com.renanravelli.springkafkasample.kafka.consumers;

import br.com.renanravelli.springkafkasample.kafka.dtos.EventDto;
import br.com.renanravelli.springkafkasample.kafka.dtos.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author renanravelli
 */
@Slf4j
public class ExampleConsumer {

  public static final String TOPIC_NAME = "topic-example";

  @KafkaListener(topics = TOPIC_NAME)
  public void consumer(EventDto<PersonDto> person) {
    log.info("Consumo do evento iniciado.");
    System.out.println(person);
    log.info("Consumo do evento finalizado.");
  }

}
