package com.faherrera2.atlas_bank.transaction.exception;

public class AccountNotActiveException extends RuntimeException {
    public AccountNotActiveException(Long accountId, String status) {
        super("Account " + accountId + " is not active. Current status " + status);
    }
}
