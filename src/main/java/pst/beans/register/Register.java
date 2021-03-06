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
    private Boolean button4 = true;
    private Boolean yellow = true;
    private Timestamp selectMonth;
    private TblDeviceEntity device;

    private List<RegisterReport> registerReports;

    private String button1Style = "background-color:#449d44; color: #fff; border-color: #398439; text-shadow: none;";
    private String button2Style = "background-color:#c9302c; color: #fff; border-color: #ac2925; text-shadow: none;";
    private String button3Style = "background-color:#31b0d5; color: #fff; border-color: #269abc; text-shadow: none;";
    private String button4Style = "background-color:#8B0000; color: #fff; border-color: #269abc; text-shadow: none;";
    private String styleYellow = "background-color:#4682B4; color: #fff; border-color: #269abc; text-shadow: none;";
    private List<Timestamp> dateTimeList;
    private String[] stMonth = {"", "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    @PostConstruct
    public void start() {
        LocalDateTime dtCur;
        dtCur = new LocalDateTime()
                .withDayOfMonth(1)
                .withTime(0, 0, 0, 0);
        this.selectMonth = new Timestamp(dtCur.toDateTime().getMillis());

        LocalDateTime dtCurTmp;
        dateTimeList = new ArrayList<>();
        Timestamp tsCur;

        dtCur = new LocalDateTime()
                .withDayOfMonth(1)
                .withTime(0, 0, 0, 0);

        for (int i = 0; i < 14; i++) {
            dtCurTmp = dtCur.minusMonths(13 - i);
            tsCur = new Timestamp(dtCurTmp.toDateTime().getMillis());
            dateTimeList.add(tsCur);
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if (param != null) {
            int id = Integer.parseInt(param);
            //чтение записи по id
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
    }

    public void load2() {
        registerReports = new ArrayList<>();
        RegisterReport oneRecord;
        int firstDay = 1;
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
        for (int i = 0; i < registerReports.size() - 1; i++) {
            if (registerReports.get(i).isToShow()) {
                for (int j = i + 1; j < registerReports.size(); j++) {
                    if (registerReports.get(j).isToShow()) {
                        registerReports.get(i).setQ1delta(registerReports.get(j).getQ1() - registerReports.get(i).getQ1());
                        registerReports.get(i).setQ2delta(registerReports.get(j).getQ2() - registerReports.get(i).getQ2());
                        registerReports.get(i).setTimeOndelta(registerReports.get(j).getTimeOn() - registerReports.get(i).getTimeOn());
                        registerReports.get(i).setVdelta(registerReports.get(j).getV1() - registerReports.get(j).getV2() - registerReports.get(i).getV1() - registerReports.get(i).getV2());
                        i = j - 1;
                        break;
                    }
                }
            }
        }

    }

    public List<RegisterReport> getRegisterReports() {
        return registerReports;
    }

    public void setRegisterReports(List<RegisterReport> registerReports) {
        this.registerReports = registerReports;
    }

    public Timestamp getSelectMonth() {
        return selectMonth;
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


    public String getButton3Style() {
        String ret = "";
        if (button3) {
            ret = button3Style;
        }
        return ret;
    }


    public String getButton1Style() {
        String ret = "";
        if (button1) {
            ret = button1Style;
        }
        return ret;
    }


    public String getButton2Style() {
        String ret = "";
        if (button2) {
            ret = button2Style;
        }
        return ret;
    }


    public String getButton4Style() {
        String ret = "";
        if (button4) {
            ret = button4Style;
        }
        return ret;
    }

    public String getStyleYellow() {
        String ret = "";
        if (yellow) {
            ret = styleYellow;
        }
        return ret;
    }
}
