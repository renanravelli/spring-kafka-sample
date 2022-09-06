package br.com.renanravelli.springkafkasample.kafka.dtos;

import java.util.UUID;

/**
 * @author renanravelli
 */
public record EventDto<T>(UUID trackingId, T payload) {

}
