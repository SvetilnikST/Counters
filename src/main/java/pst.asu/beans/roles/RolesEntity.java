package pst.asu.beans.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblRoles")
public class RolesEntity {

    @Id
    @Column(name="idRole", nullable = false)
    private int idRole;

    @Column(name="role", nullable = false)
    private String role;



    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
