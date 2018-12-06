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

    @Column(name = "contract")
    private String contract;

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
}
