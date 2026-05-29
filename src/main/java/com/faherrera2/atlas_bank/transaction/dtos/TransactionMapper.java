package com.faherrera2.atlas_bank.transaction.dtos;

import com.faherrera2.atlas_bank.transaction.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponse toResponse(Transaction transaction);
}
