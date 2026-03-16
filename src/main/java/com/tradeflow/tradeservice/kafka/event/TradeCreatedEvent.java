package com.tradeflow.tradeservice.kafka.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeCreatedEvent {

    private Long tradeId;
    private String buyer;
    private String seller;
    private String buyerBank;
    private String sellerBank;
    private double amount;
}