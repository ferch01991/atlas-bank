package com.faherrera2.atlas_bank.transaction.service;

import com.faherrera2.atlas_bank.transaction.model.Transaction;

import java.math.BigDecimal;

public interface ITransferService {
    Transaction execute(Long fromId, Long toId, BigDecimal amount);
}
