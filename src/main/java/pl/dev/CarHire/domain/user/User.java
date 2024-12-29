package pl.dev.CarHire.domain.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.dev.CarHire.domain.city.City;
import pl.dev.CarHire.domain.hire.CarHire;
import pl.dev.CarHire.domain.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

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
