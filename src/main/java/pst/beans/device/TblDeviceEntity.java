package pst.beans.device;


//import pst.beans.networkDevice.TblNetworkDeviceEntity;
import pst.beans.typeDevice.TblTypeDeviceEntity;
import pst.beans.unitQ.TblUnitQEntity;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Integer lastRequestDate;

    @Column(name="requestsCount", nullable = false)
    private Integer requestsCount;

    @Column(name="ip",nullable = false)
    private String ip;

    @Column(name = "num_port", nullable = false)
    private int num_port;

    @Column(name = "requestInterval", nullable = false)
    private int requestInterval;

    @ManyToOne
    @JoinColumn(name="type_id")
    private TblTypeDeviceEntity typeDeviceEntity;

    @ManyToOne
    @JoinColumn(name = "unitQ_id")
    private TblUnitQEntity unitQEntity;

//    @ManyToOne
//    @JoinColumn(name="network_id")
//    private TblNetworkDeviceEntity networkDeviceEntity;

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



    public int getRequestsCount() {
        return requestsCount;
    }

    public void setLastRequestDate(Integer lastRequestDate) {
        this.lastRequestDate = lastRequestDate;
    }

    public void setRequestsCount(Integer requestsCount) {
        this.requestsCount = requestsCount;
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

    public int getRequestInterval() {
        return requestInterval;
    }

    public void setRequestInterval(int requestInterval) {
        this.requestInterval = requestInterval;
    }

//    public TblNetworkDeviceEntity getNetworkDeviceEntity() {
//        return networkDeviceEntity;
//    }
//
//    public void setNetworkDeviceEntity(TblNetworkDeviceEntity networkDeviceEntity) {
//        this.networkDeviceEntity = networkDeviceEntity;
//    }
}
