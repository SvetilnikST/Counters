package pst.beans.object;

import pst.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblObject")
public class TblObjectEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id", nullable = false)
     private int id;

     @Column(name = "nameObject", nullable = false)
     private String nameObject;

//    @ManyToMany (fetch = FetchType.EAGER)
//    @JoinTable(name = "device", joinColumns = @JoinColumn(name = "id"))
//    private Set<TblDeviceEntity> deviceEntitySet = new HashSet<>();
//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

//    public Set<TblDeviceEntity> getDeviceEntitySet() {
//        return deviceEntitySet;
//    }
//
//    public void setDeviceEntitySet(Set<TblDeviceEntity> deviceEntitySet) {
//        this.deviceEntitySet = deviceEntitySet;
//    }
}
