package com.example.mongo.service;

import com.example.mongo.domain.Account;
import com.example.mongo.dto.AccountDto;
import com.example.mongo.exception.EntityNotFoundException;
import com.example.mongo.exception.EntityOperationException;
import com.example.mongo.mapper.AccountMapper;
import com.example.mongo.repository.AccountRepository;
import com.example.mongo.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@SuppressWarnings("java:S6212")
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final AccountMapper accountMapper;

    public AccountDto create(AccountDto accountDto) {
        return save(accountDto);
    }

    private AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.accountDtoToAccount(accountDto);
        account = accountRepository.save(account);

        return accountMapper.accountToAccountDto(account);
    }

    public Page<AccountDto> read(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("owner"));

        return accountRepository.findAll(pageable)
                .map(accountMapper::accountToAccountDto);
    }

    public AccountDto read(String id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountDto)
                .orElseThrow(() -> new EntityNotFoundException("account " + id + " not found"));
    }

    public AccountDto update(String id, AccountDto accountDto) {
        accountDto.setId(id);

        return save(accountDto);
    }

    @Transactional
    public void delete(String id) {
        if (transferRepository.existsByFrom(id) || transferRepository.existsByTo(id)) {
            throw new EntityOperationException("account " + id + " referenced in other documents");
        }

        accountRepository.deleteById(id);
    }
}
