package com.example.mongo.projection;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransferProjection {
    private String id;
    private Instant instant;
    private BigDecimal amount;
}
