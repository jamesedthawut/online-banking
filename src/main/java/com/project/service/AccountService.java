package com.project.service;

import com.project.entity.Account;
import com.project.entity.User;
import com.project.exception.AccountException;
import com.project.exception.CustomException;
import com.project.mapper.*;
import com.project.model.*;
import com.project.repository.AccountRepository;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final DepositService depositService;
    private final AccountRepository accountRepository;
    private final DepositMapper depositMapper;
    private final BalanceMapper balanceMapper;
    private final BalanceService balanceService;
    private final WithdrawService withdrawService;
    private final WithdrawMapper withdrawMapper;
    private final TransferService transferService;
    private final TransferMapper transferMapper;
    private final UserRepository userRepository;
    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    public AccountService(DepositService depositService, AccountRepository accountRepository, DepositMapper depositMapper,
                          BalanceMapper balanceMapper, BalanceService balanceService, WithdrawService withdrawService,
                          WithdrawMapper withdrawMapper, TransferService transferService, TransferMapper transferMapper,
                          UserRepository userRepository, HistoryService historyService, HistoryMapper historyMapper) {

        this.depositService = depositService;
        this.accountRepository = accountRepository;
        this.depositMapper = depositMapper;
        this.balanceMapper = balanceMapper;
        this.balanceService = balanceService;
        this.withdrawService = withdrawService;
        this.withdrawMapper = withdrawMapper;
        this.transferService = transferService;
        this.transferMapper = transferMapper;
        this.userRepository = userRepository;
        this.historyService = historyService;
        this.historyMapper = historyMapper;
    }

    public BalanceResponse getBalance(String accountNumber) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        Double balance = balanceService.getBalance(accountNumber);

        BalanceResponse response = balanceMapper.toBalanceResponse(balance);

        return response;
    }

    public DepositResponse deposit(String accountNumber, AccountRequest request) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        Account account = depositService.deposit(accountNumber, request.getDeposit());

        DepositResponse response = depositMapper.toDepositResponse(account);

        return response;
    }

    public WithdrawResponse withdraw(String accountNumber, AccountRequest request) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        Account account = withdrawService.withdraw(accountNumber, request.getWithdraw());

        WithdrawResponse response = withdrawMapper.toWithdrawResponse(account);

        return response;
    }

    public TransferResponse transfer(String accountNumber, AccountRequest request) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        Account account = transferService.transfer(accountNumber, request.getAccountNumber(), request.getTransfer());

        TransferResponse response = transferMapper.toTransferResponse(account);

        return response;
    }

    public TransferResponse promptPayTransfer(String accountNumber, AccountRequest request) throws CustomException {
        Optional<User> optFrom = userRepository.findByAccountNumber(accountNumber);

        if(optFrom.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        Optional<User> optTo = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if(optTo.isEmpty()) {
            throw AccountException.transferToAccountNotFound();
        }

        User user = optTo.get();

        Account account = transferService.transfer(accountNumber, user.getAccountNumber(), request.getTransfer());

        TransferResponse response = transferMapper.toTransferResponse(account);

        return response;
    }

    public List<HistoryResponse> history(String accountNumber) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw AccountException.accountNumberNotFound();
        }

        List<Account> list = historyService.getHistory(accountNumber);

        List<HistoryResponse> response = historyMapper.toHistoryResponse(list);

        return response;
    }
}
