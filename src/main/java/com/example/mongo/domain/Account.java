package com.example.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "accounts")
public class Account {
    private String id;
    private String owner;
    private BigDecimal balance;
}
