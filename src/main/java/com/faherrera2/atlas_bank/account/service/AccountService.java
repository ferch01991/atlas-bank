package com.faherrera2.atlas_bank.account.service;

import com.faherrera2.atlas_bank.account.model.Account;
import com.faherrera2.atlas_bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService{
    private final AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long Id) {
        return accountRepository.findById(Id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
    }
}
