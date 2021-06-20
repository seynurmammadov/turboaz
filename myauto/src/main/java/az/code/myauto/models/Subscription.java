package az.code.myauto.models;

import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

    public Subscription(SubscriptionDto data, String username) {
        this.minYear = data.getMinYear();
        this.maxYear = data.getMaxYear();
        this.minPrice = data.getMinPrice();
        this.maxPrice = data.getMaxPrice();
        this.minMileage = data.getMinMileage();
        this.maxMileage = data.getMaxMileage();
        this.loanOption = data.getLoanOption();
        this.leaseOption = data.getLeaseOption();
        this.cashOption = data.getCashOption();
        this.barterOption = data.getBarterOption();
        this.name = data.getName();
        this.make.setId(data.getMakeId());
        this.model.setId(data.getModelId());
        this.city.setId(data.getCityId());
        this.fuelType = FuelType.valueOf(data.getFuelType());
        this.bodyType = BodyType.valueOf(data.getBodyType());
        this.color = Color.valueOf(data.getColor());
        data.getSpecs().forEach(e -> equipments.add(Equipment.builder().id(e).build()));
        this.createdAt = LocalDateTime.now();
        this.getUser().setUsername(username);
    }
}
