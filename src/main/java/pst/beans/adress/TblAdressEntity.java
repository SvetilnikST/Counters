package pst.beans.adress;

import pst.beans.city.TblCityEntity;
import pst.beans.object.TblObjectEntity;
import pst.beans.street.TblStreetEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblAdress")
public class TblAdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdress", nullable = false)
    private int idAdress;

    @Column(name = "home", nullable = false, length = 100)
    private String home;


    @ManyToOne
    @JoinColumn(name = "idStreet")
    private TblStreetEntity streetEntity;

    @ManyToOne
    @JoinColumn(name = "idCity")
    private TblCityEntity cityEntity;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adressEntity")
    private Set<TblObjectEntity> objectEntitySet = new HashSet<>();

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
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

    public Set<TblObjectEntity> getObjectEntitySet() {
        return objectEntitySet;
    }

    public void setObjectEntitySet(Set<TblObjectEntity> objectEntitySet) {
        this.objectEntitySet = objectEntitySet;
    }
}
