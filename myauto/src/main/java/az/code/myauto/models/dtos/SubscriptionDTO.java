package az.code.myauto.models.dtos;

import az.code.myauto.models.Equipment;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SubscriptionDTO {
    private String name;

    private long subId;
    private long makeId;
    private long modelId;
    private long cityId;

    private String fuelType;
    private String bodyType;

    private String color;
    private List<Long> specs;

    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;

    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;
    private LocalDateTime createdAt;




}
