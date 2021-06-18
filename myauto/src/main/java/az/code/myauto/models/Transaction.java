package az.code.myauto.models;

import az.code.myauto.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private long id;
    private TransactionType transactionType;
    private BigInteger amount;
    private User user;
}
