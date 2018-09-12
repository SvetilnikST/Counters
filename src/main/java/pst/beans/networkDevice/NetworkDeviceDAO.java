package pst.beans.networkDevice;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class NetworkDeviceDAO extends AbstractDao<TblNetworkDeviceEntity> {
    public NetworkDeviceDAO(){
        super(TblNetworkDeviceEntity.class);
    }
}
