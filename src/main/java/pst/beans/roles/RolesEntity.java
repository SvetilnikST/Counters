package pst.beans.roles;


import pst.beans.rightsItems.RightsItemEntity;
import pst.beans.user.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblRoles")
public class RolesEntity {

    @Id
    @Column(name = "idRole", nullable = false)
    private int idRole;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany
    @JoinTable(name = "tblRoleRights",
            joinColumns = @JoinColumn(name = "idRole"),
            inverseJoinColumns = @JoinColumn(name = "idRightsItem"))
    private Set<RightsItemEntity> rightsItemEntitySet = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "tblUserRole",
            joinColumns = @JoinColumn(name = "idRole"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    private Set<UserEntity> userEntitySet = new HashSet<>();



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

    public Set<RightsItemEntity> getRightsItemEntitySet() {
        return rightsItemEntitySet;
    }

    public void setRightsItemEntitySet(Set<RightsItemEntity> rightsItemEntitySet) {
        this.rightsItemEntitySet = rightsItemEntitySet;
    }

    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
    }

}
