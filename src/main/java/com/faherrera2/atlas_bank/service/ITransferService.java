package com.faherrera2.atlas_bank.service;

import com.faherrera2.atlas_bank.model.Transaction;

import java.math.BigDecimal;

public interface ITransferService {
    Transaction execute(Long fromId, Long toId, BigDecimal amount);
}
