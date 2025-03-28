package com.example.mongo.controller;

import com.example.mongo.projection.AccountProjection;
import com.example.mongo.service.AccountProjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-projections")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AccountProjectionController {
    private final AccountProjectionService accountProjectionService;

    @GetMapping("/{id}")
    public AccountProjection getAccountProjection(@PathVariable("id") String id) {
        return accountProjectionService.getAccountProjection(id);
    }
}
