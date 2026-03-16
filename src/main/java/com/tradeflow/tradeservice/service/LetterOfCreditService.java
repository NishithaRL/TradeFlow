package com.tradeflow.tradeservice.service;

import com.tradeflow.tradeservice.entity.Trade;
import org.springframework.stereotype.Service;

@Service
public class LetterOfCreditService {

    public void issueLetterOfCredit(Trade trade) {

        System.out.println("====================================");
        System.out.println("LETTER OF CREDIT ISSUED");
        System.out.println("Buyer: " + trade.getBuyer());
        System.out.println("Seller: " + trade.getSeller());
        System.out.println("Buyer Bank: " + trade.getBuyerBank());
        System.out.println("Seller Bank: " + trade.getSellerBank());
        System.out.println("Amount: " + trade.getAmount());
        System.out.println("Status: LC ISSUED");
        System.out.println("====================================");
    }
}
