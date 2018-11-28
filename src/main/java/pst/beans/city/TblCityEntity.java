package pst.beans.city;


import pst.beans.adress.TblAdressEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblсity")
public class TblCityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCity")
    private int idCity;

    @Column(name = "nameCity", nullable = false)
    private String nameCity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityEntity")
    private Set<TblAdressEntity> tblAdressEntitySet = new HashSet<>();

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }


}
