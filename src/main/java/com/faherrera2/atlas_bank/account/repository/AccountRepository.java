package com.faherrera2.atlas_bank.account.repository;

import com.faherrera2.atlas_bank.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
