package com.example.mongo.controller;

import com.example.mongo.dto.AccountDto;
import com.example.mongo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @GetMapping("/{id}")
    public AccountDto read(@PathVariable("id") String id) {
        return accountService.read(id);
    }

    @GetMapping
    public Page<AccountDto> read(@RequestParam("page") int page, @RequestParam("size") int size) {
        return accountService.read(page, size);
    }

    @PutMapping("/{id}")
    public AccountDto update(@PathVariable("id") String id, @RequestBody AccountDto accountDto) {
        return accountService.update(id, accountDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        accountService.delete(id);
    }
}
