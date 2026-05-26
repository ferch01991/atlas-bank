package com.faherrera2.atlas_bank.account.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; // SAVING, CHECKING
    private BigDecimal balance;
    private String status; // ACTIVE, CLOSED, FROZEN
    private LocalDateTime createAt;

    @PrePersist // jpa callback to assign values by default
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        if (status == null) status = "ACTIVE";
        if (balance == null) balance = BigDecimal.ZERO;
    }


}
