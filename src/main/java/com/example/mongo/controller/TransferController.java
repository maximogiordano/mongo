package com.example.mongo.controller;

import com.example.mongo.dto.TransferDto;
import com.example.mongo.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferDto transfer(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount
    ) {
        return transferService.transfer(from, to, amount);
    }

    @GetMapping("/{id}")
    public TransferDto read(@PathVariable("id") String id) {
        return transferService.read(id);
    }

    @GetMapping
    public Page<TransferDto> read(@RequestParam("page") int page, @RequestParam("size") int size) {
        return transferService.read(page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        transferService.delete(id);
    }
}
