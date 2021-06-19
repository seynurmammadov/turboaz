package az.code.myauto.services.interfaces;

import az.code.myauto.models.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void increaseBalance(BigDecimal amount) {
        //TODO: after creating mUser DAO, must be returned here
    }

    @Override
    public void decreaseBalance(BigDecimal amount) {
        //TODO: after creating mUser DAO, must be returned here
    }
}
