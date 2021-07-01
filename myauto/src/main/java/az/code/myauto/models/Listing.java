package az.code.myauto.models;

import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.UserDTO;
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
@ToString
@EqualsAndHashCode
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listing",fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive = true;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "listing")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User User;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

}