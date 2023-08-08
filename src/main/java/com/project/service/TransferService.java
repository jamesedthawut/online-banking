package com.project.service;

import com.project.entity.Account;
import com.project.entity.User;
import com.project.exception.AccountException;
import com.project.exception.CustomException;
import com.project.exception.UserException;
import com.project.helper.SecurityUtil;
import com.project.repository.AccountRepository;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public TransferService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Account transfer(String fromAccountNumber, String toAccountNumber, double value) throws CustomException {
        Optional<String> opt = SecurityUtil.getCurrentAccount();

        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String currentAccount = opt.get();

        if(!currentAccount.equals(fromAccountNumber)) {
            throw UserException.unauthorized();
        }

        if(fromAccountNumber.equals(toAccountNumber)) {
            throw AccountException.transferSameAccount();
        }

        List<Account> from = accountRepository.findByAccountNumber(fromAccountNumber);
        List<Account> to = accountRepository.findByAccountNumber(toAccountNumber);

        if (from.isEmpty()) {
            throw AccountException.transferEmptyAccount();
        }

        Account fromAccount = from.get(from.size() - 1);

        if (value <= 0) {
            throw AccountException.notTransferPositiveValue();
        }

        if (value > fromAccount.getBalance()) {
            throw AccountException.notEnoughMoneyForTransfer();
        }

        Optional<User> optFrom = userRepository.findByAccountNumber(fromAccountNumber);
        Optional<User> optTo = userRepository.findByAccountNumber(toAccountNumber);

        User fromUser = optFrom.get();
        User toUser = optTo.get();

        Account fromEntity = new Account();

        fromEntity.setAccountNumber(fromAccountNumber);
        fromEntity.setWithdraw(value);
        fromEntity.setDate(LocalDateTime.now());
        fromEntity.setNote("Transfer to: " + toUser.getName());

        BigDecimal currentBalance1 = BigDecimal.valueOf(fromAccount.getBalance());
        BigDecimal updateBalance1 = currentBalance1.subtract(BigDecimal.valueOf(value));
        fromEntity.setBalance(updateBalance1.doubleValue());

        Account toEntity = new Account();

        toEntity.setAccountNumber(toAccountNumber);
        toEntity.setDeposit(value);
        toEntity.setDate(LocalDateTime.now());
        toEntity.setNote("Transfer from: " + fromUser.getName());

        if (to.isEmpty()) {
            toEntity.setBalance(value);
        } else {
            Account toAccount = to.get(to.size() - 1);

            BigDecimal currentBalance = BigDecimal.valueOf(toAccount.getBalance());
            BigDecimal updateBalance = currentBalance.add(BigDecimal.valueOf(value));
            toEntity.setBalance(updateBalance.doubleValue());
        }

        accountRepository.save(toEntity);

        return accountRepository.save(fromEntity);
    }
}
