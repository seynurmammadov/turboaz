package az.code.myauto.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "makes")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany( mappedBy = "make")
    private List<Model> models = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "make")
    private List<Auto> autos;

}
