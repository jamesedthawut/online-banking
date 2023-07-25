package com.project.mapper;

import com.project.entity.Account;
import com.project.model.HistoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    List<HistoryResponse> toHistoryResponse(List<Account> list);
}
