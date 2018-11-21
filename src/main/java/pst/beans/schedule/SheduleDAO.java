package pst.beans.schedule;

import org.joda.time.LocalDateTime;
import pst.beans.AbstractDao;
import pst.beans.device.TblDeviceEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    //просто получаем список всех Устройств, которые нужно опросить в следующем часу
    public List<TblScheduleEntity> listRecordsToExecute() {
        EntityManager entityManager = getEntityManager();

        LocalDateTime dtPlusOneHour = new LocalDateTime();

        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
                "select entity from TblScheduleEntity entity where entity.statusexecute=0 and entity.timetoexecute < current_timestamp ",
                TblScheduleEntity.class);
        List<TblScheduleEntity> resultList = query.getResultList();

        return resultList;
    }


}
