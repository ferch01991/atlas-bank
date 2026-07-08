package com.faherrera2.atlas_bank.account.controller;

import com.faherrera2.atlas_bank.account.dtos.AccountMapper;
import com.faherrera2.atlas_bank.account.dtos.AccountResponse;
import com.faherrera2.atlas_bank.account.dtos.CreateAccountRequest;
import com.faherrera2.atlas_bank.account.model.Account;
import com.faherrera2.atlas_bank.account.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {
        Account account = accountMapper.toEntity(request);

        Account accountSaved = accountService.create(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountMapper.toResponse(accountSaved));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll(){

        List<AccountResponse> response = accountService.findAll()
                .stream()
                .map(accountMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(accountMapper.toResponse(accountService.findById(id)));
    }
}
