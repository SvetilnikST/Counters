package pst.beans.device;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class DeviceDAO extends AbstractDao<TblDeviceEntity> {
    public DeviceDAO(){
        super(TblDeviceEntity.class);
    }
}
