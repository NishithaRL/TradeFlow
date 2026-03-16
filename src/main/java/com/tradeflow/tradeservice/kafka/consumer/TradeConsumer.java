package com.tradeflow.tradeservice.kafka.consumer;

import com.tradeflow.tradeservice.entity.Trade;
import com.tradeflow.tradeservice.kafka.event.TradeCreatedEvent;
import com.tradeflow.tradeservice.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeConsumer {

    @Autowired
    private TradeRepository tradeRepository;

    @KafkaListener(topics = "trade-created", groupId = "trade-group")
    public void consumeTradeEvent(TradeCreatedEvent event) {

        System.out.println("Settlement service received trade event");

        try {

            // simulate processing delay
            Thread.sleep(2000);

            Trade trade = tradeRepository.findById(event.getTradeId())
                    .orElseThrow(() -> new RuntimeException("Trade not found"));

            trade.setStatus("SETTLED");

            tradeRepository.save(trade);

            System.out.println("Trade settled successfully: " + trade.getId());

        } catch (Exception e) {

            System.out.println("Error processing settlement: " + e.getMessage());
        }
    }
}