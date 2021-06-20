package az.code.myauto.models;

import az.code.myauto.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private double amount;

    private LocalDateTime createdAt ;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User User;

    @ManyToOne
    @JoinColumn(name = "listingId")
    private Listing listing;
}
