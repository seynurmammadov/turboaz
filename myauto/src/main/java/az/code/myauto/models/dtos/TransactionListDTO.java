package az.code.myauto.models.dtos;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class TransactionListDTO {
    private Long id;
    private ListingListDTO listing; // nullable
    private double amount;
    private LocalDateTime createdAt;
}
