package pst.beans.typeDevice;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class TypeDeviceDAO extends AbstractDao<TblTypeDeviceEntity> {

    public TypeDeviceDAO(){
        super(TblTypeDeviceEntity.class);
    }

}
