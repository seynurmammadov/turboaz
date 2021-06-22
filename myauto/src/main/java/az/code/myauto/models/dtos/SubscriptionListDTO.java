package az.code.myauto.models.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SubscriptionListDTO {
    private long subId;
    private String name;
    private MakeDTO make;
    private ModelDTO model;
    private CityDTO city;

    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;

    private String fuelType;
    private String bodyType;
    private String color;

    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;

    private LocalDateTime createdAt;

    private List<CarSpecDTO> equipments = new ArrayList<>();
}
