package com.project.service;

import com.project.entity.Account;
import com.project.exception.CustomException;
import com.project.exception.UserException;
import com.project.helper.SecurityUtil;
import com.project.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    private final AccountRepository accountRepository;

    public HistoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getHistory(String accountNumber) throws CustomException {
        Optional<String> opt = SecurityUtil.getCurrentAccount();

        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String currentAccount = opt.get();

        if(!currentAccount.equals(accountNumber)) {
            throw UserException.unauthorized();
        }

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }
}
