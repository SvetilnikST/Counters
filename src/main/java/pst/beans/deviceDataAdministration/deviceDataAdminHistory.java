package pst.beans.deviceDataAdministration;

import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;
import pst.beans.qBox.Common.TblBoxCommonEntity;
import pst.beans.qBox.System.TblBoxSystemEntity;
import pst.beans.schedule.SheduleDAO;
import pst.beans.schedule.TblScheduleEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean(name="deviceAdminHistory")
@ViewScoped
public class deviceDataAdminHistory implements Serializable{

    List<TblBoxSystemEntity> boxSystemEntityList;
    TblBoxCommonEntity boxCommonEntity;
    List<TblBoxCommonEntity> boxCommonEntityList;
    List<TblScheduleEntity> tblScheduleEntityList;

    @EJB
    private DeviceDAO deviceDAO;

    @EJB
    private SheduleDAO sheduleDAO;



    @Default
    private int id;

    @PostConstruct
    public void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if (param != null) {

            id = Integer.parseInt(param);
            //чтение записи по id
//            this.tblDeviceEntity = deviceDAO.read(id);
            TblDeviceEntity device = deviceDAO.read(id);

            //проверка если пустая entity, сообщение об ощибке
            if (device == null) {
                String message = "Bad request. Unknown type device.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            //вызываем метод загрузки данный в сущность
            load(device);
        } else {

        }
//
//        //TODO исправить на получение реального номера устройства
//        TblDeviceEntity device = deviceDAO.read(1);
    }

    private void load(TblDeviceEntity device) {
        tblScheduleEntityList = sheduleDAO.findDeviceWasReaded(device.getId());
        for (TblScheduleEntity  oneEntyty:tblScheduleEntityList ) {
            boxCommonEntityList=oneEntyty.getCommons();

            //TODO треубется проверка на 0
            boxCommonEntity=boxCommonEntityList.get(0);
            boxSystemEntityList=boxCommonEntity.getSystem();
        }
    }

    public List<TblBoxSystemEntity> getBoxSystemEntityList() {
        return boxSystemEntityList;
    }

    public void setBoxSystemEntityList(List<TblBoxSystemEntity> boxSystemEntityList) {
        this.boxSystemEntityList = boxSystemEntityList;
    }

    public TblBoxCommonEntity getBoxCommonEntity() {
        return boxCommonEntity;
    }

    public void setBoxCommonEntity(TblBoxCommonEntity boxCommonEntity) {
        this.boxCommonEntity = boxCommonEntity;
    }

    public List<TblBoxCommonEntity> getBoxCommonEntityList() {
        return boxCommonEntityList;
    }

    public void setBoxCommonEntityList(List<TblBoxCommonEntity> boxCommonEntityList) {
        this.boxCommonEntityList = boxCommonEntityList;
    }

    public List<TblScheduleEntity> getTblScheduleEntityList() {
        return tblScheduleEntityList;
    }

    public void setTblScheduleEntityList(List<TblScheduleEntity> tblScheduleEntityList) {
        this.tblScheduleEntityList = tblScheduleEntityList;
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public SheduleDAO getSheduleDAO() {
        return sheduleDAO;
    }

    public void setSheduleDAO(SheduleDAO sheduleDAO) {
        this.sheduleDAO = sheduleDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
