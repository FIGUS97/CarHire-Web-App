package pl.dev.CarHire.user;


import lombok.*;
import pl.dev.CarHire.hire.CarHire;
import pl.dev.CarHire.city.City;
import pl.dev.CarHire.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID_user")
})
public class User {

    @Id
    @Column(name = "ID_User")
    @SequenceGenerator(name = "local_user",
        sequenceName = "db_GenName")
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator="local_user")
    public Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ID_Role")
    public Role role;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ID_City")
    public City city;

    @Column(name = "Responsibility")
    public String responsibility;

    @Column(name = "Name_Surname")
    public String name_surname;

    @Column(name = "Age")
    public String age;

    @Column(name = "status")
    public String status;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "Customer")
    public List<CarHire> carHires;

    @Email
    public String email;

    public String username;

    public String password;


}
