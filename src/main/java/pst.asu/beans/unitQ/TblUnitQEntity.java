package pst.asu.beans.unitQ;

import pst.asu.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="unitQ")
public class TblUnitQEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id;

@Column(name = "name", nullable = false)
private String name;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "unitQEntity")
private Set<TblDeviceEntity> tblDeviceEntitySet = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TblDeviceEntity> getTblDeviceEntitySet() {
        return tblDeviceEntitySet;
    }

    public void setTblDeviceEntitySet(Set<TblDeviceEntity> tblDeviceEntitySet) {
        this.tblDeviceEntitySet = tblDeviceEntitySet;
    }
}
