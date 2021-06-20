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

    @Column(nullable = false, unique = true)
    private String email;
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    private String fullname;
    private String phonenumber;

    private double balance = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "User")
    private List<Transaction> transactions;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "User")
    private List<Listing> listings;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "User")
    private List<Subscription> subscriptions;

    public  User(UserData data){
        this.username = data.getUsername();
        this.fullname = data.getFullName();
        this.email = data.getEmail();
        this.phonenumber = data.getPhoneNumber();
    }
}