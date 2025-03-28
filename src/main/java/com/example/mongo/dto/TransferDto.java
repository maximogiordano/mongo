package com.example.mongo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransferDto {
    private String id;
    private Instant instant;
    private String from;
    private String to;
    private BigDecimal amount;
}
