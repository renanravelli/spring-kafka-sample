package br.com.renanravelli.springkafkasample.kafka;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.converter.MessagingMessageConverter;

/**
 * @author renanravelli
 */
@Slf4j
public class ExampleMessageConverter extends MessagingMessageConverter {

  @Override
  protected Object extractAndConvertValue(ConsumerRecord<?, ?> record, Type type) {

    log.info("Realizando converter do evento.");

    final var mapper = new ObjectMapper();

    JavaType javaType = TypeFactory.defaultInstance().constructType(type);

    return mapper.convertValue(record.value(), javaType);
  }
}
