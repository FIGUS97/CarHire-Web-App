package pl.dev.CarHire.domain.hire;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.dev.CarHire.domain.car.Car;
import pl.dev.CarHire.domain.user.User;

import javax.persistence.*;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarHire {
    @Id
    @Column(name = "ID_Hire", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Customer")
    public User customer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Car")
    public Car car;

    @Column(name = "status")
    public String status;

    @Column(name = "Number_days")
    public Integer days;

    @Column(name = "Amount")
    public Float price;

}
