package pl.dev.CarHire.city;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.user.User;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    /*
    - TODO: Zarządzanie ID-kami żeby sie nie powtarzały (UUID)
    */

    @Id
    @Column(name = "ID_CITY", nullable = false)
    @SequenceGenerator(name = "local_city",
        sequenceName = "db_GenName")
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator="local_city")
    public Long id;

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
