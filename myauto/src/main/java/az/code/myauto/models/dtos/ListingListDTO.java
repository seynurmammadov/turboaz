package az.code.myauto.models.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@ToString
public class ListingListDTO {
    Long id;
    String makeName;
    String modelName;
    List<ImageDTO> images;
    String cityName;
    Integer price;
    Integer mileage;
    Integer year;
    LocalDateTime updatedAt;
}
