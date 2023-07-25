package com.project.api;

import com.project.exception.CustomException;
import com.project.model.*;
import com.project.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountApi {

    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountNumber) throws CustomException {
        BalanceResponse response = accountService.getBalance(accountNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<DepositResponse> deposit(@PathVariable String accountNumber,
                                                   @RequestBody AccountRequest request) throws CustomException {
        DepositResponse response = accountService.deposit(accountNumber, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<WithdrawResponse> withdraw(@PathVariable String accountNumber,
                                                     @RequestBody AccountRequest request) throws CustomException {
        WithdrawResponse response = accountService.withdraw(accountNumber, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer/{accountNumber}")
    public ResponseEntity<TransferResponse> transfer(@PathVariable String accountNumber,
                                                     @RequestBody AccountRequest request) throws CustomException {
        TransferResponse response = accountService.transfer(accountNumber, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer/promptpay/{accountNumber}")
    public ResponseEntity<TransferResponse> promptPayTransfer(@PathVariable String accountNumber,
                                                              @RequestBody AccountRequest request) throws CustomException {
        TransferResponse response = accountService.promptPayTransfer(accountNumber, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<HistoryResponse>> history(@PathVariable String accountNumber) throws CustomException {
        List<HistoryResponse> response = accountService.history(accountNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
