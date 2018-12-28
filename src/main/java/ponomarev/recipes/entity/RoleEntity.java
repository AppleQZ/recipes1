package ponomarev.recipes.entity;

import lombok.Data;
import ponomarev.recipes.model.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int id;
	@Column(name = "role")
	private String role;

	public RoleEntity(Role role) {
		this.role = role.getRole();
	}
}
