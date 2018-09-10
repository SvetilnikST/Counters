package pst.asu.beans.typeDevice;


import pst.asu.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="typeDevice")
public class TblTypeDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="name", nullable = false, length = 20)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeDeviceEntity")
    private Set<TblDeviceEntity> deviceEntitySet = new HashSet<>();

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

    public Set<TblDeviceEntity> getDeviceEntitySet() {
        return deviceEntitySet;
    }

    public void setDeviceEntitySet(Set<TblDeviceEntity> deviceEntitySet) {
        this.deviceEntitySet = deviceEntitySet;
    }
}
