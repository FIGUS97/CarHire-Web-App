package pl.dev.CarHire.model.car;

import javax.persistence.*;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.dev.CarHire.model.hire.CarHire;
import pl.dev.CarHire.model.city.City;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Car {

    @Id
    @Column(name = "ID_Car", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @NonNull
    @Column(name = "Brand")
    public String brand;

    @Column(name = "Model")
    public String model;

    @NonNull
    @Column(name = "status")
    public String status;

    @JsonBackReference    // This annotation prevents response loop (a car belongs to city, which has cars and the cars belong to city.. etc.)
    @ManyToOne
    @JoinColumn(name = "City")
    public City city;

    @Column(name = "Price_Per_Day")
    public float pricePerDay;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "Car")
    public List<CarHire> carHires;

}
