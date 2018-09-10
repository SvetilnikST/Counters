package pst.asu.beans.networkDevice;

import pst.asu.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="network_device")
public class TblNetworkDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ip")
    private String ip;

    @Column(name="num_port")
    private int num_port;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "networkDeviceEntity")
    private Set<TblDeviceEntity> tblDeviceEntitySet = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNum_port() {
        return num_port;
    }

    public void setNum_port(int num_port) {
        this.num_port = num_port;
    }

    public Set<TblDeviceEntity> getTblDeviceEntitySet() {
        return tblDeviceEntitySet;
    }

    public void setTblDeviceEntitySet(Set<TblDeviceEntity> tblDeviceEntitySet) {
        this.tblDeviceEntitySet = tblDeviceEntitySet;
    }
}
