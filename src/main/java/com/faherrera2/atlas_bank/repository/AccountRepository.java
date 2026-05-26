package com.faherrera2.atlas_bank.repository;

import com.faherrera2.atlas_bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
