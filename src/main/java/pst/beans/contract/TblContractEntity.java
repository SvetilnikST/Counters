package pst.beans.contract;

import pst.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblContract")
public class TblContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContract")
    private int idContract;

    @Column(name = "contract", nullable = true,length = 255)
    private String contract;

    @Column(name = "date")
    private int date;

    @Column(name = "description",nullable = true,length = 500)
    private String  description;

    @Column(name = "number",nullable = true,length = 50)
    private String number;

    @ManyToOne
    @JoinColumn(name = "idDevice")
    private TblDeviceEntity deviceEntity;


    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public TblDeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(TblDeviceEntity deviceEntity) {
        this.deviceEntity = deviceEntity;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
