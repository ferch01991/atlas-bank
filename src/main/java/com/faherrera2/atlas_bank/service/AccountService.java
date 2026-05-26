package com.faherrera2.atlas_bank.service;

import com.faherrera2.atlas_bank.model.Account;
import com.faherrera2.atlas_bank.repository.AccountRepository;
import com.faherrera2.atlas_bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long Id) {
        return accountRepository.findById(Id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
    }
}
