package com.faherrera2.atlas_bank.account.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("The account with ID: " + id + " was not found");
    }
}
