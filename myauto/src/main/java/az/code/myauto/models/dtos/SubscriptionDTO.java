package az.code.myauto.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("specs")
    private List<CarSpecDTO> equipments;

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
