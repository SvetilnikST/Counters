package pst.beans.street;

import pst.beans.adress.TblAdressEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblStreet")
public class TblStreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStreet")
    private int idStreet;

    @Column(name = "nameStreet")
    private String nameStreet;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "streetEntity")
    private Set<TblAdressEntity> tblAdressEntitySet = new HashSet<>();


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

    public Set<TblAdressEntity> getTblAdressEntitySet() {
        return tblAdressEntitySet;
    }

    public void setTblAdressEntitySet(Set<TblAdressEntity> tblAdressEntitySet) {
        this.tblAdressEntitySet = tblAdressEntitySet;
    }
}
