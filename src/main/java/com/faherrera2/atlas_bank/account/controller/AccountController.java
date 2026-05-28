package com.faherrera2.atlas_bank.account.controller;

import com.faherrera2.atlas_bank.account.dtos.AccountResponse;
import com.faherrera2.atlas_bank.account.dtos.CreateAccountRequest;
import com.faherrera2.atlas_bank.account.model.Account;
import com.faherrera2.atlas_bank.account.service.IAccountService;
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

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody CreateAccountRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setOwnerName(request.getOwnerName());
        account.setEmail(request.getEmail());
        account.setType(request.getType());
        account.setBalance(request.getBalance());
        account.setStatus(request.getStatus());

        Account accountSaved = accountService.create(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(accountSaved));
    }


    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll(){

        List<AccountResponse> response = accountService.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(toResponse(accountService.findById(id)));
    }

    private AccountResponse toResponse(Account accountSaved) {
        AccountResponse response = new AccountResponse();
        response.setId(accountSaved.getId());
        response.setAccountNumber(accountSaved.getAccountNumber());
        response.setOwnerName(accountSaved.getOwnerName());
        response.setEmail(accountSaved.getEmail());
        response.setType(accountSaved.getType());
        response.setBalance(accountSaved.getBalance());
        response.setStatus(accountSaved.getStatus());
        response.setCreateAt(accountSaved.getCreateAt());

        return response;
    }

}
