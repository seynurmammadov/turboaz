package az.code.myauto.models;

import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.enums.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autoId")
    private Auto auto;

    private boolean auto_pay;
    private Boolean creditOption;
    private Boolean barterOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private String description;
    ListingType type;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listing")
    private List<Thumbnail> thumbnails = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User User;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public Listing(ListingCreationDTO data, UserData user) {
        this.creditOption = data.getCreditOption();
        this.barterOption = data.getBarterOption();
        this.leaseOption = data.getLeaseOption();
        this.cashOption = data.getCashOption();
        this.type = ListingType.valueOf(data.getType());
        this.city = City.builder().id(data.getCityId()).build();
        this.auto_pay = data.isAuto_pay();
        this.description = data.getDescription();
        this.thumbnails.add(Thumbnail.builder().name(data.getThymbnailUrl()).build());
        this.User = new User(user);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.auto.setMake(Make.builder().id(data.getMakeId()).build());
        this.auto.setYear(data.getYear());
        this.auto.setPrice(data.getPrice());
        this.auto.setMileage(data.getMileage());
        this.auto.setMileage(data.getMileage());
        this.auto.setFueltype(FuelType.valueOf(data.getFuelType()));
        this.auto.setBodyType(BodyType.valueOf(data.getBodyType()));
        this.auto.setColor(Color.valueOf(data.getColor()));
        this.auto.setGearBox(GearBox.valueOf(data.getGearBox()));
        this.auto.addEquipments(data.getCarSpecIds());
        this.auto.setModel(Model.builder().id(data.getModelId()).build());
    }
}