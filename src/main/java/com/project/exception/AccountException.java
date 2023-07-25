package com.project.exception;

public class AccountException extends CustomException {

    public AccountException(String message) {
        super(message);
    }

    public static AccountException accountNumberNotFound() {
        return new AccountException("Account number not found");
    }

    public static AccountException notDepositPositiveValue() {
        return new AccountException("Deposit fail: Please insert positive value");
    }

    public static AccountException notWithdrawPositiveValue() {
        return new AccountException("Withdraw fail: Please insert positive value");
    }

    public static AccountException notTransferPositiveValue() {
        return new AccountException("Transfer fail: Please insert positive value");
    }

    public static AccountException notEnoughMoneyForWithdraw() {
        return new AccountException("Withdraw fail: You do not have enough money");
    }

    public static AccountException notEnoughMoneyForTransfer() {
        return new AccountException("Transfer fail: You do not have enough money");
    }

    public static AccountException withdrawEmptyAccount() {
        return new AccountException("Withdraw fail: Your account is empty");
    }

    public static AccountException transferEmptyAccount() {
        return new AccountException("Transfer fail: Your account is empty");
    }

    public static AccountException transferToAccountNotFound() {
        return new AccountException("Transfer fail: Destination account not found");
    }

    public static AccountException transferSameAccount() {
        return new AccountException("Transfer fail: Can't transfer to this account");
    }
}
