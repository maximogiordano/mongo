package com.example.mongo.service;

import com.example.mongo.exception.EntityNotFoundException;
import com.example.mongo.projection.AccountProjection;
import com.example.mongo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountProjectionService {
    private final AccountRepository accountRepository;

    public AccountProjection getAccountProjection(String id) {
        return accountRepository.getAccountProjection(id)
                .orElseThrow(() -> new EntityNotFoundException("account not found"));
    }
}
