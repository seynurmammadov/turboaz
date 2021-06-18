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
@Table(name = "makes")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "Make")
    private List<Model> models;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "make")
    private List<Auto> autos;
}
