package pst.beans.verification;

import pst.beans.device.TblDeviceEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblVerification")
public class TblVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVerification")
    private int idVerification;

    @Column(name = "IVB", nullable = false)
    private int IVB;

    @Column(name = "PPR", nullable = false)
    private int PPR;

    @Column(name = "TCP", nullable = false)
    private int TCP;

    @Column(name = "additionalDevice", nullable = false)
    private int additionalDevice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitQEntity")
    private Set<TblDeviceEntity> tblDeviceEntitySet = new HashSet<>();

    public int getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(int idVerification) {
        this.idVerification = idVerification;
    }

    public int getIVB() {
        return IVB;
    }

    public void setIVB(int IVB) {
        this.IVB = IVB;
    }

    public int getPPR() {
        return PPR;
    }

    public void setPPR(int PPR) {
        this.PPR = PPR;
    }

    public int getTCP() {
        return TCP;
    }

    public void setTCP(int TCP) {
        this.TCP = TCP;
    }

    public int getAdditionalDevice() {
        return additionalDevice;
    }

    public void setAdditionalDevice(int additionalDevice) {
        this.additionalDevice = additionalDevice;
    }

    public Set<TblDeviceEntity> getTblDeviceEntitySet() {
        return tblDeviceEntitySet;
    }

    public void setTblDeviceEntitySet(Set<TblDeviceEntity> tblDeviceEntitySet) {
        this.tblDeviceEntitySet = tblDeviceEntitySet;
    }
}
