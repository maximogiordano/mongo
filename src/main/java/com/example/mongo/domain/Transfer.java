package com.example.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Document(collection = "transfers")
public class Transfer {
    private String id;
    private Instant instant;
    private String from;
    private String to;
    private BigDecimal amount;
}
