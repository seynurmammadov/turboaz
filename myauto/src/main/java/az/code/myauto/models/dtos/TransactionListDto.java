package az.code.myauto.models.dtos;

import java.time.LocalDateTime;

public class TransactionListDto {
    private int id;
    private Long listingid; // nullable
    private double amount;
    private LocalDateTime createdAt;
}
