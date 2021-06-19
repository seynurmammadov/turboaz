package az.code.myauto.models.dtos;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class TransactionListDto {
    private int id;
    private Long listingid; // nullable
    private double amount;
    private LocalDateTime createdAt;
}
