package az.code.myauto.models.dtos;

import az.code.myauto.models.City;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class CityDTO {
    Long id;
    String name;
    public CityDTO(City city){
        this.id=city.getId();
        this.name=city.getName();
    }
}
