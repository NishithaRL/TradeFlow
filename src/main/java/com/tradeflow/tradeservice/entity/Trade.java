package com.tradeflow.tradeservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trades")
@Getter
@Setter
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyer;

    private String seller;

    private String buyerBank;
    private String sellerBank;

    private double amount;

    private String status;
}