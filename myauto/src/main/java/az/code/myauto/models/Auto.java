package az.code.myauto.models;

import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import az.code.myauto.models.enums.GearBox;
import lombok.*;

import javax.persistence.*;
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
    @JoinColumn(name="makeId")
    private Make make;
    private int year;
    private int price;
    private int mileage;
    private FuelType fueltype;
    private BodyType bodyType;
    private Color color;
    private GearBox gearBox;
    @OneToOne(mappedBy = "auto")
    private Listing listing;
    @ManyToMany
    @JoinTable(name="auto_equipment",
            joinColumns=@JoinColumn(name="autoId"),
            inverseJoinColumns=@JoinColumn(name="equipmentId"))
    List<Equipment> equipments;
}
