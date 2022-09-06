package br.com.renanravelli.springkafkasample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

/**
 * @author renanravelli
 */
@Slf4j
public class ExampleDefaultErrorHandler implements CommonErrorHandler {

  @Override
  public void handleRecord(Exception thrownException, ConsumerRecord<?, ?> record,
      Consumer<?, ?> consumer, MessageListenerContainer container) {

    log.error("Ocorreu um erro ao realizar o consumer do evento: {}", record.value());

    CommonErrorHandler.super.handleRecord(thrownException, record, consumer, container);
  }
}
