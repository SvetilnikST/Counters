package pst.beans.networkDevice;

import pst.beans.AbstractDao;

public class NetworkDeviceDAO extends AbstractDao<TblNetworkDeviceEntity> {
    public NetworkDeviceDAO(){
        super(TblNetworkDeviceEntity.class);
    }
}
