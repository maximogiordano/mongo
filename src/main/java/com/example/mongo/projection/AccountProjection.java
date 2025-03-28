package com.example.mongo.projection;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountProjection {
    private String id;
    private String owner;
    private BigDecimal balance;
    private List<IncomingTransferProjection> incoming;
    private List<OutgoingTransferProjection> outgoing;
}
