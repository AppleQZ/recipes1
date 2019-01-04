package ponomarev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ponomarev.recipes.entity.RoleEntity;
import ponomarev.recipes.entity.UserEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {

	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public User newUser(UserEntity user) {
		this.setId(user.getId());
		this.setEmail(user.getEmail());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setPassword(user.getPassword());
		this.setActive(user.getActive());
		Set<Role> roles = new HashSet<>();
		for (RoleEntity role : user.getRoles()) {
			Role newRole = new Role();
			newRole.setRole(role.getRole());
			newRole.setId(role.getId());
			roles.add(newRole);
		}
		this.setRoles(roles);
		return this;
	}


}
