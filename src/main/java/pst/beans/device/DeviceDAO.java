package pst.beans.device;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import pst.beans.AbstractDao;
import pst.beans.schedule.TblScheduleEntity;
import pst.beans.typeDevice.TblTypeDeviceEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@LocalBean
@Stateless
public class DeviceDAO extends AbstractDao<TblDeviceEntity> {
    public DeviceDAO(){
        super(TblDeviceEntity.class);
    }

    //просто получаем список всех Устройств, которые нужно опросить в следующем часу
    public List<TblDeviceEntity> listAllInTime() {
        EntityManager entityManager = getEntityManager();

        LocalDateTime dtPlusOneHour = new LocalDateTime()
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        dtPlusOneHour = dtPlusOneHour.plusHours(1);

        TypedQuery<TblDeviceEntity> query = entityManager.createQuery(
                "select entity from TblDeviceEntity entity where :taskHour % entity.requestInterval = 0 ",
                TblDeviceEntity.class)
                .setParameter("taskHour", dtPlusOneHour.getHourOfDay());
        List<TblDeviceEntity> resultList = query.getResultList();

        return resultList;
    }

//    public void test() {
//        EntityManager entityManager = getEntityManager();
//        TypedQuery<TblScheduleEntity> query = entityManager.createQuery(
//                "select shedul from TblScheduleEntity shedul  left join TblBoxCommonEntity box ON box.tblScheduleEntity=shedul where  box.instore1=true and shedul.deviceId :=deviceId",
//                TblScheduleEntity.class)
//                .setParameter("deviceId", 1); // вместо 1 надо будет подставлять реальный номер устройства
//        List<TblScheduleEntity> resultList = query.getResultList();
//        int a=0;
//
//    }



    public static int daysOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 14, 12, 0, 0, 000);
        return dateTime.dayOfMonth().getMaximumValue();
    }

    public void test2(){
//        Date dt = new Date();
//        DateTime dtOrg = new DateTime(dt);
//
//        for (int i=0;i<30 ; i++ ) {
//            //добавляет один день
//            DateTime dtPlusOne = dtOrg.plusDays(1);
//        }
//       int a=0;


    }

}
