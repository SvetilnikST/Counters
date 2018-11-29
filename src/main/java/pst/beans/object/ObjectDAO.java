package pst.beans.object;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ObjectDAO extends AbstractDao<TblObjectEntity>{
    public ObjectDAO(){
        super(TblObjectEntity.class);
    }
}
