package com.project.service;

import com.project.entity.Account;
import com.project.exception.AccountException;
import com.project.exception.CustomException;
import com.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawService {

    private final AccountRepository accountRepository;

    public WithdrawService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account withdraw(String accountNumber, double value) throws CustomException {
        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        if (list.isEmpty()) {
            throw AccountException.withdrawEmptyAccount();
        }

        Account account = list.get(list.size() - 1);

        Account entity = new Account();

        if (value <= 0) {
            throw AccountException.notWithdrawPositiveValue();
        }

        if (value > account.getBalance()) {
            throw AccountException.notEnoughMoneyForWithdraw();
        }

        entity.setAccountNumber(accountNumber);
        entity.setWithdraw(value);
        entity.setDate(LocalDateTime.now());

        BigDecimal currentBalance = BigDecimal.valueOf(account.getBalance());
        BigDecimal updateBalance = currentBalance.subtract(BigDecimal.valueOf(value));
        entity.setBalance(updateBalance.doubleValue());

        return accountRepository.save(entity);
    }
}
