package pst.beans.schedule;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSheduleHistory")
public class TblScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "deviceId")
    private int deviceId;

    @Column(name = "stringToSend")
    private String stringtosend;

    @Column(name = "timeToExecute")
    private Timestamp timetoexecute;

    @Column(name = "statusExecute")
    private int statusexecute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getStringtosend() {
        return stringtosend;
    }

    public void setStringtosend(String stringtosend) {
        this.stringtosend = stringtosend;
    }

    public Timestamp getTimetoexecute() {
        return timetoexecute;
    }

    public void setTimetoexecute(Timestamp timetoexecute) {
        this.timetoexecute = timetoexecute;
    }

    public int getStatusexecute() {
        return statusexecute;
    }

    public void setStatusexecute(int statusexecute) {
        this.statusexecute = statusexecute;
    }
}
