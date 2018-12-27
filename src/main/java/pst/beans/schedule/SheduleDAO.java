package pst.beans.schedule;

import org.joda.time.LocalDateTime;
import org.primefaces.model.SortOrder;
import pst.beans.AbstractDao;
import pst.beans.device.TblDeviceEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class SheduleDAO extends AbstractDao<TblScheduleEntity> {
    public SheduleDAO() {
        super(TblScheduleEntity.class);
    }

    //Обрабатываем список устройств по одиночке
    public void makeInserts(List<TblScheduleEntity> tblScheduleList) {
        EntityManager entityManager = getEntityManager();
        for (TblScheduleEntity tblSchedule : tblScheduleList) {
            entityManager.persist(tblSchedule);
        }
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


    //TODO В будущем требует переделки - требуется указывать диапазон времени, за который нас интересуют данные
    public List<TblScheduleEntity> findDeviceWasReaded(int id) {

        EntityManager entityManager = getEntityManager();
        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
                "select entity from TblScheduleEntity entity where entity.statusexecute=1 and entity.deviceId = :id " +
                        "order by entity.id desc ",
                TblScheduleEntity.class)
                .setParameter("id", id)
                .setMaxResults(60);
        List<TblScheduleEntity> resultList = query.getResultList();

        return resultList;
    }

    public int getTotalCount(Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        Integer dateFirstFilter = ((Long) filters.get("dateFrom")).intValue();
        Integer dateLastFilter = ((Long) filters.get("dateLast")).intValue();
        Integer statusExecute = (Integer) filters.get("statusExecute");
        Boolean inStore1 = (Boolean) filters.get("inStore1");
        Integer deviceId = (Integer) filters.get("deviceId");

        int rez;

        if (inStore1 == null) {
            rez = ((Long) entityManager.createQuery("select count(shedul.id) from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where shedul.statusexecute =:statusExecute and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast")
                    .setParameter("statusExecute", statusExecute)
                    .setParameter("dateFirst", dateFirstFilter)
                    .setParameter("dateLast", dateLastFilter)
                    .setParameter("deviceId", deviceId)
                    .getSingleResult()).intValue();
        } else {
            rez = ((Long) entityManager.createQuery("select count(shedul.id) from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where shedul.statusexecute =:statusExecute and box.instore1=:inStore1 and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast")
                    .setParameter("statusExecute", statusExecute)
                    .setParameter("dateFirst", dateFirstFilter)
                    .setParameter("dateLast", dateLastFilter)
                    .setParameter("inStore1", inStore1)
                    .setParameter("deviceId", deviceId)
                    .getSingleResult()).intValue();
        }
        return rez;

    }


    public List<TblScheduleEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        Integer dateFirstFilter = ((Long) filters.get("dateFrom")).intValue();
        Integer dateLastFilter = ((Long) filters.get("dateLast")).intValue();
        Integer statusExecute = (Integer) filters.get("statusExecute");
        Boolean inStore1 = (Boolean) filters.get("inStore1");
        Integer deviceId = (Integer) filters.get("deviceId");

        List<TblScheduleEntity> rez;

        if (inStore1 == null) {
            rez = (entityManager.createQuery("select shedul from TblScheduleEntity shedul left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where shedul.statusexecute =:statusExecute and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast")
                    .setParameter("dateFirst", dateFirstFilter)
                    .setParameter("dateLast", dateLastFilter)
                    .setParameter("deviceId", deviceId)
                    .setParameter("statusExecute", statusExecute)
                    .setFirstResult(first)
                    .setMaxResults(pageSize)
                    .getResultList());
        } else {
            rez = (entityManager.createQuery("select shedul from TblScheduleEntity shedul left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where shedul.statusexecute =:statusExecute and box.instore1=:inStore1 and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast")
                    .setParameter("dateFirst", dateFirstFilter)
                    .setParameter("dateLast", dateLastFilter)
                    .setParameter("deviceId", deviceId)
                    .setParameter("statusExecute", statusExecute)
                    .setParameter("inStore1", inStore1)
                    .setFirstResult(first)
                    .setMaxResults(pageSize)
                    .getResultList());
        }

        return rez;

    }


    public TblScheduleEntity loadrecord(int dayBeginInt, int dayEndInt, int id) {
        EntityManager entityManager = getEntityManager();

        List<TblScheduleEntity> rez;

        rez = (entityManager.createQuery("select shedul from TblScheduleEntity shedul left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where box.instore1=true and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast order by box.timeRequest", TblScheduleEntity.class)
                .setParameter("dateFirst", dayBeginInt)
                .setParameter("dateLast", dayEndInt)
                .setParameter("deviceId", id)
                .setMaxResults(1)
                .getResultList());

        if (rez.size() == 0) {
            return null;
        } else {
            return rez.get(0);
        }
    }


    public TblScheduleEntity loadrecordAct(int dayBeginInt, int dayEndInt, int id) {
        EntityManager entityManager = getEntityManager();

        List<TblScheduleEntity> rez;

        rez = (entityManager.createQuery("select shedul from TblScheduleEntity shedul left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where box.instore1=true " +
                "and shedul.deviceId =:deviceId " +
                "and box.timeRequest between  :dateLast AND :dateFirst "+
                "order by box.timeRequest desc"
                ,TblScheduleEntity.class)
                .setParameter("deviceId", id)
                .setParameter("dateFirst", dayBeginInt)
                .setParameter("dateLast", dayEndInt)
                .getResultList());

        if (rez.size() == 0) {
            return null;
        } else {
            return rez.get(0);
        }
    }

}
