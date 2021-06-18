package az.code.myauto.models.dtos;

import java.util.List;

public class ListingCreationDTO {      // CREATE OLDUQDAN SONRA RESPONSE ListingGetDTO OLARAQ QAYIDACAQ
    Long makeId;
    Long modelId;
    int year;
    int price;
    int mileage;
    String fuelType; // enum
    String bodyType; // enum
    String color; // enum
    Long cityId;
    String gearBox; // enum
    boolean auto_pay; // default false---
    boolean creditOption; // nullable-----
    boolean barterOption; // nullable-----
    boolean leaseOption; // nullable----
    boolean cashOption; // nullable-----
    String description;//------
    String type; //enum ->  new, standart, vip---
    String thymbnailUrl;  //----
    List<Integer> carSpecIds;
}
