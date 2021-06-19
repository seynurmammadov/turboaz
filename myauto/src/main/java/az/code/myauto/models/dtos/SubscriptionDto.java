package az.code.myauto.models.dtos;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SubscriptionDto {
    private long subId;
    private String name;
    private long makeId;
    private long modelId;
    private long cityId;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
    private String fuelType;
    private String bodyType;
    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;
    private String color;
    private List<Long> specs;
}
