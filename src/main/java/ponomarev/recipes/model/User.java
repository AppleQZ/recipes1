package ponomarev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ponomarev.recipes.entity.RoleEntity;
import ponomarev.recipes.entity.UserEntity;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int id;

    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;


    @NotEmpty(message = "*Please provide your name")
    private String name;


    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    private int active;


    private Set<Role> roles;

    public User(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.active = user.getActive();
        Set<Role> usersRole = null;
        for (RoleEntity role:user.getRoles()) {
            usersRole.add(new Role(role));
        }
        this.roles = usersRole;
    }
}
