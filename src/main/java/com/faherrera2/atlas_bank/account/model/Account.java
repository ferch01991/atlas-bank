package com.faherrera2.atlas_bank.account.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
