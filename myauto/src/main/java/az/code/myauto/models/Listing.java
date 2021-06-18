package az.code.myauto.models;

import az.code.myauto.models.enums.ListingType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    ListingType type;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "listing")
    private List<Thumbnail> thumbnails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "userId")
    private mUser mUser;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

}