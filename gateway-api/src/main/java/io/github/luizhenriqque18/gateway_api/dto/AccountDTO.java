package io.github.luizhenriqque18.gateway_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.luizhenriqque18.gateway_api.domain.Account;

public record AccountDTO(
    
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String name,

    @NotEmpty(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be empty")
    String email,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String APIKey
) {

    public static Account fromAccount(AccountDTO accountDTO) {
        return Account.builder()
            .name(accountDTO.name())
            .email(accountDTO.email())
            .build();
    }

    public static AccountDTO fromAccountDTO(Account account) {
        return new AccountDTO(account.getName(), account.getEmail(), account.getAPIKey());
    }
}
