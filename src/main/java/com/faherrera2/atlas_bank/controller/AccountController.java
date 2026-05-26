package com.faherrera2.atlas_bank.controller;

import com.faherrera2.atlas_bank.model.Account;
import com.faherrera2.atlas_bank.model.Transaction;
import com.faherrera2.atlas_bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.LambdaMetafactory;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long Id){
        return ResponseEntity.ok(accountService.findById(Id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam BigDecimal amount
    ){
        return ResponseEntity.ok(accountService.transfer(fromId, toId, amount));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long Id){
        return ResponseEntity.ok(accountService.getTransactions(Id));
    }

}
