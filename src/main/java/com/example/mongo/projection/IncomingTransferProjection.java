package com.example.mongo.projection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncomingTransferProjection extends TransferProjection {
    private String from;
}
