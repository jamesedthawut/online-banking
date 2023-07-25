package com.project.service;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final AccountRepository accountRepository;

    public HistoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getHistory(String accountNumber) {
        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }
}
