package com.faherrera2.atlas_bank.transaction.service;

import com.faherrera2.atlas_bank.account.exception.AccountNotFoundException;
import com.faherrera2.atlas_bank.account.model.Account;
import com.faherrera2.atlas_bank.transaction.exception.AccountNotActiveException;
import com.faherrera2.atlas_bank.transaction.exception.InsufficientFoundException;
import com.faherrera2.atlas_bank.transaction.model.Transaction;
import com.faherrera2.atlas_bank.account.repository.AccountRepository;
import com.faherrera2.atlas_bank.transaction.repository.TransactionRepository;
import com.faherrera2.atlas_bank.transaction.service.fee.FeeCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransferService implements ITransferService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final List<FeeCalculator> feeCalculators;

    @Override
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount) {
        // search accounts
        Account accountFrom = accountRepository.findById(fromId).orElseThrow(() -> new AccountNotFoundException(fromId));
        Account accountTo = accountRepository.findById(toId).orElseThrow(() -> new AccountNotFoundException(toId));

        // Validate status accounts: Active
        if (!"ACTIVE".equals(accountFrom.getStatus())){
            throw new AccountNotActiveException(fromId, accountFrom.getStatus());
        }
        if (!"ACTIVE".equals(accountTo.getStatus())){
            throw new AccountNotActiveException(toId, accountTo.getStatus());
        }

        // Account balance
        if (accountFrom.getBalance().compareTo(amount) < 0){
            throw new InsufficientFoundException(fromId, accountFrom.getBalance(), amount);
        }

        // Estimate fees
        BigDecimal fee = feeCalculators.stream()
                .filter(fc -> fc.supports(accountFrom.getType()))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("There isn't calculator for type : " + accountFrom.getType()))
                .calculate(amount);

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
}
