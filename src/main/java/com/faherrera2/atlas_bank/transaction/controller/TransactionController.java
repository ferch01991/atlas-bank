package com.faherrera2.atlas_bank.transaction.controller;

import com.faherrera2.atlas_bank.transaction.dtos.TransactionMapper;
import com.faherrera2.atlas_bank.transaction.dtos.TransactionResponse;
import com.faherrera2.atlas_bank.transaction.dtos.TransferRequest;
import com.faherrera2.atlas_bank.transaction.model.Transaction;
import com.faherrera2.atlas_bank.transaction.service.ITransactionQueryService;
import com.faherrera2.atlas_bank.transaction.service.ITransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransferService transferService;
    private final ITransactionQueryService transactionQueryService;
    private final TransactionMapper transactionMapper;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferRequest request){
        Transaction transaction = transferService.execute(
                request.getFromId(),
                request.getToId(),
                request.getAmount()
        );

        return ResponseEntity.ok(
                transactionMapper.toResponse(transaction)
        );
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long Id){
        List<TransactionResponse> response = transactionQueryService.getByAccountId(Id)
                .stream()
                .map(transactionMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}
