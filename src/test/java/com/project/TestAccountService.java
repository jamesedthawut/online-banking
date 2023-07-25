package com.project;

import com.project.entity.Account;
import com.project.exception.CustomException;
import com.project.repository.AccountRepository;
import com.project.service.BalanceService;
import com.project.service.DepositService;
import com.project.service.TransferService;
import com.project.service.WithdrawService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAccountService {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getBalance1() throws CustomException {
        String accountNumber = "1922343455";

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        double balance = balanceService.getBalance(account.getAccountNumber());

        Assertions.assertEquals(balance, account.getBalance());

    }

    @Test
    void getBalance2() throws CustomException {
        String accountNumber = "3357079693";

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        double balance = balanceService.getBalance(account.getAccountNumber());

        Assertions.assertEquals(balance, account.getBalance());

    }

    @Test
    void deposit1() throws CustomException {
        String accountNumber = "1922343455";
        double value = 10000;

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        Account deposit = depositService.deposit(accountNumber, value);

        Assertions.assertNotNull(deposit.getDeposit());
        Assertions.assertEquals(value, deposit.getDeposit());
        Assertions.assertEquals(deposit.getBalance(), account.getBalance() + value);

    }

    @Test
    void deposit2() throws CustomException {
        String accountNumber = "1922343455";
        double value = 15000;

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        Account deposit = depositService.deposit(accountNumber, value);

        Assertions.assertNotNull(deposit.getDeposit());
        Assertions.assertEquals(value, deposit.getDeposit());
        Assertions.assertEquals(deposit.getBalance(), account.getBalance() + value);

    }

    @Test
    void withdraw1() throws CustomException {
        String accountNumber = "1922343455";
        double value = 3000;

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        Account withdraw = withdrawService.withdraw(accountNumber, value);

        Assertions.assertNotNull(withdraw.getWithdraw());
        Assertions.assertEquals(value, withdraw.getWithdraw());
        Assertions.assertEquals(withdraw.getBalance(), account.getBalance() - value);
    }

    @Test
    void withdraw2() throws CustomException {
        String accountNumber = "3357079693";
        double value = 1000;

        List<Account> list = accountRepository.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(list);

        Account account = list.get(list.size() - 1);

        Account withdraw = withdrawService.withdraw(accountNumber, value);

        Assertions.assertNotNull(withdraw.getWithdraw());
        Assertions.assertEquals(value, withdraw.getWithdraw());
        Assertions.assertEquals(withdraw.getBalance(), account.getBalance() - value);
    }

    @Test
    void transfer1() throws CustomException {
        String fromAccountNumber = "1922343455";
        String toAccountNumber = "3357079693";
        double value = 10000;

        List<Account> fromlist = accountRepository.findByAccountNumber(fromAccountNumber);
        List<Account> toList = accountRepository.findByAccountNumber(toAccountNumber);

        Assertions.assertNotNull(fromlist);
        Assertions.assertNotNull(toList);

        Account fromAccount = fromlist.get(fromlist.size() - 1);

        Account transfer = transferService.transfer(fromAccountNumber, toAccountNumber, value);

        Assertions.assertNotNull(transfer.getWithdraw());
        Assertions.assertEquals(value, transfer.getWithdraw());
        Assertions.assertEquals(transfer.getBalance(), fromAccount.getBalance() - value);
        Assertions.assertNotNull(transfer.getNote());
    }

    @Test
    void transfer2() throws CustomException {
        String fromAccountNumber = "3357079693";
        String toAccountNumber = "1922343455";
        double value = 5000;

        List<Account> fromlist = accountRepository.findByAccountNumber(fromAccountNumber);
        List<Account> toList = accountRepository.findByAccountNumber(toAccountNumber);

        Assertions.assertNotNull(fromlist);
        Assertions.assertNotNull(toList);

        Account fromAccount = fromlist.get(fromlist.size() - 1);

        Account transfer = transferService.transfer(fromAccountNumber, toAccountNumber, value);

        Assertions.assertNotNull(transfer.getWithdraw());
        Assertions.assertEquals(value, transfer.getWithdraw());
        Assertions.assertEquals(transfer.getBalance(), fromAccount.getBalance() - value);
        Assertions.assertNotNull(transfer.getNote());
    }
}
