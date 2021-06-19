package az.code.myauto.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String name;

    @ManyToMany(mappedBy = "equipments")
    private List<Auto> autos;

}
