package az.code.myauto.models;

import az.code.myauto.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    TransactionType transactionType;

    BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private mUser mUser;

}
