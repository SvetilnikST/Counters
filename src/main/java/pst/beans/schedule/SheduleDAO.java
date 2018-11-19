package pst.beans.schedule;

import pst.beans.AbstractDao;
import pst.beans.device.TblDeviceEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@LocalBean
@Stateless
public class SheduleDAO extends AbstractDao<TblScheduleEntity> {
    public SheduleDAO(){
        super(TblScheduleEntity.class);
    }

    //Обрабатываем список устройств по одиночке
    public void makeInserts(List<TblScheduleEntity> tblScheduleList) {
        EntityManager entityManager = getEntityManager();
//        entityManager.getTransaction().begin();
        for (TblScheduleEntity tblSchedule : tblScheduleList) {
            entityManager.persist(tblSchedule);
        }
//        entityManager.getTransaction().commit();
    }

}
