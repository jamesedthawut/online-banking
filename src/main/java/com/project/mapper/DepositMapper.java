package com.project.mapper;

import com.project.entity.Account;
import com.project.model.DepositResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    DepositResponse toDepositResponse(Account account);
}
