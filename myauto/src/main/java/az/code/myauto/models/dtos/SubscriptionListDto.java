package az.code.myauto.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SubscriptionListDto {
    //parameters -> makeId, modelId, cityId, minYear, maxYear,  minPrice, maxPrice, minMileage, maxMileage,
    // fuel, loanOption(loan, barter, lease, cash) , bodyType, gearBox, type , specs = [...specIds]
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
    private int fuelType;
    private int bodyType;
    private boolean loanOption;
    private boolean leaseOption;
    private boolean cashOption;
    private boolean barterOption;
    private LocalDateTime creationDate;
    private String color;
    private List<CarSpecDTO> specs;
}
