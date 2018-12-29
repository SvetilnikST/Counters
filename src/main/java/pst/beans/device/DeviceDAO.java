package pst.beans.device;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.primefaces.model.SortOrder;
import pst.beans.AbstractDao;
import pst.beans.schedule.TblScheduleEntity;
import pst.beans.typeDevice.TblTypeDeviceEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public static int daysOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 14, 12, 0, 0, 000);
        return dateTime.dayOfMonth().getMaximumValue();
    }


    public int getTotalCount(Map<String, Object> filters) {
        return 1000;
    }

    public List<TblDeviceEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        String sql = "select * from " + getTableName() + " entity WHERE 1 ";
        String name = (String) filters.get("name");


        if (name != null) {
            sql += " and name = " + name + " ";
        }

        if (sortField != null) {
            sql += " order by " + sortField + " "
                    + (sortOrder.equals(SortOrder.ASCENDING) ?
                    "ASC" :
                    "DESC");
        } else {
        }

        List<TblDeviceEntity> rez = entityManager.createNativeQuery(sql, TblDeviceEntity.class)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
        return rez;

    }
    private String getTableName() {
        Table table = TblDeviceEntity.class.getAnnotation(Table.class);
        return table.name();
    }

}
