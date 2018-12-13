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


    //TODO В будущем требует переделки - требуется указывать диапазон времени, за который нас интересуют данные
    public List<TblScheduleEntity> findDeviceWasReaded(int id) {

        EntityManager entityManager = getEntityManager();
        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
                "select entity from TblScheduleEntity entity where entity.statusexecute=1 and entity.deviceId = :id " +
                        "order by entity.id desc ",
                TblScheduleEntity.class)
                .setParameter("id",id)
                .setMaxResults(60);
        List<TblScheduleEntity> resultList = query.getResultList();

        return resultList;
    }

    public int getTotalCount(Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        Integer dateFirstFilter = ((Long) filters.get("dateFrom")).intValue();
        Integer dateLastFilter =  ((Long) filters.get("dateLast")).intValue();
//        String deviceIds = (String) filters.get("deviceId");
//        Integer deviceId = Integer.parseInt(deviceIds);
        Integer deviceId = (Integer) filters.get("deviceId");

        int rez = ((Long) entityManager.createQuery("select count(shedul.id) from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where  box.instore1=true and shedul.deviceId =:deviceId")
//                .setParameter("dateFirst",dateFirstFilter)
//                .setParameter("dateLast",dateLastFilter)
                .setParameter("deviceId",deviceId)
                .getSingleResult()).intValue();

//        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
//                "select shedul from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where  box.instore1=true and shedul.deviceId :=deviceId",
//                TblScheduleEntity.class)
//                .setParameter("deviceId", 1); // вместо 1 надо будет подставлять реальный номер устройства
//        List<TblScheduleEntity> resultList = query.getResultList();
//        int a=0;
   return rez;

    }


    public List<TblScheduleEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        Integer dateFirstFilter = ((Long) filters.get("dateFrom")).intValue();
        Integer dateLastFilter =  ((Long) filters.get("dateLast")).intValue();
//        String deviceIds = (String) filters.get("deviceId");
//        Integer deviceId = Integer.parseInt(deviceIds);

        Integer deviceId = (Integer) filters.get("deviceId");

        List<TblScheduleEntity> rez = ( entityManager.createQuery("select shedul from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where  box.instore1=true and shedul.deviceId =:deviceId and box.timeRequest between :dateFirst AND :dateLast")
                .setParameter("dateFirst",dateFirstFilter)
                .setParameter("dateLast",dateLastFilter)
                .setParameter("deviceId",deviceId)
                .getResultList());

//        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
//                "select shedul from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where  box.instore1=true and shedul.deviceId :=deviceId",
//                TblScheduleEntity.class)
//                .setParameter("deviceId", 1); // вместо 1 надо будет подставлять реальный номер устройства
//        List<TblScheduleEntity> resultList = query.getResultList();
//        int a=0;
        return rez;

    }


}
