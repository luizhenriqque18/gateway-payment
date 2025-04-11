package io.github.luizhenriqque18.gateway_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.luizhenriqque18.gateway_api.domain.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    
    Account findByEmail(String email);
    
    Account findByAPIKey(String apiKey);
}
