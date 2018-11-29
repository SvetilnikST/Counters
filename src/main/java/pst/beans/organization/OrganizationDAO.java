package pst.beans.organization;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class OrganizationDAO extends AbstractDao<TblOrganizationEntity>{
    public OrganizationDAO(){
        super(TblOrganizationEntity.class);
    }
}
