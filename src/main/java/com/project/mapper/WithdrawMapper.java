package com.project.mapper;

import com.project.entity.Account;
import com.project.model.WithdrawResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WithdrawMapper {

    WithdrawResponse toWithdrawResponse(Account account);
}
