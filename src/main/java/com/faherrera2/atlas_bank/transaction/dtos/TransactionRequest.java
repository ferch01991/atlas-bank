package com.faherrera2.atlas_bank.transaction.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status; // PENDING, EXECUTED, REJECTED
}
