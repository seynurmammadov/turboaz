package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.UserData;

public interface TransactionService {
    Transaction increaseBalance(double amount, UserData userData) throws TransactionIncorrectAmountException;
    Transaction decreaseBalance(double amount, UserData userData, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException;
    double getBalance(UserData userData);
}
