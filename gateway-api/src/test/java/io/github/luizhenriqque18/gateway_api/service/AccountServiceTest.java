package io.github.luizhenriqque18.gateway_api.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import io.github.luizhenriqque18.gateway_api.domain.Account;
import io.github.luizhenriqque18.gateway_api.exception.ConflitoDeDadosException;
import io.github.luizhenriqque18.gateway_api.repository.AccountRepository;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static io.github.luizhenriqque18.gateway_api.utils.GenerateUtil.generateApiKey;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService; // Classe que será testada

    @Mock
    private AccountRepository accountRepository; // Mock de uma dependência

    @Test
    void createAccount_ShouldReturnAccount_WhenInputIsValid() {

        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setName("Test Account");

        // Configurar o comportamento do mock
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Executar o método a ser testado
        Account sut = accountService.createAccount(account);

        // Verificar o resultado
        assertNotNull(sut);
        assertEquals("Test Account", account.getName());
    }

    @Test
    void createAccount_ShouldThrowsException_WhenInputIsNotValid() {
        Account account = new Account();
        account.setName(" ");

        // Configurar o comportamento do mock
        when(accountRepository.save(any(Account.class))).thenThrow(new DataIntegrityViolationException(""));

        // Executar o método a ser testado
        // Verificar o resultado
        assertThatThrownBy(() -> accountService.createAccount(account))
                .isInstanceOf(ConflitoDeDadosException.class)
                .hasMessageContaining("Conflito ao criar a conta.");

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test 
    void createAccount_ShouldThrowsException_WhenEmailAlreadyExists() {
        Account account = new Account();
        account.setEmail("luiz.souza@email.com");

        // Configurar o comportamento do mock
        when(accountRepository.save(any(Account.class))).thenThrow(new DataIntegrityViolationException("PUBLIC.CONSTRAINT_INDEX_E ACCOUNT EMAIL"));

        // Executar o método a ser testado
        // Verificar o resultado

        assertThatThrownBy(() -> accountService.createAccount(account))
                .isInstanceOf(ConflitoDeDadosException.class)
                .hasMessageContaining(String.format("Email %s já existe.", account.getEmail()));
        
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void findAccount_ShouldReturnAccount_WhenApiKeyIsExists () {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setAPIKey(generateApiKey());
        account.setName("Test Account");


        // Configurar o comportamento do mock
        when(accountRepository.findByAPIKey(any(String.class))).thenReturn(account);

        // Executar o método a ser testado
        Account sut = accountService.findByAPIKey(account.getAPIKey());
        
        // Verificar o resultado
        assertNotNull(sut);
        assertThat(sut).isEqualTo(account);
    }
}


