package pst.beans.device;

import org.joda.time.LocalDateTime;
import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

}
