package az.code.myauto.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Setter
@Getter
@ToString
public class ListingCreationDTO {
    private Long makeId;
    private Long modelId;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private Long cityId;
    private String gearBox;
    private boolean auto_pay;
    private Boolean creditOption;
    private Boolean barterOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private String description;
    private String type;
    private String thymbnailUrl;
    private List<Integer> carSpecIds;
}
