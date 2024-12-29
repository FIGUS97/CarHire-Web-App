package pl.dev.CarHire.domain.car;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.dev.CarHire.domain.city.City;
import pl.dev.CarHire.domain.hire.CarHire;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ApiModel(description = "The representation of a car of the company.")
public class Car {

    @Id
    @Column(name = "ID_Car", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(notes = "The unique ID of the car.")
    public String id;

    @NonNull
    @Column(name = "Brand")
    @ApiModelProperty(notes = "Car's brand name.")
    public String brand;

    @Column(name = "Model")
    @ApiModelProperty(notes = "Car's model name.")
    public String model;

    @NonNull
    @Column(name = "status")
    @ApiModelProperty(notes = "Car's status of availability to rent.")
    public String status;

    @NonNull
    @Column(name = "type")
    @ApiModelProperty(notes = "Car model type")
    public String type;

    @JsonBackReference
    // This annotation prevents response loop (a car belongs to city, which has cars and the cars belong to city.. etc.)
    @ManyToOne
    @JoinColumn(name = "City")
    @ApiModelProperty(notes = "City's name that the car is operating in.")
    public City city;

    @Column(name = "Price_Per_Day")
    @ApiModelProperty(notes = "The daily price of the car.")
    public float pricePerDay;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "Car")
    public List<CarHire> carHires;

}
