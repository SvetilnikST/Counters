package pst.beans.register;

import org.primefaces.model.LazyDataModel;
import pst.beans.deviceDataAdministration.SheduleReport;
import pst.beans.schedule.SheduleDAO;
import pst.beans.schedule.TblScheduleEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;


@ManagedBean(name = "register")
@Stateless
public class Register
//        extends LazyDataModel<>
{

    @EJB
    private SheduleDAO sheduleDAO;






    public void addRecord(int idShedule){
        TblScheduleEntity scheduleEntity= sheduleDAO.read(idShedule);
        int a = scheduleEntity.getCommons().get(0).getId();

    }

}
