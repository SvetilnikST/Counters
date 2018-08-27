package pst.asu.beans.qBox;

import javax.persistence.*;

@Entity
@Table(name = "qBox_data_common")
public class TblBoxCommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "serial", nullable = false)
    public String serial;

    @Column(name ="unit_q", nullable = false)
    public Long unitQ;

    @Column(name ="time_request", nullable = false)
    public Long timeRequest;
    @Column(name ="time_device", nullable = false)
    public Long timeDevice;

    @Column(name ="time_on", nullable = false)
    public Long timeOn;

    @Column(name ="time_run_common", nullable = false)
    public Long timeRunCommon;

    @Column(name ="inStore1", nullable = false)
    public Long instore1;

    @Column(name ="inStore2", nullable = false)
    public Long instore2;

    @Column(name ="inStore3", nullable = false)
    public Long instore3;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getUnitQ() {
        return unitQ;
    }

    public void setUnitQ(Long unitQ) {
        this.unitQ = unitQ;
    }

    public Long getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(Long timeRequest) {
        this.timeRequest = timeRequest;
    }

    public Long getTimeDevice() {
        return timeDevice;
    }

    public void setTimeDevice(Long timeDevice) {
        this.timeDevice = timeDevice;
    }

    public Long getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(Long timeOn) {
        this.timeOn = timeOn;
    }

    public Long getTimeRunCommon() {
        return timeRunCommon;
    }

    public void setTimeRunCommon(Long timeRunCommon) {
        this.timeRunCommon = timeRunCommon;
    }

    public Long getInstore1() {
        return instore1;
    }

    public void setInstore1(Long instore1) {
        this.instore1 = instore1;
    }

    public Long getInstore2() {
        return instore2;
    }

    public void setInstore2(Long instore2) {
        this.instore2 = instore2;
    }

    public Long getInstore3() {
        return instore3;
    }

    public void setInstore3(Long instore3) {
        this.instore3 = instore3;
    }
}
