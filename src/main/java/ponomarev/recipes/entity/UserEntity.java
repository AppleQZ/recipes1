package ponomarev.recipes.entity;

import lombok.Data;
import ponomarev.recipes.model.Role;
import ponomarev.recipes.model.User;
import sun.invoke.empty.Empty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "active", columnDefinition = "int default 1")
	private int active;

	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	public UserEntity(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.active = user.getActive();
		 Set<RoleEntity> usersRole = null;
		for (Role role:user.getRoles()) {
			usersRole.add(new RoleEntity(role));
		}
		this.roles = usersRole;
	}
}
