package com.faherrera2.atlas_bank.transaction.service;

import com.faherrera2.atlas_bank.transaction.model.Transaction;
import com.faherrera2.atlas_bank.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionQueryService implements ITransactionQueryService{

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return transactionRepository.findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }
}
