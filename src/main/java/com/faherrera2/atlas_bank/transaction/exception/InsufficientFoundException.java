package com.faherrera2.atlas_bank.transaction.exception;

import java.math.BigDecimal;

public class InsufficientFoundException extends RuntimeException {
    public InsufficientFoundException(Long id, BigDecimal balance, BigDecimal amount) {
        super("Account "+ id + " has a balance of " + balance + " and tried to transfer " + amount);
    }
}
