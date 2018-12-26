package pst.beans.register;

import org.joda.time.LocalDateTime;
import org.primefaces.model.LazyDataModel;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "registerAct")
@ViewScoped
public class RegisterAct extends LazyDataModel<RegisterReportAct> {

    @EJB
    private DeviceDAO deviceDAO;

    @EJB
    private SheduleDAO sheduleDAO;

    @EJB
    private BoxCommonDAO boxCommonDAO;

    private Timestamp dateFrom;
    private Timestamp dateLast;
    private Timestamp selectMonth;
    private TblDeviceEntity device;

    private List<RegisterReportAct> registerReports;

    private List<Timestamp> dateTimeList;
    private String[] stMonth = {"", "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    @PostConstruct
    public void start() {
//        LocalDateTime dtCur;
//        dtCur = new LocalDateTime()
//                .withDayOfMonth(1)
//                .withTime(0, 0, 0, 0);
//        this.selectMonth = new Timestamp(dtCur.toDateTime().getMillis());
//        LocalDateTime dtCurTmp;
//        dateTimeList = new ArrayList<>();
//        Timestamp tsCur;
//
//        dtCur = new LocalDateTime()
//                .withDayOfMonth(1)
//                .withTime(0, 0, 0, 0);
//
//        for (int i = 0; i < 14; i++) {
//            dtCurTmp = dtCur.minusMonths(13 - i);
//            tsCur = new Timestamp(dtCurTmp.toDateTime().getMillis());
//            dateTimeList.add(tsCur);
//        }
//



        this.dateFrom = Timestamp.valueOf("2007-09-23 00:00:00.0");
        this.dateLast = Timestamp.valueOf("2018-12-23 10:10:10.0");
        this.selectMonth = Timestamp.valueOf("2018-11-13 10:10:10.0");
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

    private void load2() {
        registerReports = new ArrayList<>();
        RegisterReportAct oneRecord1;
        RegisterReportAct oneRecord2;
        int firstDay = 1;
        int lastDay = 30;
        LocalDateTime dayBegin;
        LocalDateTime dayEnd;
        int dayBeginInt;
        int dayEndInt;
        LocalDateTime dtCur = new LocalDateTime(selectMonth);
        LocalDateTime dtTemp;

        //данные на конец
        oneRecord1 = new RegisterReportAct();
        dayBegin = dtCur.withDayOfMonth(1)
                .plusMonths(0)
                .withTime(0, 0, 0, 0);
        dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
        dayEnd = dtCur.minusYears(1);
        dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);

        TblScheduleEntity scheduleEntityEnd = sheduleDAO.loadrecordAct(dayEndInt, dayBeginInt, device.getId());

        //данные на начало
        oneRecord2 = new RegisterReportAct();
        dayBegin = dtCur.withDayOfMonth(1)
                .plusMonths(1)
                .withTime(0, 0, 0, 0);
        dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
        dayEnd = dtCur.plusYears(1);
        dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);

        TblScheduleEntity scheduleEntityStart = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());
        double Q1 = scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ1()-scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ1();
        double V1 = scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getV1()-scheduleEntityStart.getCommons().get(0).getSystem().get(0).getV1();
        double Q2 = scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ2()-scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ2();
        double V2 = scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getV2()-scheduleEntityStart.getCommons().get(0).getSystem().get(0).getV2();



        oneRecord1.setDate("Подающий трубопровод");
        oneRecord1.setTimeOnEnd(scheduleEntityEnd.getCommons().get(0).getTimeOn());
        oneRecord1.setQ1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ1());
        oneRecord1.setV1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getV1());
        oneRecord1.setTimeOnStart(scheduleEntityStart.getCommons().get(0).getTimeOn());
        oneRecord1.setQ1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ1());
        oneRecord1.setV1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getV1());
        oneRecord1.setK(1.0);
        oneRecord1.setdTimeOn(scheduleEntityEnd.getCommons().get(0).getTimeOn()-scheduleEntityStart.getCommons().get(0).getTimeOn());
        oneRecord1.setQ(Q1);
        oneRecord1.setV(V1);
        registerReports.add(oneRecord1);

        oneRecord2.setDate("Обратный трубопровод");
        oneRecord2.setTimeOnEnd(scheduleEntityEnd.getCommons().get(0).getTimeOn());
        oneRecord2.setQ1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ2());
        oneRecord2.setV1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getV2());
        oneRecord2.setTimeOnStart(scheduleEntityStart.getCommons().get(0).getTimeOn());
        oneRecord2.setQ1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ2());
        oneRecord2.setV1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getV2());
        oneRecord2.setK(1.0);
        oneRecord2.setdTimeOn(scheduleEntityEnd.getCommons().get(0).getTimeOn()-scheduleEntityStart.getCommons().get(0).getTimeOn());
        oneRecord2.setQ(Q2);
        oneRecord2.setV(V2);
        registerReports.add(oneRecord2);

    }


    public List<RegisterReportAct> getRegisterReports() {
        return registerReports;
    }

    public void setRegisterReports(List<RegisterReportAct> registerReports) {
        this.registerReports = registerReports;
    }



    public void setSelectMonth(Timestamp selectMonth) {
        this.selectMonth = selectMonth;
    }

    public String MyConvert(Timestamp date) {
        return new SimpleDateFormat("MMMM yyyy").format(date);
    }

    public String Convert(String str) {
        String tmp = str.substring(0, 14) + String.valueOf(Integer.parseInt(str.substring(14)) + 20);
        return tmp;
    }

    public String Convert(Timestamp dt) {
        LocalDateTime lDt = new LocalDateTime(dt);

        String tmp = stMonth[lDt.getMonthOfYear()] + lDt.toString(" YYYY");
        return tmp;
    }

    public List<Timestamp> getDateTimeList() {
        return dateTimeList;
    }

    public void setDateTimeList(List<Timestamp> dateTimeList) {
        this.dateTimeList = dateTimeList;
    }

    public String Convert(LocalDateTime dt) {
        String tmp = stMonth[dt.getMonthOfYear()] + dt.toString(" YYYY");
        return tmp;
    }




}
