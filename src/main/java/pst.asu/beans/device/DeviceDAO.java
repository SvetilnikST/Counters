package pst.asu.beans.device;

import pst.asu.beans.DAO;

import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceDAO extends DAO {

    public TblDeviceEntity read(int id){
        return entityManager.find(TblDeviceEntity.class, id);
    }

    public List<TblDeviceEntity> readList(){
        TypedQuery<TblDeviceEntity> query = entityManager.createQuery(
                "from TblDeviceEntity entity", TblDeviceEntity.class);
        List<TblDeviceEntity> tblDeviceEntities = query.getResultList();
        return tblDeviceEntities;
    }
}
