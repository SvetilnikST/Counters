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
    private Set<TblObjectEntity> objectEntitySet = new HashSet<>();


}
