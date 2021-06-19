package az.code.myauto.models.dtos;

import az.code.myauto.models.Equipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CarSpecDTO {
    String name;
    public CarSpecDTO(Equipment equipment){
        this.name=equipment.getName();
    }
}
