package com.project.service;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    private final AccountRepository accountRepository;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public double getBalance(String accountNumber) {
        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        if (list.isEmpty()) {
            return 0;
        }
        else {
            Account account = list.get(list.size() - 1);

            return account.getBalance();
        }
    }
}
