package io.github.luizhenriqque18.gateway_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizhenriqque18.gateway_api.domain.Account;
import io.github.luizhenriqque18.gateway_api.dto.AccountDTO;
import io.github.luizhenriqque18.gateway_api.service.AccountService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/api/v1/accounts")
@RestController
public class AccountsController {
    
    @Autowired
    private AccountService service;

    @PostMapping()
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO accountDTO) {
        Account account = service.createAccount(AccountDTO.fromAccount(accountDTO));
        return ResponseEntity.ok(AccountDTO.fromAccountDTO(account));
    }

    @GetMapping()
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(List.of("Account 1", "Account 2"));
    }
}
