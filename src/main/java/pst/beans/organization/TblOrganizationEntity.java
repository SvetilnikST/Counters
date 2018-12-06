package pst.beans.organization;

import pst.beans.object.TblObjectEntity;

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
}
