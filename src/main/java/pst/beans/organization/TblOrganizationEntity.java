package pst.beans.organization;

import pst.beans.city.TblCityEntity;
import pst.beans.object.TblObjectEntity;
import pst.beans.street.TblStreetEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblOrganization")
public class TblOrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrganization", nullable = false)
    private int idOrganization;

    @Column(name = "nameOrganization", nullable = false, length = 255)
    private String nameOrganization;

    @Column(name = "UNP", nullable = false)
    private int UNP;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "idStreet")
    private TblStreetEntity streetEntity;

    @ManyToOne
    @JoinColumn(name = "idCity")
    private TblCityEntity cityEntity;

    @Column(name = "home", nullable = false , length = 25)
    private String home;


    @Column(name = "DataBoss", nullable = true , length = 500)
    private String dataBoss;

    @Column(name = "DataWoker", nullable = true , length = 500)
    private String dataWoker;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationEntity")
    private Set<TblObjectEntity> tblObjectEntitySet = new HashSet<>();

    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public Set<TblObjectEntity> getTblObjectEntitySet() {
        return tblObjectEntitySet;
    }

    public void setTblObjectEntitySet(Set<TblObjectEntity> tblObjectEntitySet) {
        this.tblObjectEntitySet = tblObjectEntitySet;
    }

    public int getUNP() {
        return UNP;
    }

    public void setUNP(int UNP) {
        this.UNP = UNP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TblStreetEntity getStreetEntity() {
        return streetEntity;
    }

    public void setStreetEntity(TblStreetEntity streetEntity) {
        this.streetEntity = streetEntity;
    }

    public TblCityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(TblCityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDataBoss() {
        return dataBoss;
    }

    public void setDataBoss(String dataBoss) {
        this.dataBoss = dataBoss;
    }

    public String getDataWoker() {
        return dataWoker;
    }

    public void setDataWoker(String dataWoker) {
        this.dataWoker = dataWoker;
    }
}
