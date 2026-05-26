package com.faherrera2.atlas_bank.service;

import com.faherrera2.atlas_bank.model.Transaction;

import java.util.List;

public interface ITransactionQueryService {
    List<Transaction> getByAccountId(Long accountId);
}
