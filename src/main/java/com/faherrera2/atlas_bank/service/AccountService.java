package com.faherrera2.atlas_bank.service;

import com.faherrera2.atlas_bank.model.Account;
import com.faherrera2.atlas_bank.model.Transaction;
import com.faherrera2.atlas_bank.repository.AccountRepository;
import com.faherrera2.atlas_bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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


    @Transactional
    public Transaction transfer(Long fromId, Long toId, BigDecimal amount) {
        // search accounts
        Account accountFrom = accountRepository.findById(fromId).orElseThrow(() -> new RuntimeException("Source account not found"));
        Account accountTo = accountRepository.findById(toId).orElseThrow(() -> new RuntimeException("Target account not found"));

        // Validate status accounts: Active
        if (!"ACTIVE".equals(accountFrom.getStatus())){
            throw new RuntimeException("Source account is not active");
        }
        if (!"ACTIVE".equals(accountTo.getStatus())){
            throw new RuntimeException("Target account is not active");
        }

        // Account balance
        if (accountFrom.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Account balance not enough");
        }

        // Estimate fees
        BigDecimal fee;
        if ("SAVINGS".equals(accountFrom.getType())){
            fee = amount.multiply(new BigDecimal("0.01"));
        } else if ("CHECKING".equals(accountFrom.getType())){
            fee = amount.multiply(new BigDecimal("0.015"));
        } else {
            fee = BigDecimal.ZERO;
        }

        // Update balances
        accountFrom.setBalance(accountFrom.getBalance().subtract(amount).subtract(fee));
        accountTo.setBalance(accountTo.getBalance().add(amount));

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(fromId);
        transaction.setTargetAccountId(toId);
        transaction.setAmount(amount);
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountId){
        return transactionRepository.findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }
}
