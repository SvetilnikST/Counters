package pst.beans.adress;

import javax.persistence.*;

@Entity
@Table(name = "tblAdress")
public class TblAdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdress")
    private int idStreet;

    @Column(name = "home")
    private String home;


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
