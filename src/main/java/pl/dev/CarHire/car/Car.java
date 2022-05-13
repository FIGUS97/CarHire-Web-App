package pl.dev.CarHire.car;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.dev.CarHire.hire.CarHire;
import pl.dev.CarHire.city.City;

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
    @SequenceGenerator(name = "local_car",
        sequenceName = "db_GenName")
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator="local_car")
    public Long id;

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
