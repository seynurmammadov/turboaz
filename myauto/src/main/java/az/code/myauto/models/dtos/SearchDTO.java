package az.code.myauto.models.dtos;

import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SearchDTO {
    private Integer make;
    private Integer model;
    private Integer location;
    private Integer minYear;
    private Integer maxYear;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minMileage;
    private Integer maxMileage;
    private FuelType fuelType;
    private Boolean loanOption;
    private Boolean barterOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private BodyType bodyType;
    private Integer page;
    private Integer count;
}
