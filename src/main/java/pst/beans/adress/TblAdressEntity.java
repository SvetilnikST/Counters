package pst.beans.adress;

import pst.beans.city.TblCityEntity;
import pst.beans.street.TblStreetEntity;

import javax.persistence.*;

@Entity
@Table(name = "tblAdress")
public class TblAdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdress", nullable = false)
    private int idStreet;

    @Column(name = "home", nullable = false)
    private String home;


    @ManyToOne
    @JoinColumn(name = "idStreet")
    private TblStreetEntity streetEntity;

    @ManyToOne
    @JoinColumn(name = "idAdress")
    private TblCityEntity cityEntity;


    public int getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(int idStreet) {
        this.idStreet = idStreet;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
