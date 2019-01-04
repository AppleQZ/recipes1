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

	public UserEntity newUserEntity(User user)
	{
		this.setEmail(user.getEmail());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setPassword(user.getPassword());
		RoleEntity role = new RoleEntity();
		role.setId(1);
		role.setRole("ADMIN");
		Set<RoleEntity> setRole = new HashSet<>();
		setRole.add(role);
		this.setRoles(setRole);
		this.setActive(1);
		return this;
	}
}
