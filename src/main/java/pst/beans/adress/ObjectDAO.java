package pst.beans.adress;

import pst.beans.AbstractDao;
import pst.beans.organization.TblOrganizationEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ObjectDAO extends AbstractDao<TblObjectEntity>{
    public ObjectDAO(){
        super(TblObjectEntity.class);
    }
}
