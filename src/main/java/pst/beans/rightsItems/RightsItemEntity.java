package pst.beans.rightsItems;

import pst.beans.roles.RolesEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblRightsItems")
public class RightsItemEntity {

    @Id
    @Column(name="idRightItem", nullable = false)
    private int idRightItem;
    @Column(name="rightItem", nullable = false)
    private String rightItem;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tblRoleRights",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "idRightsItem"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<RolesEntity> roles = new HashSet<>();

    public Set<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesEntity> roles) {
        this.roles = roles;
    }

    public int getIdRightItem() {
        return idRightItem;
    }

    public void setIdRightItem(int idRightItem) {
        this.idRightItem = idRightItem;
    }

    public String getRightItem() {
        return rightItem;
    }

    public void setRightItem(String rightItem) {
        this.rightItem = rightItem;
    }
}
