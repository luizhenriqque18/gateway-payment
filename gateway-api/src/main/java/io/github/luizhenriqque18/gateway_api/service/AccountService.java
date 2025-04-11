package io.github.luizhenriqque18.gateway_api.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.github.luizhenriqque18.gateway_api.domain.Account;
import io.github.luizhenriqque18.gateway_api.exception.ConflitoDeDadosException;
import io.github.luizhenriqque18.gateway_api.repository.AccountRepository;
import io.github.luizhenriqque18.gateway_api.utils.GenerateUtil;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository repository;

    public Account createAccount(Account account) {
        try {
            account.setAPIKey(GenerateUtil.generateApiKey());
            account.setBalance(BigDecimal.ZERO);

            return this.repository.save(account);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("PUBLIC.CONSTRAINT_INDEX_E") && e.getMessage().contains("ACCOUNT") && e.getMessage().contains("EMAIL")) {
                throw new ConflitoDeDadosException(String.format("Email %s já existe.", account.getEmail()));
            } else {
                throw new ConflitoDeDadosException("Conflito ao criar a conta.", e); // Exceção genérica para outros conflitos
            }
        }
    }
}
