package az.code.myauto.models.dtos;

import az.code.myauto.models.Make;
import az.code.myauto.models.Model;

public class ModelDTO {
    Long id;
    String name;
    public ModelDTO(Model model){
        this.id=model.getId();
        this.name=model.getName();
    }
}
