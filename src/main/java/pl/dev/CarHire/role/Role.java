package pl.dev.CarHire.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.user.User;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {


    @Id
    @Column(name = "ID_Role")
    @GeneratedValue
    public Long id_role;

    @Column(name = "Name")
    public String name;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "ID_Role")
    public List<User> users;
}
