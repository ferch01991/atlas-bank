package com.faherrera2.atlas_bank.account.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @NotBlank(message = "Account number is required")
    private String accountNumber;
    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "The email is required")
    @Email(message = "The email has not valid format")
    private String email;

    @NotBlank(message = "Type is required")
    private String type; // SAVING, CHECKING

    @PositiveOrZero(message = "The balance can't be negative")
    private BigDecimal balance;

    @NotBlank(message = "Status is required")
    private String status; // ACTIVE, CLOSED, FROZEN
}
