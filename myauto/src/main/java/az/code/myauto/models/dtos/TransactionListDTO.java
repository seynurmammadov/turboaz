package az.code.myauto.models.dtos;

import az.code.myauto.models.Transaction;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class TransactionListDTO {
    private Long id;
    private Long listingId; // nullable
    private double amount;
    private LocalDateTime createdAt;

    public TransactionListDTO(Transaction transaction) {
        this.id = transaction.getId();
        if(transaction.getListing()!=null)
        this.listingId = transaction.getListing().getId();
        this.amount = transaction.getAmount();
        this.createdAt = transaction.getCreatedAt();
    }
}
