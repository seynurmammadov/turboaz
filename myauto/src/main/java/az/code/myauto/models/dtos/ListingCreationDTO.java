package az.code.myauto.models.dtos;

import az.code.myauto.models.Listing;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class ListingCreationDTO {      // CREATE OLDUQDAN SONRA RESPONSE ListingGetDTO OLARAQ QAYIDACAQ
    private Long makeId;
    private Long modelId;
    private int year;
    private int price;
    private int mileage;
    private String fuelType; // enum
    private String bodyType; // enum
    private String color; // enum
    private Long cityId;
    private String gearBox; // enum
    private boolean auto_pay; // default false---
    private Boolean creditOption; // nullable-----
    private Boolean barterOption; // nullable-----
    private Boolean leaseOption; // nullable----
    private Boolean cashOption; // nullable-----
    private String description;//------
    private String type; //enum ->  new, standart, vip---
    private String thymbnailUrl;  //----
    private List<Integer> carSpecIds;
}
