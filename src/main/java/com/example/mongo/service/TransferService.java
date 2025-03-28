package com.example.mongo.service;

import com.example.mongo.domain.Account;
import com.example.mongo.domain.Transfer;
import com.example.mongo.dto.TransferDto;
import com.example.mongo.exception.EntityNotFoundException;
import com.example.mongo.mapper.TransferMapper;
import com.example.mongo.repository.AccountRepository;
import com.example.mongo.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@SuppressWarnings("java:S6212")
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    @Transactional
    public TransferDto transfer(String from, String to, BigDecimal amount) {
        Account accountFrom = findAccountById(from);
        Account accountTo = findAccountById(to);

        updateBalance(accountFrom, accountFrom.getBalance().subtract(amount));
        updateBalance(accountTo, accountTo.getBalance().add(amount));

        Transfer transfer = createTransfer(from, to, amount);

        return transferMapper.transferToTransferDto(transfer);
    }

    private Account findAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("account " + id + " not found"));
    }

    private void updateBalance(Account account, BigDecimal balance) {
        account.setBalance(balance);
        accountRepository.save(account);
    }

    private Transfer createTransfer(String from, String to, BigDecimal amount) {
        Transfer transfer = new Transfer();

        transfer.setInstant(Instant.now());
        transfer.setFrom(from);
        transfer.setTo(to);
        transfer.setAmount(amount);

        return transferRepository.save(transfer);
    }

    public TransferDto read(String id) {
        Transfer transfer = findTransferById(id);

        return transferMapper.transferToTransferDto(transfer);
    }

    private Transfer findTransferById(String id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("transfer " + id + " not found"));
    }

    public Page<TransferDto> read(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("instant"));

        return transferRepository.findAll(pageable)
                .map(transferMapper::transferToTransferDto);
    }

    @Transactional
    public void delete(String id) {
        Transfer transfer = findTransferById(id);
        Account accountFrom = findAccountById(transfer.getFrom());
        Account accountTo = findAccountById(transfer.getTo());

        updateBalance(accountFrom, accountFrom.getBalance().add(transfer.getAmount()));
        updateBalance(accountTo, accountTo.getBalance().subtract(transfer.getAmount()));

        transferRepository.deleteById(id);
    }
}
