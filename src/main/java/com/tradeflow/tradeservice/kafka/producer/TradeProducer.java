package com.tradeflow.tradeservice.kafka.producer;

import com.tradeflow.tradeservice.kafka.event.TradeCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradeProducer {

    private static final String TOPIC = "trade-created";

    @Autowired
    private KafkaTemplate<String, TradeCreatedEvent> kafkaTemplate;

    public void sendTradeEvent(TradeCreatedEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Trade event sent to Kafka");
    }
}