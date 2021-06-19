package az.code.myauto.models.dtos;

import az.code.myauto.models.Make;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MakeDTO {
    Long id;
    String name;
    public MakeDTO(Make make){
        this.id=make.getId();
        this.name=make.getName();
    }

}
