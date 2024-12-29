package pl.dev.CarHire.domain.city;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.dev.CarHire.domain.car.Car;
import pl.dev.CarHire.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @Column(name = "ID_CITY", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @Column(name = "Name")
    public String name;

    @JsonManagedReference    // This annotation prevents response loop (a car belongs to city, which has cars and the cars belong to city.. etc.)
    @OneToMany
    @JoinColumn(name = "City")
    public List<Car> cars;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "ID_City")
    public List<User> users;
}
