package pst.asu.beans.qBoxCommon;

import pst.asu.beans.qBoxSystem.TblBoxSystemEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "qBox_data_common1")
public class TblBoxCommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "serial", length = 225, nullable = false)
    private String serial;

    @Column(name ="unit_q", nullable = false)
    private int unitQ;

    @Column(name ="time_request", nullable = false)
    private int timeRequest;

    @Column(name ="time_device", nullable = false)
    private int timeDevice;

    @Column(name ="time_on", nullable = false)
    private int timeOn;

    @Column(name ="time_run_common", nullable = false)
    private int timeRunCommon;

    @Column(name ="inStore1", nullable = false)
    private int instore1;

    @Column(name ="inStore2", nullable = false)
    public int instore2;

    @Column(name ="inStore3", nullable = false)
    public int instore3;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblBoxCommonEntity")
    private List<TblBoxSystemEntity> system = new ArrayList<>();

    public List<TblBoxSystemEntity> getSystem() {
        return system;
    }

    public void setSystem(List<TblBoxSystemEntity> system) {
        this.system = system;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getUnitQ() {
        return unitQ;
    }

    public void setUnitQ(int unitQ) {
        this.unitQ = unitQ;
    }

    public int getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(int timeRequest) {
        this.timeRequest = timeRequest;
    }

    public int getTimeDevice() {
        return timeDevice;
    }

    public void setTimeDevice(int timeDevice) {
        this.timeDevice = timeDevice;
    }

    public int getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(int timeOn) {
        this.timeOn = timeOn;
    }

    public int getTimeRunCommon() {
        return timeRunCommon;
    }

    public void setTimeRunCommon(int timeRunCommon) {
        this.timeRunCommon = timeRunCommon;
    }

    public int getInstore1() {
        return instore1;
    }

    public void setInstore1(int instore1) {
        this.instore1 = instore1;
    }

    public int getInstore2() {
        return instore2;
    }

    public void setInstore2(int instore2) {
        this.instore2 = instore2;
    }

    public int getInstore3() {
        return instore3;
    }

    public void setInstore3(int instore3) {
        this.instore3 = instore3;
    }

    public void refilAfterJson(){
        for (TblBoxSystemEntity boxSystem : this.getSystem()){
            boxSystem.setTblBoxCommonEntity(this);
        }
    }
}
