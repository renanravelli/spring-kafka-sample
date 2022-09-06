package br.com.renanravelli.springkafkasample.kafka;

import br.com.renanravelli.springkafkasample.kafka.dtos.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.RecordInterceptor;

/**
 * @author renanravelli
 */
@Slf4j
public class ExampleRecordInterceptor implements RecordInterceptor<String, EventDto<?>> {

  @Override
  public ConsumerRecord<String, EventDto<?>> intercept(ConsumerRecord<String, EventDto<?>> record) {
    final var value = record.value();
    log.info("O evento foi interceptado: {}", value);
    return record;
  }
}
