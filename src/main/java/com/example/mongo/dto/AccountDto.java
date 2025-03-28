package com.example.mongo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String owner;
    private BigDecimal balance;
}
