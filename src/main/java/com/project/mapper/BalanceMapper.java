package com.project.mapper;

import com.project.model.BalanceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    BalanceResponse toBalanceResponse(Double balance);
}
