package az.code.myauto.models.dtos;

import az.code.myauto.models.Make;

public class MakeDTO {
    Long id;
    String name;
    public MakeDTO(Make make){
        this.id=make.getId();
        this.name=make.getName();
    }

}
