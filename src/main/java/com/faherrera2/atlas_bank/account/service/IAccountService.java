package com.faherrera2.atlas_bank.account.service;

import com.faherrera2.atlas_bank.account.model.Account;

import java.util.List;

public interface IAccountService {
    Account create(Account account);
    List<Account> findAll();
    Account findById(Long Id);
}
