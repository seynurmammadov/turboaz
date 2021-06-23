package az.code.myauto.models;

import az.code.myauto.models.dtos.CarSpecDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.models.enums.GearBox;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "makeId")
    private Make make = new Make();
    private int year;
    private int price;
    private int mileage;
    private FuelType fueltype;
    private BodyType bodyType;
    private Color color;
    private GearBox gearBox;
    @OneToOne(mappedBy = "auto")
    private Listing listing;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "auto_equipment",
            joinColumns = @JoinColumn(name = "autoId"),
            inverseJoinColumns = @JoinColumn(name = "equipmentId"))
    List<Equipment> equipments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    Model model;
}
