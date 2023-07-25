package com.project.mapper;

import com.project.entity.Account;
import com.project.model.TransferResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferResponse toTransferResponse(Account account);
}
