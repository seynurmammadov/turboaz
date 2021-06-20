package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.TransactionListDto;

public interface TransactionService {
    TransactionListDto increaseBalance(double amount, UserData userData) throws TransactionIncorrectAmountException;
    TransactionListDto decreaseBalance(double amount, UserData userData, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException;
    double getBalance(UserData userData);
}
