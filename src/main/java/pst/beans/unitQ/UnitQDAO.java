package pst.beans.unitQ;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class UnitQDAO extends AbstractDao<TblUnitQEntity> {
    public UnitQDAO(){
        super(TblUnitQEntity.class);
    }
}
