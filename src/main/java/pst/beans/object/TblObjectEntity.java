package pst.beans.object;

import pst.beans.adress.TblAdressEntity;
import pst.beans.organization.TblOrganizationEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblObject")
public class TblObjectEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id", nullable = false)
     private int id;

     @Column(name = "nameObject", nullable = false)
     private String nameObject;


    @ManyToOne
    @JoinColumn(name = "idAdress")
    private TblAdressEntity adressEntity;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objectEntity")
    private Set<TblOrganizationEntity> tblOrganizationEntitySet = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "idDevice")
    private TblOrganizationEntity organizationEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public TblAdressEntity getAdressEntity() {
        return adressEntity;
    }

    public void setAdressEntity(TblAdressEntity adressEntity) {
        this.adressEntity = adressEntity;
    }

    public Set<TblOrganizationEntity> getTblOrganizationEntitySet() {
        return tblOrganizationEntitySet;
    }

    public void setTblOrganizationEntitySet(Set<TblOrganizationEntity> tblOrganizationEntitySet) {
        this.tblOrganizationEntitySet = tblOrganizationEntitySet;
    }

    public TblOrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(TblOrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }
}
