package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.dtos.TransactionListDTO;
import az.code.myauto.models.dtos.UserDTO;

import java.util.List;

public interface TransactionService {
    TransactionListDTO increaseBalance(double amount, UserDTO user) throws TransactionIncorrectAmountException;

    TransactionListDTO decreaseBalance(double amount, UserDTO user, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException;

    List<TransactionListDTO> getTransactions(Integer pageNo, Integer pageSize, String sortBy, UserDTO user);

    double getBalance(UserDTO user);
}
