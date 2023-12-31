package com.project.service;

import com.project.entity.Account;
import com.project.exception.AccountException;
import com.project.exception.CustomException;
import com.project.exception.UserException;
import com.project.helper.SecurityUtil;
import com.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    private final AccountRepository accountRepository;

    public DepositService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account deposit(String accountNumber, double value) throws CustomException {
        Optional<String> opt = SecurityUtil.getCurrentAccount();

        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String currentAccount = opt.get();

        if(!currentAccount.equals(accountNumber)) {
            throw UserException.unauthorized();
        }

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        if (value <= 0) {
            throw AccountException.notDepositPositiveValue();
        }

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setDeposit(value);
        account.setDate(LocalDateTime.now());

        if(list.isEmpty()) {
            account.setBalance(value);
        }
        else {
            Account accountBalance = list.get(list.size() - 1);

            BigDecimal currentBalance = BigDecimal.valueOf(accountBalance.getBalance());
            BigDecimal updateBalance = currentBalance.add(BigDecimal.valueOf(value));
            account.setBalance(updateBalance.doubleValue());
        }

        return accountRepository.save(account);
    }
}
