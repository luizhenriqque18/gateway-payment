package io.github.luizhenriqque18.gateway_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luizhenriqque18.gateway_api.domain.Account;
import io.github.luizhenriqque18.gateway_api.dto.AccountDTO;
import io.github.luizhenriqque18.gateway_api.dto.ResponseGateway;
import io.github.luizhenriqque18.gateway_api.service.AccountService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<ResponseGateway<AccountDTO>> list(
        @RequestHeader(value = "X-API-Key", required = false) String apiKey
    ) {
        ResponseGateway<AccountDTO> response = new ResponseGateway<>("Account", null);

        if (apiKey == null) {
            response.setMessage("API Key not provided");
            return ResponseEntity.badRequest().body(response);
        }

        Account account = service.findByAPIKey(apiKey);

        if (account == null) {
            response.setMessage("Account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setData(AccountDTO.fromAccountDTO(account));

        return ResponseEntity.ok(response);
    }
}
