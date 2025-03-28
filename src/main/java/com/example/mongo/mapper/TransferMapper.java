package com.example.mongo.mapper;

import com.example.mongo.domain.Transfer;
import com.example.mongo.dto.TransferDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    TransferDto transferToTransferDto(Transfer transfer);
}
