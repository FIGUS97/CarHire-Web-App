package pl.dev.CarHire.domain.role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {


    @Id
    @Column(name = "ID_Role")
    @GeneratedValue
    public String id_role;

    @Column(name = "Name")
    public String name;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "ID_Role")
    public List<User> users;
}
