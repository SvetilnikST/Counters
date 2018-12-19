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
import java.sql.Timestamp;
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


    private Boolean button1 = true;
    private Boolean button2 = true;
    private Boolean button3 = true;

    private Timestamp dateFrom;
    private Timestamp dateLast;
    private Timestamp selectMonth;
    private TblDeviceEntity device;

    private List<RegisterReportAct> registerReports;

    private String button1Style = "background-color:#449d44; color: #fff; border-color: #398439; text-shadow: none;";
    private String button2Style = "background-color:#c9302c; color: #fff; border-color: #ac2925; text-shadow: none;";
    private String button3Style = "background-color:#31b0d5; color: #fff; border-color: #269abc; text-shadow: none;";
    private String styleYellow = "background-color:#f0ad4e; color: #fff; border-color: #269abc; text-shadow: none;";


    @PostConstruct
    public void start() {
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
        RegisterReportAct oneRecord;
        int firstDay = 1;
        int lastDay = 30;
        LocalDateTime dayBegin;
        LocalDateTime dayEnd;
        int dayBeginInt;
        int dayEndInt;
        LocalDateTime dtCur = new LocalDateTime(selectMonth);
        LocalDateTime dtTemp;

        //данные на последний учетный день
        oneRecord = new RegisterReportAct();
        dayBegin = dtCur.withDayOfMonth(1)
                .plusMonths(0)
                .withTime(0, 0, 0, 0);
        dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
        dayEnd = dtCur.minusYears(1);
        dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);

        TblScheduleEntity scheduleEntityEnd = sheduleDAO.loadrecordAct(dayEndInt, dayBeginInt, device.getId());
        TblScheduleEntity scheduleEntityStart = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());

//        dayBegin = new LocalDateTime(scheduleEntity.getCommons().get(0).getTimeRequest() * 1000L);
//        oneRecord.setDate(dayBegin.toString("dd.MM HH:mm"));
//        oneRecord.setTimeOnEnd(scheduleEntity.getCommons().get(0).getTimeOn());
//        oneRecord.setQ1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
//        oneRecord.setV1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
//        oneRecord.setTimeOnStart(scheduleEntity.getCommons().get(0).getTimeOn());
//        oneRecord.setQ1Start(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
//        oneRecord.setV1Start(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
//        oneRecord.setK(1.0);

        int t1 = scheduleEntityEnd.getCommons().get(0).getTimeOn()-scheduleEntityStart.getCommons().get(0).getTimeOn();
        double Q = scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ1()-scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ1();

        oneRecord.setDate("Подающий трубопровод");
        oneRecord.setTimeOnEnd(scheduleEntityEnd.getCommons().get(0).getTimeOn());
        oneRecord.setQ1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getQ1());
        oneRecord.setV1End(scheduleEntityEnd.getCommons().get(0).getSystem().get(0).getV1());
        oneRecord.setTimeOnStart(scheduleEntityStart.getCommons().get(0).getTimeOn());
        oneRecord.setQ1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getQ1());
        oneRecord.setV1Start(scheduleEntityStart.getCommons().get(0).getSystem().get(0).getV1());
        oneRecord.setK(1.0);
        oneRecord.setdTimeOn(t1);
        oneRecord.setQ(Q);
        registerReports.add(oneRecord);

        //данные на начало
        oneRecord = new RegisterReportAct();
        dayBegin = dtCur.withDayOfMonth(1)
                .plusMonths(1)
                .withTime(0, 0, 0, 0);
        dayBeginInt = (int) (dayBegin.toDateTime().getMillis() / 1000L);
        dayEnd = dtCur.plusYears(1);
        dayEndInt = (int) (dayEnd.toDateTime().getMillis() / 1000L);

//        TblScheduleEntity scheduleEntityStart = sheduleDAO.loadrecord(dayBeginInt, dayEndInt, device.getId());
//        dayEnd = new LocalDateTime(scheduleEntityEnd.getCommons().get(0).getTimeRequest() * 1000L);
//        oneRecord.setDate(dayEnd.toString("dd.MM HH:mm"));
//        oneRecord.setTimeOnEnd(scheduleEntity.getCommons().get(0).getTimeOn());
//        oneRecord.setQ1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
//        oneRecord.setV1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());


//        oneRecord.setDate(dayEnd.toString("dd.MM HH:mm"));
//        oneRecord.setTimeOnEnd(scheduleEntity.getCommons().get(0).getTimeOn());
//        oneRecord.setQ1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
//        oneRecord.setV1End(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
//        oneRecord.setTimeOnStart(scheduleEntity.getCommons().get(0).getTimeOn());
//        oneRecord.setQ1Start(scheduleEntity.getCommons().get(0).getSystem().get(0).getQ1());
//        oneRecord.setV1Start(scheduleEntity.getCommons().get(0).getSystem().get(0).getV1());
//        oneRecord.setK(1.0);



        registerReports.add(oneRecord);
    }


    public List<RegisterReportAct> getRegisterReports() {
        return registerReports;
    }

    public void setRegisterReports(List<RegisterReportAct> registerReports) {
        this.registerReports = registerReports;
    }
}
