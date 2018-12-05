package pst.beans.adress;

import pst.beans.city.TblCityEntity;
import pst.beans.street.TblStreetEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblObject")
public class TblObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idObject", nullable = false)
    private int idObject;

    @Column(name = "nameObject", nullable = false, length = 100)
    private String nameObject;



    @Column(name = "home", nullable = false, length = 100)
    private String home;


    @ManyToOne
    @JoinColumn(name = "idStreet")
    private TblStreetEntity streetEntity;

    @ManyToOne
    @JoinColumn(name = "idCity")
    private TblCityEntity cityEntity;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objectEntitySet")
//    private Set<TblObjectEntity> objectEntitySet = new HashSet<>();


    public int getIdObject() {
        return idObject;
    }

    public void setIdObject(int idObject) {
        this.idObject = idObject;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
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

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }
}
