package pst.beans.deviceDataAdministration;

import org.joda.time.LocalDateTime;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;
import pst.beans.qBox.Common.BoxCommonDAO;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean(name = "deviceAdminHistory")
@ViewScoped
public class deviceDataAdminHistory extends LazyDataModel<SheduleReport> {

    List<TblBoxSystemEntity> boxSystemEntityList;
    TblBoxCommonEntity boxCommonEntity;
    List<TblBoxCommonEntity> boxCommonEntityList;
    List<TblScheduleEntity> tblScheduleEntityList;
    TblDeviceEntity device;
    String dateRangeString;
    private Date startDate;
    private Date endDate;
    private DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    List<SheduleReport> sheduleReports;

    @EJB
    private DeviceDAO deviceDAO;

    @EJB
    private SheduleDAO sheduleDAO;

    @EJB
    private BoxCommonDAO boxCommonDAO;


    @Default
    private int id;

    @PostConstruct
    public void start() {


        Calendar c1 = Calendar.getInstance();
        endDate = c1.getTime();
        Calendar c2 =Calendar.getInstance();

//        c2.set(Calendar.DAY_OF_MONTH,-7);
        c2.add(Calendar.DAY_OF_YEAR,-7);
        startDate = c2.getTime();

        sheduleReports = new ArrayList<>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if (param != null) {

            id = Integer.parseInt(param);
            //чтение записи по id
//            this.tblDeviceEntity = deviceDAO.read(id);
            device = deviceDAO.read(id);

            //TODO закомментировать
//            deviceDAO.test();
            deviceDAO.test2();

            //проверка если пустая entity, сообщение об ощибке
            if (device == null) {
                String message = "Bad request. Unknown type device.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            //вызываем метод загрузки данный в сущность
//            load(device);
        } else {

        }
        int a=0;
//
//        //TODO исправить на получение реального номера устройства
//        TblDeviceEntity device = deviceDAO.read(1);
    }

//    private void load(TblDeviceEntity device) {
//        tblScheduleEntityList = sheduleDAO.findDeviceWasReaded(device.getId());
//        for (TblScheduleEntity oneEntyty : tblScheduleEntityList) {
//            boxCommonEntityList = oneEntyty.getCommons();
//
//            //TODO треубется проверка на 0
//            boxCommonEntity = boxCommonEntityList.get(0);
//            boxSystemEntityList = boxCommonEntity.getSystem();
//        }
//
//        SheduleReport sheduleReport;
//
//        LocalDateTime dtCur;
//        boolean firstTime = true;
//        LocalDateTime dtOld = new LocalDateTime(tblScheduleEntityList.get(0).getCommons().get(0).getTimeRequest() * 1000L);
//        String tmp;
//
//        for (TblScheduleEntity shedulerRecord : tblScheduleEntityList) {
//            sheduleReport = new SheduleReport();
//
//            dtCur = new LocalDateTime(shedulerRecord.getCommons().get(0).getTimeRequest() * 1000L);
//
//            if (dtCur.getDayOfMonth() == dtOld.getDayOfMonth() && !firstTime) {
//                sheduleReport.setDateRequest("   ");
//            } else {
//                sheduleReport.setDateRequest(dtCur.toString("dd.MMMM"));
//            }
//            firstTime = false;
//            dtOld = dtCur;
//
//            //сюда гоним запись всего остального.
//            sheduleReport.setTimeRequest(dtCur.toString("HH:mm:ss"));
////            sheduleReport.setTimeRequest(String.valueOf(dtCur.getHourOfDay()));
////            sheduleReport.setTimeRequest(shedulerRecord.getCommons().get(0).getTimeRequest());
//            sheduleReport.setTimeDevice(shedulerRecord.getCommons().get(0).getTimeDevice());
////            sheduleReport.setTimeRequest(shedulerRecord.get);
//            sheduleReport.setSIGMA_Q(shedulerRecord.getCommons().get(0).getSystem().get(0).getSIGMA_Q());
//            sheduleReport.setQ1(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ1());
//            sheduleReport.setQ2(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ2());
//            sheduleReport.setQ3(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ3());
//            sheduleReport.setV1(shedulerRecord.getCommons().get(0).getSystem().get(0).getV1());
//            sheduleReport.setV2(shedulerRecord.getCommons().get(0).getSystem().get(0).getV2());
//            sheduleReport.setM1(shedulerRecord.getCommons().get(0).getSystem().get(0).getM1());
//            sheduleReport.setM2(shedulerRecord.getCommons().get(0).getSystem().get(0).getM2());
//            sheduleReport.setGM1(shedulerRecord.getCommons().get(0).getSystem().get(0).getGM1());
//            sheduleReport.setGM2(shedulerRecord.getCommons().get(0).getSystem().get(0).getGM2());
//            sheduleReport.setGV1(shedulerRecord.getCommons().get(0).getSystem().get(0).getGV1());
//            sheduleReport.setGV2(shedulerRecord.getCommons().get(0).getSystem().get(0).getGV2());
//            sheduleReport.setT1(shedulerRecord.getCommons().get(0).getSystem().get(0).getT1());
//            sheduleReport.setT2(shedulerRecord.getCommons().get(0).getSystem().get(0).getT2());
//            sheduleReport.setT3(shedulerRecord.getCommons().get(0).getSystem().get(0).getT3());
//            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP1());
//            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP2());
//            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP3());
//            sheduleReport.setInStore1(shedulerRecord.getCommons().get(0).getInstore1());
//            sheduleReport.setIdShedule(shedulerRecord.getId());
//
//            int a = 0;
//            sheduleReports.add(sheduleReport);
//        }
//    }


    public void addRecordToRegister(int idShedule){
        TblScheduleEntity scheduleEntity= sheduleDAO.read(idShedule);
        TblBoxCommonEntity tblBoxCommonEntity = scheduleEntity.getCommons().get(0);
        tblBoxCommonEntity.setInstore1(true);
        boxCommonDAO.update(tblBoxCommonEntity);
        for (SheduleReport oneRecord :sheduleReports) {
            if(oneRecord.getIdShedule()==idShedule){
                oneRecord.setInStore1(true);
                break;
            }

        }

//        sheduleReports.setInStore1(true);
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

    public TblDeviceEntity getDevice() {
        return device;
    }

    public void setDevice(TblDeviceEntity device) {
        this.device = device;
    }

    public List<SheduleReport> getSheduleReports() {
        return sheduleReports;
    }

    public void setSheduleReports(List<SheduleReport> sheduleReports) {
        this.sheduleReports = sheduleReports;
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

    public Timestamp timstampFromInt(int valueToTimestamp) {
        Timestamp rezult  = new Timestamp(valueToTimestamp * 1000L);
        return rezult;
    }

    public String getDateRangeString() {
        return String.format("С: %s по: %s%n",
                formatter.format(startDate), formatter.format(endDate));
    }

    public void setDateRangeString(String dateRangeString) {
        this.dateRangeString = dateRangeString;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        if(endDate.before(startDate)){
            endDate = startDate;
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public List<SheduleReport> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        int a=0;

        filters.put("dateFrom",startDate.getTime()/1000);
        filters.put("dateLast",endDate.getTime()/1000);
        filters.put("statusExecute",1);

        filters.put("deviceId",device.getId());//исправить на правильный номер устройства

//        tblScheduleEntityList = sheduleDAO.findDeviceWasReaded(1);


        setRowCount(sheduleDAO.getTotalCount(filters));
//        TblScheduleEntity scheduleEntity;
//        scheduleEntity.getCommons().get(0).getTimeRequest();

        tblScheduleEntityList =sheduleDAO.load(first, pageSize, sortField, sortOrder, filters);
/////

        sheduleReports = new ArrayList<>();
        for (TblScheduleEntity oneEntyty : tblScheduleEntityList) {
            boxCommonEntityList = oneEntyty.getCommons();

            //TODO треубется проверка на 0
            boxCommonEntity = boxCommonEntityList.get(0);
            boxSystemEntityList = boxCommonEntity.getSystem();
        }

        SheduleReport sheduleReport;

        LocalDateTime dtCur;
        boolean firstTime = true;
        LocalDateTime dtOld = new LocalDateTime(tblScheduleEntityList.get(0).getCommons().get(0).getTimeRequest() * 1000L);
        String tmp;

        for (TblScheduleEntity shedulerRecord : tblScheduleEntityList) {
            sheduleReport = new SheduleReport();

            dtCur = new LocalDateTime(shedulerRecord.getCommons().get(0).getTimeRequest() * 1000L);

            if (dtCur.getDayOfMonth() == dtOld.getDayOfMonth() && !firstTime) {
                sheduleReport.setDateRequest("   ");
            } else {
                sheduleReport.setDateRequest(dtCur.toString("dd.MMMM"));
            }
            firstTime = false;
            dtOld = dtCur;

            //сюда гоним запись всего остального.
            sheduleReport.setTimeRequest(dtCur.toString("HH:mm:ss"));
//            sheduleReport.setTimeRequest(String.valueOf(dtCur.getHourOfDay()));
//            sheduleReport.setTimeRequest(shedulerRecord.getCommons().get(0).getTimeRequest());
            sheduleReport.setTimeDevice(shedulerRecord.getCommons().get(0).getTimeDevice());
//            sheduleReport.setTimeRequest(shedulerRecord.get);
            sheduleReport.setSIGMA_Q(shedulerRecord.getCommons().get(0).getSystem().get(0).getSIGMA_Q());
            sheduleReport.setQ1(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ1());
            sheduleReport.setQ2(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ2());
            sheduleReport.setQ3(shedulerRecord.getCommons().get(0).getSystem().get(0).getQ3());
            sheduleReport.setV1(shedulerRecord.getCommons().get(0).getSystem().get(0).getV1());
            sheduleReport.setV2(shedulerRecord.getCommons().get(0).getSystem().get(0).getV2());
            sheduleReport.setM1(shedulerRecord.getCommons().get(0).getSystem().get(0).getM1());
            sheduleReport.setM2(shedulerRecord.getCommons().get(0).getSystem().get(0).getM2());
            sheduleReport.setGM1(shedulerRecord.getCommons().get(0).getSystem().get(0).getGM1());
            sheduleReport.setGM2(shedulerRecord.getCommons().get(0).getSystem().get(0).getGM2());
            sheduleReport.setGV1(shedulerRecord.getCommons().get(0).getSystem().get(0).getGV1());
            sheduleReport.setGV2(shedulerRecord.getCommons().get(0).getSystem().get(0).getGV2());
            sheduleReport.setT1(shedulerRecord.getCommons().get(0).getSystem().get(0).getT1());
            sheduleReport.setT2(shedulerRecord.getCommons().get(0).getSystem().get(0).getT2());
            sheduleReport.setT3(shedulerRecord.getCommons().get(0).getSystem().get(0).getT3());
            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP1());
            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP2());
            sheduleReport.setP1(shedulerRecord.getCommons().get(0).getSystem().get(0).getP3());
            sheduleReport.setInStore1(shedulerRecord.getCommons().get(0).getInstore1());
            sheduleReport.setIdShedule(shedulerRecord.getId());


            sheduleReports.add(sheduleReport);
        }



//////
        return sheduleReports;
    }


}
