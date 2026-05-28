package com.faherrera2.atlas_bank.transaction.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private Long fromId;
    private Long toId;
    private BigDecimal amount;

}
