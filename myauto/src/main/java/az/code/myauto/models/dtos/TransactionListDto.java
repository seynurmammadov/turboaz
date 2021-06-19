package az.code.myauto.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionListDto {
    private int id;
    private Long listingId; // nullable
    private double amount;
    private LocalDateTime createdAt;
}
