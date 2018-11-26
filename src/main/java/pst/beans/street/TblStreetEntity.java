package pst.beans.street;

import javax.persistence.*;

@Entity
@Table(name = "tblStreet")
public class TblStreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStreet")
    private int idStreet;

    @Column(name = "nameStreet")
    private String nameStreet;


    public int getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(int idStreet) {
        this.idStreet = idStreet;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }
}
