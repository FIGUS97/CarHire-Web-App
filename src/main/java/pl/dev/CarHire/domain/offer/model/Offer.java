package pl.dev.CarHire.domain.offer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.dev.CarHire.domain.user.User;

import javax.persistence.*;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Customer's offer representation.")
public class Offer {
    @Id
    @Column(name = "ID_Hire", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    @NonNull
    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @NonNull
    @Column(name = "pricePerDay")
    public Double pricePerDay;

    @Column(name = "photosReference")
    @ApiModelProperty(notes = "Reference Id to offer's photos group.")
    public String photosReference;

    @JoinColumn(name = "offerer")
    public User offerer;
}
