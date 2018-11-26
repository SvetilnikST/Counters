package pst.beans.street;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class StreetDAO extends AbstractDao<TblStreetEntity> {
    public StreetDAO(){
        super(TblStreetEntity.class);
    }
}
