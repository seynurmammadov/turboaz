package az.code.myauto.models.dtos;

import java.time.LocalDateTime;

public class ListingListDTO { // list dtos ,  search return dtos
    Long id;
    String makeName;
    String modelName;
    String thumbnailUrl;
    String cityName;
    Integer price;
    Integer mileage;
    Integer year;
    LocalDateTime updatedAt;
}
