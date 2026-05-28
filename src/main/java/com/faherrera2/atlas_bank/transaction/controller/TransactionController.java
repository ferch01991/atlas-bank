package com.faherrera2.atlas_bank.transaction.controller;

import com.faherrera2.atlas_bank.transaction.dtos.TransactionResponse;
import com.faherrera2.atlas_bank.transaction.dtos.TransferRequest;
import com.faherrera2.atlas_bank.transaction.model.Transaction;
import com.faherrera2.atlas_bank.transaction.service.ITransactionQueryService;
import com.faherrera2.atlas_bank.transaction.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransferService transferService;
    private final ITransactionQueryService transactionQueryService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest request){
        return ResponseEntity.ok(
                toResponse(transferService.execute(
                        request.getFromId(),
                        request.getToId(),
                        request.getAmount()
                ))
        );
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long Id){
        List<TransactionResponse> response = transactionQueryService.getByAccountId(Id)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    public TransactionResponse toResponse(Transaction request){
        TransactionResponse response = new TransactionResponse();
        response.setId(request.getId());
        response.setType(request.getType());
        response.setSourceAccountId(request.getSourceAccountId());
        response.setTargetAccountId(request.getTargetAccountId());
        response.setAmount(request.getAmount());
        response.setFee(request.getFee());
        response.setStatus(request.getStatus());
        response.setCreatedAt(request.getCreatedAt());

        return response;

    }
}
