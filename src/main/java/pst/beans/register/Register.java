package pst.beans.register;

import org.joda.time.LocalDateTime;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;
import pst.beans.qBox.Common.BoxCommonDAO;
import pst.beans.schedule.SheduleDAO;
import pst.beans.schedule.TblScheduleEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@ManagedBean(name = "register")
@ViewScoped
public class Register extends LazyDataModel<RegisterReport> {

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
    private Timestamp selectMonth;
    private LocalDateTime selectMonth1;
    private TblDeviceEntity device;

    private List<RegisterReport> registerReports;
    private List<Timestamp> timestampsReportPeriodList;
    private List<LocalDateTime> timestampsReportPeriodList1;

//    private LocalDateTime selectedMonthYear;

    private String button1Style = "background-color:#449d44; color: #fff; border-color: #398439; text-shadow: none;";
    private String button2Style = "background-color:#c9302c; color: #fff; border-color: #ac2925; text-shadow: none;";
    private String button3Style = "background-color:#31b0d5; color: #fff; border-color: #269abc; text-shadow: none;";
    private String styleYellow = "background-color:#f0ad4e; color: #fff; border-color: #269abc; text-shadow: none;";

//    private String option;
//    private List<String> options;


    private List<LocalDateTime> dateTimeList;
    private LocalDateTime selectedDateTime;
    String [] stMonth = {"","январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};




    @PostConstruct
    public void start() {


//        options = new ArrayList<String>();
//        for(int i = 0; i < 20; i++) {
//            options.add("Option " + i);
//        }

//        LocalDateTime dtCur;
//        LocalDateTime dtCurTemp;
//        timestampsReportPeriodList =new ArrayList<>();
//        timestampsReportPeriodList1 =new ArrayList<>();
//
//        dtCur = new LocalDateTime()
//                .withDayOfMonth(1)
//                .withTime(0,0,0,0);
//        dtCurTemp = new LocalDateTime(dtCur)
//                .minusMonths(13);
//
//        Date tmpDate;
//        for(int i=0;i<13;i++){
//            dtCurTemp=dtCur.minusMonths(13-i);
//            timestampsReportPeriodList.add(new Timestamp(dtCurTemp.toDateTime().getMillis()));
//            timestampsReportPeriodList1.add(dtCurTemp);
//        }
//
//        this.selectMonth = new Timestamp(dtCur.toDateTime().getMillis());

        //this.dateFrom = new Timestamp(timestampsReportPeriodList.get(0).toDateTime().getMillis());         //java.sql.Timestamp.valueOf("2007-09-23 00:00:00.0");
//        this.dateFrom = java.sql.Timestamp.valueOf("2007-09-23 00:00:00.0");
//        this.dateLast = java.sql.Timestamp.valueOf("2018-12-23 10:10:10.0");
//        this.selectMonth = java.sql.Timestamp.valueOf("2018-11-13 10:10:10.0");


        LocalDateTime dtCur;
        dateTimeList = new ArrayList<>();

        dtCur = new LocalDateTime()
                .withDayOfMonth(1)
                .withTime(0,0,0,0);

        for (int i=0;i<14;i++){
            dateTimeList.add(dtCur.minusMonths(13-i));
        }





        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if (param != null) {

            int id = Integer.parseInt(param);
            //чтение записи по id
//            this.tblDeviceEntity = deviceDAO.read(id);
            device = deviceDAO.read(id);


            //проверка если пустая entity, сообщение об ощибке
            if (device == null) {
                String message = "Bad request. Unknown type device.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            //вызываем метод загрузки данный в сущность
            load2();
        } else {

        }
        int a = 0;
    }

    public void load2() {
        registerReports = new ArrayList<>();
        RegisterReport oneRecord;

        int firstDay = 1;
//        int lastDay = 30;
        int lastDay = (new LocalDateTime(selectMonth)).dayOfMonth().getMaximumValue();
        LocalDateTime dayBegin;
        LocalDateTime dayEnd;
        int dayBeginInt;
        int dayEndInt;



        LocalDateTime dtCur = new LocalDateTime(selectMonth);

        LocalDateTime dtTemp;

        for (int i = firstDay; i <= lastDay; i++) {
            oneRecord = new RegisterReport();
            dayBegin = dtCur.withDayOfMonth(i)
                    .withTime(0, 0, 0, 0);
            dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
            dayEnd = dtCur.withDayOfMonth(i)
                    .withTime(23, 59, 59, 999);
            dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);
            TblScheduleEntity scheduleEntity = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());
            if (scheduleEntity == null) {
                oneRecord.setDate(dayBegin.toString("dd.MM HH:mm"));

                if (device.getObjectEntity().getHotWater() == null) {
                    oneRecord.setHotWater(false);
                } else {
                    oneRecord.setHotWater(true);
                }

                if (device.getObjectEntity().getHeating() == null) {
                    oneRecord.setHeating(false);
                } else {
                    oneRecord.setHeating(true);
                }

                if (device.getObjectEntity().getVentilation() == null) {
                    oneRecord.setVentilation(false);
                } else {
                    oneRecord.setVentilation(true);
                }

                oneRecord.setToShow(false);
                registerReports.add(oneRecord);
                continue;
            }

            oneRecord.setToShow(true);

            dtTemp = new LocalDateTime(scheduleEntity.getCommons().get(0).getTimeRequest() * 1000L);
            oneRecord.setDate(dtTemp.toString("dd.MM HH:mm"));
            if (device.getObjectEntity().getHotWater() == null) {
                oneRecord.setHotWater(false);
            } else {
                oneRecord.setHotWater(true);
            }

            if (device.getObjectEntity().getHeating() == null) {
                oneRecord.setHeating(false);
            } else {
                oneRecord.setHeating(true);
            }

            if (device.getObjectEntity().getVentilation() == null) {
                oneRecord.setVentilation(false);
            } else {
                oneRecord.setVentilation(true);
            }

            oneRecord.setQ1(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
            oneRecord.setV1(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
            oneRecord.setGM1(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM1());
            oneRecord.setT1(scheduleEntity.getCommons().get(0).getSystem().get(0).getT1());
            oneRecord.setP1(scheduleEntity.getCommons().get(0).getSystem().get(0).getP1());
            oneRecord.setQ2(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ2());
            oneRecord.setV2(scheduleEntity.getCommons().get(0).getSystem().get(0).getV2());
            oneRecord.setGM2(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM2());
            oneRecord.setT2(scheduleEntity.getCommons().get(0).getSystem().get(0).getT2());
            oneRecord.setP2(scheduleEntity.getCommons().get(0).getSystem().get(0).getP2());
            oneRecord.setTimeOn(scheduleEntity.getCommons().get(0).getTimeOn());

            registerReports.add(oneRecord);
        }


        //следующий месяц
        oneRecord = new RegisterReport();
        dayBegin = dtCur.withDayOfMonth(1)
                .plusMonths(1)
                .withTime(0, 0, 0, 0);
        dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
        dayEnd = dtCur.plusYears(1);
        dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);

        TblScheduleEntity scheduleEntity = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());
        if (scheduleEntity == null) {
            oneRecord.setDate(dayBegin.toString("dd.MM HH:mm"));

            if (device.getObjectEntity().getHotWater() == null) {
                oneRecord.setHotWater(false);
            } else {
                oneRecord.setHotWater(true);
            }

            if (device.getObjectEntity().getHeating() == null) {
                oneRecord.setHeating(false);
            } else {
                oneRecord.setHeating(true);
            }

            if (device.getObjectEntity().getVentilation() == null) {
                oneRecord.setVentilation(false);
            } else {
                oneRecord.setVentilation(true);
            }

            oneRecord.setToShow(false);
            registerReports.add(oneRecord);
        } else {
            oneRecord.setToShow(true);

            dtTemp = new LocalDateTime(scheduleEntity.getCommons().get(0).getTimeRequest() * 1000L);
            oneRecord.setDate(dtTemp.toString("dd.MM HH:mm"));
            if (device.getObjectEntity().getHotWater() == null) {
                oneRecord.setHotWater(false);
            } else {
                oneRecord.setHotWater(true);
            }

            if (device.getObjectEntity().getHeating() == null) {
                oneRecord.setHeating(false);
            } else {
                oneRecord.setHeating(true);
            }

            if (device.getObjectEntity().getVentilation() == null) {
                oneRecord.setVentilation(false);
            } else {
                oneRecord.setVentilation(true);
            }

            oneRecord.setQ1(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
            oneRecord.setV1(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
            oneRecord.setGM1(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM1());
            oneRecord.setT1(scheduleEntity.getCommons().get(0).getSystem().get(0).getT1());
            oneRecord.setP1(scheduleEntity.getCommons().get(0).getSystem().get(0).getP1());
            oneRecord.setQ2(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ2());
            oneRecord.setV2(scheduleEntity.getCommons().get(0).getSystem().get(0).getV2());
            oneRecord.setGM2(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM2());
            oneRecord.setT2(scheduleEntity.getCommons().get(0).getSystem().get(0).getT2());
            oneRecord.setP2(scheduleEntity.getCommons().get(0).getSystem().get(0).getP2());
            oneRecord.setTimeOn(scheduleEntity.getCommons().get(0).getTimeOn());
            registerReports.add(oneRecord);
        }
        fillDeltaData(registerReports);
    }

    private void fillDeltaData(List<RegisterReport> registerReports) {

        RegisterReport previous = registerReports.get(0);
        for (int i=0;i<registerReports.size()-1;i++){
            if(registerReports.get(i).isToShow()) {
                for (int j = i + 1; j < registerReports.size(); j++) {
                    if (registerReports.get(j).isToShow()){
                        registerReports.get(i).setQ1delta(registerReports.get(j).getQ1()-registerReports.get(i).getQ1());
                        registerReports.get(i).setQ2delta(registerReports.get(j).getQ2()-registerReports.get(i).getQ2());
                        registerReports.get(i).setTimeOndelta(registerReports.get(j).getTimeOn()-registerReports.get(i).getTimeOn());
                        registerReports.get(i).setVdelta(registerReports.get(j).getV1()-registerReports.get(j).getV2()-registerReports.get(i).getV1()-registerReports.get(i).getV2());
                        i=j-1;
                        break;
                    }
                }
            }
        }

    }

    @Override
    public List<RegisterReport> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<RegisterReport> rez = new ArrayList<>();
        RegisterReport oneRecord;

        int firstDay = 1;
        int lastDay = 30;
        LocalDateTime dayBegin;
        LocalDateTime dayEnd;
        int dayBeginInt;
        int dayEndInt;

        LocalDateTime dtCur = new LocalDateTime(selectMonth);

        LocalDateTime dtTemp;

        for (int i = firstDay; i <= lastDay; i++) {
            oneRecord = new RegisterReport();
            dayBegin = dtCur.withDayOfMonth(i)
                    .withTime(0, 0, 0, 0);
            dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
            dayEnd = dtCur.withDayOfMonth(i)
                    .withTime(23, 59, 59, 999);
            dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);
            TblScheduleEntity scheduleEntity = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());
            if (scheduleEntity == null) {
                oneRecord.setDate(dayBegin.toString("dd.MM HH:mm"));
                rez.add(oneRecord);
                continue;
            }

            dtTemp = new LocalDateTime(scheduleEntity.getCommons().get(0).getTimeRequest() * 1000L);
            oneRecord.setDate(dtTemp.toString("dd.MM HH:mm"));
            if (device.getObjectEntity().getHotWater() == null) {
                oneRecord.setHotWater(false);
            } else {
                oneRecord.setHotWater(true);
            }

            if (device.getObjectEntity().getHeating() == null) {
                oneRecord.setHeating(false);
            } else {
                oneRecord.setHeating(true);
            }

            if (device.getObjectEntity().getVentilation() == null) {
                oneRecord.setVentilation(false);
            } else {
                oneRecord.setVentilation(true);
            }

            oneRecord.setQ1(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
            oneRecord.setV1(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
            oneRecord.setGM1(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM1());
            oneRecord.setT1(scheduleEntity.getCommons().get(0).getSystem().get(0).getT1());
            oneRecord.setP1(scheduleEntity.getCommons().get(0).getSystem().get(0).getP1());
            oneRecord.setQ2(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ2());
            oneRecord.setV2(scheduleEntity.getCommons().get(0).getSystem().get(0).getV2());
            oneRecord.setGM2(scheduleEntity.getCommons().get(0).getSystem().get(0).getGM2());
            oneRecord.setT2(scheduleEntity.getCommons().get(0).getSystem().get(0).getT2());
            oneRecord.setP2(scheduleEntity.getCommons().get(0).getSystem().get(0).getP2());
            oneRecord.setTimeOn(scheduleEntity.getCommons().get(0).getTimeOn());

            rez.add(oneRecord);
        }
        return rez;
    }



    public List<TblScheduleEntity> load1(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        filters.put("dateFrom", dateFrom.getTime() / 1000);
        filters.put("dateLast", dateLast.getTime() / 1000);
        filters.put("statusExecute", 1);
        filters.put("inStore1", true);

        filters.put("deviceId", 1);//исправить на правильный номер устройства

        setRowCount(sheduleDAO.getTotalCount(filters));
//        TblScheduleEntity scheduleEntity;
//        scheduleEntity.getCommons().get(0).getTimeRequest();

        return sheduleDAO.load(first, pageSize, sortField, sortOrder, filters);
    }

    public List<RegisterReport> getRegisterReports() {
        return registerReports;
    }

    public void setRegisterReports(List<RegisterReport> registerReports) {
        this.registerReports = registerReports;
    }

    public List<Timestamp> getTimestampsReportPeriodList() {
        return timestampsReportPeriodList;
    }

    public void setTimestampsReportPeriodList(List<Timestamp> timestampsReportPeriodList) {
        this.timestampsReportPeriodList = timestampsReportPeriodList;
    }

    public Timestamp getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(Timestamp selectMonth) {
        this.selectMonth = selectMonth;
    }

//    public String getOption() {
//        return option;
//    }
//
//    public void setOption(String option) {
//        this.option = option;
//    }
//
//    public List<String> getOptions() {
//        return options;
//    }
//
//    public void setOptions(List<String> options) {
//        this.options = options;
//    }

    public List<LocalDateTime> getTimestampsReportPeriodList1() {
        return timestampsReportPeriodList1;
    }

    public void setTimestampsReportPeriodList1(List<LocalDateTime> timestampsReportPeriodList1) {
        this.timestampsReportPeriodList1 = timestampsReportPeriodList1;
    }

    public LocalDateTime getSelectMonth1() {
        return selectMonth1;
    }

    public void setSelectMonth1(LocalDateTime selectMonth1) {
        this.selectMonth1 = selectMonth1;
    }

    public String MyConvert(Timestamp date){
        return new SimpleDateFormat("MMMM yyyy").format(date);
    }









    public String Convert(String str) {
        String tmp = str.substring(0, 14) + String.valueOf(Integer.parseInt(str.substring(14)) + 20);
        return tmp;
    }
    public String Convert(LocalDateTime dt) {
        String tmp = stMonth[dt.getMonthOfYear()] + dt.toString(" YYYY");
        return tmp;
    }

    public List<LocalDateTime> getDateTimeList() {
        return dateTimeList;
    }

    public void setDateTimeList(List<LocalDateTime> dateTimeList) {
        this.dateTimeList = dateTimeList;
    }

    public LocalDateTime getSelectedDateTime() {
        return selectedDateTime;
    }

    public void setSelectedDateTime(LocalDateTime selectedDateTime) {
        this.selectedDateTime = selectedDateTime;
    }

    public void Stop(){
        int a=0;

    }





}


/*

                <p:selectOneMenu id="idMonthYear" value="#{register.selectedMonthYear}"
                                 converter="omnifaces.SelectItemsConverter"
                                 var="month"
                                 valueChangeListener="#{register.load2()}">
                    <f:selectItems value="#{register.timestampsReportPeriodList}" var="month"
                                   itemLabel="#{month}" itemValue="#{month}"/>
                    <p:ajax update="orderTable" event="change"/>
                </p:selectOneMenu>

                <p:outputLabel for="lazy" value="Lazy:" />
                <p:selectOneMenu id="lazy"  value="#{register.option}" >
                    <p:ajax listener="#{register.load2}" update="orderTable" />
                    <f:selectItems value="#{register.options}"  />
                </p:selectOneMenu>


                <p:outputLabel for="idMonthYear" value="Выбранный отчетный период:" />
                <p:selectOneMenu id="idMonthYear" value="#{register.selectedMonthYear}"
                                 converter="omnifaces.SelectItemsConverter"
                                 var="month" >
                    <f:selectItems value="#{register.timestampsReportPeriodList}" var="month"
                                   itemLabel="#{month}" itemValue="#{month}"/>
                    <p:ajax update="orderTable" listener="#{register.load2}"/>
                </p:selectOneMenu>




            <p:panel style="height: 100px">
                <p:outputLabel for="idMonthYear" value="Выбранный отчетный период:" />
                <p:selectOneMenu id="idMonthYear" value="#{register.selectMonth}"
                                 converter="omnifaces.SelectItemsConverter"
                                 var="month" dynamic="true"
                                 panelStyle="width:500px"
                                 style="width:500px"
                                 appendTo="@this"
                >
                    <p:ajax update="orderTable" listener="#{register.load2}"/>
                    <f:selectItem itemLabel=" - - - - " itemValue="" />
                    <f:selectItems value="#{register.timestampsReportPeriodList}"
                                   var="monthYear" itemLabel="#{omnifaces:formatDate(monthYear, 'MMMM yyyy')}"
                                   itemValue="#{monthYear}" />
                </p:selectOneMenu>
            </p:panel>
                <p:spacer/>

                    <f:selectItems value="#{register.timestampsReportPeriodList1}"
                                   var="monthYear" itemLabel="#{omnifaces:formatDate(monthYear, 'MMMM yyyy')}"
                                   itemValue="#{monthYear}" />


//////////////////////////////////////////
///рабочий вариант, проверено :)
///отсюда и до самого низа.....

        <h:outputLabel for="selectitems" value="Items with SelectItem list: " />
        <h:selectOneMenu id="selectitems" value="#{myDate.selectedDateTime}"
                         converter="omnifaces.SelectItemsConverter">
            <f:selectItems value="#{myDate.dateTimeList}"
                    var="dt"
                     itemValue="#{dt}"
                           itemLabel="#{myDate.Convert(dt)}"
                        />

            <f:ajax update="ddd" listener="#{myDate.Stop}"/>
        </h:selectOneMenu>

просто лень уже вставлять все на место. :)
@ManagedBean(name = "myDate")
@ViewScoped
public class MyDate implements Serializable {

    private List<LocalDateTime> dateTimeList;
    private LocalDateTime selectedDateTime;
    String [] stMonth = {"","??????","???????","????","??????","???","????","????","??????","????????","???????","??????","???????"};

    @PostConstruct
    public void init() {
        LocalDateTime dtCur;
        dateTimeList = new ArrayList<>();

        dtCur = new LocalDateTime()
                .withDayOfMonth(1)
                .withTime(0,0,0,0);

        for (int i=0;i<14;i++){
            dateTimeList.add(dtCur.minusMonths(13-i));
        }

    }

    public String Convert(String str) {
        String tmp = str.substring(0, 14) + String.valueOf(Integer.parseInt(str.substring(14)) + 20);
        return tmp;
    }
    public String Convert(LocalDateTime dt) {
        String tmp = stMonth[dt.getMonthOfYear()] + dt.toString(" YYYY");
        return tmp;
    }

    public List<LocalDateTime> getDateTimeList() {
        return dateTimeList;
    }

    public void setDateTimeList(List<LocalDateTime> dateTimeList) {
        this.dateTimeList = dateTimeList;
    }

    public LocalDateTime getSelectedDateTime() {
        return selectedDateTime;
    }

    public void setSelectedDateTime(LocalDateTime selectedDateTime) {
        this.selectedDateTime = selectedDateTime;
    }

    public void Stop(){
        int a=0;

    }

}






 */