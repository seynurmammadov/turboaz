package az.code.myauto.models.dtos;

import az.code.myauto.models.Make;
import az.code.myauto.models.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ModelDTO {
    Long id;
    String name;
    public ModelDTO(Model model){
        this.id=model.getId();
        this.name=model.getName();
    }
}
