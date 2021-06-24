package az.code.myauto.models;


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
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private double balance = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
    private List<Transaction> transactions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
    private List<Listing> listings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
    private List<Subscription> subscriptions;

    private Boolean isActive = false;

    @OneToOne(mappedBy = "user")
    private UserConfirmationToken userConfirmationToken;

}