package pst.asu.beans.typeDevice;

import pst.asu.beans.DAO;

import javax.persistence.TypedQuery;
import java.util.List;

public class TypeDeviceDAO extends DAO {

    public TblTypeDeviceEntity read(int id){
        return entityManager.find(TblTypeDeviceEntity.class, id);
    }

    public List<TblTypeDeviceEntity> readList(){
        TypedQuery<TblTypeDeviceEntity> query = entityManager.createQuery(
                "from TblTypeDeviceEntity entity", TblTypeDeviceEntity.class);
        List<TblTypeDeviceEntity> tblTypeDeviceEntities=query.getResultList();
        return tblTypeDeviceEntities;
    }
}
