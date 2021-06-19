package az.code.myauto.services;

import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void increaseBalance(BigDecimal amount) {
        //TODO: after creating User DAO, must be returned here
    }

    @Override
    public void decreaseBalance(BigDecimal amount) {
        //TODO: after creating User DAO, must be returned here
    }
}
