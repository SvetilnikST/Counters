package pst.asu.beans.device;

import pst.asu.beans.AbstractDao;

public class DeviceDAO extends AbstractDao<TblDeviceEntity> {
    public DeviceDAO(){
        super(TblDeviceEntity.class);
    }
}
