package pst.beans.register;

import org.joda.time.LocalDateTime;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;
import pst.beans.deviceDataAdministration.SheduleReport;
import pst.beans.qBox.Common.BoxCommonDAO;
import pst.beans.qBox.Common.TblBoxCommonEntity;
import pst.beans.qBox.System.BoxSystemDAO;
import pst.beans.qBox.System.TblBoxSystemEntity;
import pst.beans.schedule.SheduleDAO;
import pst.beans.schedule.TblScheduleEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "register")
@ViewScoped
public class Register  extends LazyDataModel<TblScheduleEntity> {

    @EJB
    private DeviceDAO deviceDAO;

    @EJB
    private SheduleDAO sheduleDAO;

    @EJB
    private BoxCommonDAO boxCommonDAO;


    private Boolean button1 = true;
    private Boolean button2 = true;
    private Boolean button3 = true;

    private Timestamp dateFrom;
    private Timestamp dateLast;

    private String button1Style = "background-color:#449d44; color: #fff; border-color: #398439; text-shadow: none;";
    private String button2Style = "background-color:#c9302c; color: #fff; border-color: #ac2925; text-shadow: none;";
    private String button3Style = "background-color:#31b0d5; color: #fff; border-color: #269abc; text-shadow: none;";
    private String styleYellow = "background-color:#f0ad4e; color: #fff; border-color: #269abc; text-shadow: none;";


    @PostConstruct
    public void start() {
        this.dateFrom = java.sql.Timestamp.valueOf("2007-09-23 00:00:00.0");
        this.dateLast = java.sql.Timestamp.valueOf("2018-12-23 10:10:10.0");

    }

    @Override
    public List<TblScheduleEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        filters.put("dateFrom",dateFrom.getTime()/1000);
        filters.put("dateLast",dateLast.getTime()/1000);
        filters.put("statusExecute",1);
        filters.put("inStore1",true);

        filters.put("deviceId",1);//исправить на правильный номер устройства

        setRowCount(sheduleDAO.getTotalCount(filters));
//        TblScheduleEntity scheduleEntity;
//        scheduleEntity.getCommons().get(0).getTimeRequest();

        return sheduleDAO.load(first, pageSize, sortField, sortOrder, filters);
    }


//    public void togleDuring(OrderEntity orderEntity){
//        if(!userBean.doRightVerify("changeStatus")){
//            return;
//        }
//        orderEntity.during = !orderEntity.during;
//        if(orderEntity.during){
//            orderEntity.setStatus((short)100);
//        }else {
//            orderEntity.setStatus((short)10);
//        }
//        orderDAOBean.update(orderEntity);
//    }

}
