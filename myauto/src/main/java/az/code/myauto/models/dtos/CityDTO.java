package az.code.myauto.models.dtos;

import az.code.myauto.models.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CityDTO {
    Long id;
    String name;
    public CityDTO(City city){
        this.id=city.getId();
        this.name=city.getName();
    }
}
