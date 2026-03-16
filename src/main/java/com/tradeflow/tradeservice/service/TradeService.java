package com.tradeflow.tradeservice.service;

import com.tradeflow.tradeservice.dto.TradeDTO;
import com.tradeflow.tradeservice.entity.Trade;
import com.tradeflow.tradeservice.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.tradeflow.tradeservice.kafka.producer.TradeProducer;
import com.tradeflow.tradeservice.kafka.event.TradeCreatedEvent;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeProducer tradeProducer;

    @Autowired
    private LetterOfCreditService letterOfCreditService;

    public Trade createTrade(TradeDTO tradeDTO) {

        Trade trade = new Trade();

        trade.setBuyer(tradeDTO.getBuyer());
        trade.setSeller(tradeDTO.getSeller());
        trade.setBuyerBank(tradeDTO.getBuyerBank());
        trade.setSellerBank(tradeDTO.getSellerBank());
        trade.setAmount(tradeDTO.getAmount());
        trade.setStatus("CREATED");

        Trade savedTrade = tradeRepository.save(trade);

        letterOfCreditService.issueLetterOfCredit(savedTrade);

        TradeCreatedEvent event = new TradeCreatedEvent();
        event.setTradeId(savedTrade.getId());
        event.setBuyer(savedTrade.getBuyer());
        event.setSeller(savedTrade.getSeller());
        event.setAmount(savedTrade.getAmount());
        event.setBuyerBank(savedTrade.getBuyerBank());
        event.setSellerBank(savedTrade.getSellerBank());


        tradeProducer.sendTradeEvent(event);

        return savedTrade;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade getTradeById(Long id) {

        return tradeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Trade not found")
                );
    }

    public String deleteTrade(Long id) {

        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Trade not found")
                );

        tradeRepository.delete(trade);

        return "Trade deleted successfully";
    }
}