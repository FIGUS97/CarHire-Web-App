package pl.dev.CarHire.domain.photo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ApiModel(description = "Image used in the application")
public class PhotoRepository {
    @Id
    @Column(name = "ID_Car", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(notes = "The unique ID of the photo.")
    public String id;

    public String offerReference;
}
