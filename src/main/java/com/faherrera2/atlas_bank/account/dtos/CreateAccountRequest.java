package com.faherrera2.atlas_bank.account.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; // SAVING, CHECKING
    private BigDecimal balance;
    private String status; // ACTIVE, CLOSED, FROZEN
}
