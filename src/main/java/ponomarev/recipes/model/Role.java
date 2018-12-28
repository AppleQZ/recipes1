package ponomarev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ponomarev.recipes.entity.RoleEntity;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;

    public Role(RoleEntity role) {
        this.role = role.getRole();
    }
}
