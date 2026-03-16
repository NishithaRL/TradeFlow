package com.tradeflow.tradeservice.controller;

import com.tradeflow.tradeservice.dto.TradeDTO;
import com.tradeflow.tradeservice.entity.Trade;
import com.tradeflow.tradeservice.repository.TradeRepository;
import com.tradeflow.tradeservice.service.TradeService;
import com.tradeflow.tradeservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/trade")
@SecurityRequirement(name = "bearerAuth")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Operation(summary = "Create a new trade")
    @PostMapping("/create")
    public ApiResponse<Trade> createTrade(@Valid @RequestBody TradeDTO tradeDTO){

        Trade trade = tradeService.createTrade(tradeDTO);

        return new ApiResponse<>(
                true,
                "Trade created successfully",
                trade
        );
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ApiResponse<List<Trade>> getAllTrades(){

        List<Trade> trades = tradeService.getAllTrades();

        return new ApiResponse<>(
                true,
                "Trades fetched successfully",
                trades
        );
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Trade> getTradeById(@PathVariable Long id){

        Trade trade = tradeService.getTradeById(id);

        return new ApiResponse<>(
                true,
                "Trade fetched successfully",
                trade
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTrade(@PathVariable Long id){

        tradeService.deleteTrade(id);

        return new ApiResponse<>(
                true,
                "Trade deleted successfully",
                null
        );
    }
}