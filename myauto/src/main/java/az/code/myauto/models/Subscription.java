package az.code.myauto.models;

import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "makeId")
    private Make make = new Make();
    @ManyToOne
    @JoinColumn(name = "modelId")
    private Model model= new Model();
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city= new City();

    private FuelType fuelType;
    private BodyType bodyType;

    private Color color;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "subs_equipment",
            joinColumns = @JoinColumn(name = "subId"),
            inverseJoinColumns = @JoinColumn(name = "equipmentId"))
    List<Equipment> equipments = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "userId")
    private User User= new User();

    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;

    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;
    private LocalDateTime createdAt;

}
