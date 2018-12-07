package pst.beans.city;

import pst.beans.object.TblObjectEntity;
import pst.beans.organization.TblOrganizationEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblCity")
public class TblCityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCity")
    private int idCity;

    @Column(name = "nameCity", nullable = false)
    private String nameCity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityEntity")
    private Set<TblObjectEntity> tblObjectEntitySet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityEntity")
    private Set<TblOrganizationEntity> tblOrganizationEntitySet = new HashSet<>();

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

    public Set<TblObjectEntity> getTblObjectEntitySet() {
        return tblObjectEntitySet;
    }

    public void setTblObjectEntitySet(Set<TblObjectEntity> tblObjectEntitySet) {
        this.tblObjectEntitySet = tblObjectEntitySet;
    }

    public Set<TblOrganizationEntity> getTblOrganizationEntitySet() {
        return tblOrganizationEntitySet;
    }

    public void setTblOrganizationEntitySet(Set<TblOrganizationEntity> tblOrganizationEntitySet) {
        this.tblOrganizationEntitySet = tblOrganizationEntitySet;
    }
}
