package com.faherrera2.atlas_bank.transaction.repository;

import com.faherrera2.atlas_bank.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Query method
    List<Transaction> findBySourceAccountIdOrTargetAccountId(Long sourceId, Long targetId);
}
