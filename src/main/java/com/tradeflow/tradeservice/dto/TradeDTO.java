package com.tradeflow.tradeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDTO {

    @NotBlank(message = "Buyer cannot be empty")
    private String buyer;

    @NotBlank(message = "Seller cannot be empty")
    private String seller;

    @NotBlank(message = "Buyer bank cannot be empty")
    private String buyerBank;

    @NotBlank(message = "Seller bank cannot be empty")
    private String sellerBank;

    @Positive(message = "Amount must be greater than zero")
    private double amount;
}