package az.code.myauto.models.dtos;

import az.code.myauto.models.Equipment;

public class CarSpecDTO {
    String name;
    public CarSpecDTO(Equipment equipment){
        this.name=equipment.getName();
    }
}
