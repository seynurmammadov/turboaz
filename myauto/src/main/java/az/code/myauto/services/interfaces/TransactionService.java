package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.dtos.TransactionListDTO;
import az.code.myauto.models.dtos.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    /**
     * This method is for increasing (faking the process) balance by registered user.
     * @param amount
     * @param user
     * @return
     * @throws TransactionIncorrectAmountException
     */
    TransactionListDTO increaseBalance(double amount, UserDTO user) throws TransactionIncorrectAmountException;

    /**
     * This method is for decreasing (faking the process) user balance
     * @param amount
     * @param user
     * @param listingId
     * @return
     * @throws TransactionIncorrectAmountException
     * @throws TransactionInsufficientFundsException
     */
    TransactionListDTO decreaseBalance(double amount, UserDTO user, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException;

    /**
     * This method is for getting all transactions by registered user.
     * @param pageable
     * @param user
     * @return
     */
    List<TransactionListDTO> getTransactions(Pageable pageable, UserDTO user);

    /**
     * This method for getting user balance.
     * @param user
     * @return
     */
    double getBalance(UserDTO user);
}
