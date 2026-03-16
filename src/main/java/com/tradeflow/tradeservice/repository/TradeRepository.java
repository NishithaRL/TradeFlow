package com.tradeflow.tradeservice.repository;

import com.tradeflow.tradeservice.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}