package com.faherrera2.atlas_bank.transaction.dtos;

import com.faherrera2.atlas_bank.transaction.validation.DifferentAccounts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Data
@DifferentAccounts
public class TransferRequest {

    @NotNull(message = "Account source is required")
    private Long fromId;

    @NotNull(message = "Account target is required")
    private Long toId;

    @NotNull(message = "The amount is required")
    @Positive(message = "The amount must be greater than 0 ")
    private BigDecimal amount;

}
