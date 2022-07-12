package pl.dev.CarHire.model.city;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.model.car.Car;
import pl.dev.CarHire.model.user.User;

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
