package pst.beans.device;


import pst.beans.networkDevice.TblNetworkDeviceEntity;
import pst.beans.typeDevice.TblTypeDeviceEntity;
import pst.beans.unitQ.TblUnitQEntity;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class TblDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name= "name", nullable = false, length = 20)
    private String name;

    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name="lastRequestDate", nullable = false)
    private int lastRequestDate;

    @Column(name="requestsCount", nullable = false)
    private int requestsCount;

    @ManyToOne
    @JoinColumn(name="type_id")
    private TblTypeDeviceEntity typeDeviceEntity;

    @ManyToOne
    @JoinColumn(name = "unitQ_id")
    private TblUnitQEntity unitQEntity;

    @ManyToOne
    @JoinColumn(name="network_id")
    private TblNetworkDeviceEntity networkDeviceEntity;

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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getLastRequestDate() {
        return lastRequestDate;
    }

    public void setLastRequestDate(int lastRequestDate) {
        this.lastRequestDate = lastRequestDate;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
    }

    public TblTypeDeviceEntity getTypeDeviceEntity() {
        return typeDeviceEntity;
    }

    public void setTypeDeviceEntity(TblTypeDeviceEntity typeDeviceEntity) {
        this.typeDeviceEntity = typeDeviceEntity;
    }

    public TblUnitQEntity getUnitQEntity() {
        return unitQEntity;
    }

    public void setUnitQEntity(TblUnitQEntity unitQEntity) {
        this.unitQEntity = unitQEntity;
    }

    public TblNetworkDeviceEntity getNetworkDeviceEntity() {
        return networkDeviceEntity;
    }

    public void setNetworkDeviceEntity(TblNetworkDeviceEntity networkDeviceEntity) {
        this.networkDeviceEntity = networkDeviceEntity;
    }
}