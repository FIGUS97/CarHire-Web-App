package pl.dev.CarHire.hire;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.user.User;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarHire {
    @Id
    @Column(name = "ID_Hire", nullable = false)
    @SequenceGenerator(name = "local_carHire",
        sequenceName = "db_GenName")
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator="local_carHire")
    public Long id;

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
    public Integer number_days;

    @Column(name = "Amount")
    public Float amount;

}
