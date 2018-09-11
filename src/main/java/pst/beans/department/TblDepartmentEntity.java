package pst.beans.department;

import pst.beans.user.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblDepartment")
public class TblDepartmentEntity {
    @Id
    @Column(name="idDepartment", nullable = false)
    private int idDepartment;

    @Column(name = "department", nullable = false, length = 20)
    private  String department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentEntity")
    private Set<UserEntity> userEntitySet = new HashSet<>();

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
    }
}
