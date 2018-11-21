package pst.beans.schedule;

import org.joda.time.LocalDateTime;
import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Definition of the two JMS destinations used by the quickstart
 * (one queue and one topic).
 */
@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/CountersToAskMDBQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "CountersToAskMDBQueue"
                )
        }
)


//@WebServlet("/HelloWorldMDBServletClient")
@Singleton
public class AutoSheduleBean {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/queue/CountersToAskMDBQueue")
    private Queue queue;


    @Inject
    DeviceDAO deviceDAO;

    @Inject
    SheduleDAO sheduleDAO;

    //    @Override
    @Schedule(second = "30", minute = "*", hour = "*", persistent = true)
    protected void generateShedule(){

        List<TblDeviceEntity> deviceList = deviceDAO.listAllInTime();

        LocalDateTime dtPlusOneHour = new LocalDateTime()
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);

        dtPlusOneHour = dtPlusOneHour.plusHours(1);

        Timestamp timeToSchedlul = new Timestamp(dtPlusOneHour.toDateTime().getMillis());

        TblScheduleEntity scheduleRecord;
        List<TblScheduleEntity> tblScheduleList = new ArrayList<>();
        String stringToRequest;

        for (TblDeviceEntity device :
                deviceList) {
            scheduleRecord = new TblScheduleEntity();
            scheduleRecord.setDeviceId(device.getId());
            stringToRequest = "-format=json -number=1 -type=" + device.getTypeDeviceEntity().getTypeInt()+" -unitQ="+device.getUnitQEntity().getId() ;
            stringToRequest = stringToRequest + " "+device.getIp()+":"+device.getNum_port();
            scheduleRecord.setStringtosend(stringToRequest);
            scheduleRecord.setStatusexecute(0);
            scheduleRecord.setTimetoexecute(timeToSchedlul);
            tblScheduleList.add(scheduleRecord);
        }

        sheduleDAO.makeInserts(tblScheduleList);

        System.out.println("HaHaHA");
    }

    //    @Override
    @Schedule(second = "0", minute = "55", hour = "*", persistent = false)
    protected void sendTimer() {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter out = resp.getWriter();
//        out.write("<h1>Quickstart: Example demonstrates the use of <strong>JMS 2.0</strong> and <strong>EJB 3.2 Message-Driven Bean</strong> in JBoss EAP.</h1>");
        try {
//            boolean useTopic = req.getParameterMap().keySet().contains("topic");
            boolean useTopic=false;
            final Destination destination = queue;

            List<TblScheduleEntity> listSchedule = sheduleDAO.listRecordsToExecute();

            Map<String, Object> bodyValue;// = new HashMap<String, Object>();

            for(TblScheduleEntity scheduleEntity: listSchedule){
                bodyValue = new HashMap<String, Object>();
                bodyValue.put("id",String.valueOf(scheduleEntity.getId()));
                bodyValue.put("send",scheduleEntity.getStringtosend());

                context.createProducer().send(destination,bodyValue);
            }

//            for (String sen: toSend) {
//                context.createProducer().send(destination, sen);
////                out.write("Message: " + sen + "</br>");
//            }

//            for (int i = 0; i < MSG_COUNT; i++) {
//                String text = "This is message " + (i + 1);
//                context.createProducer().send(destination, text);
//                out.write("Message (" + i + "): " + text + "</br>");
//            }
//            out.write("<p><i>Go to your JBoss EAP server console or server log to see the result of messages processing.</i></p>");
        } finally {
//            if (out != null) {
//                out.close();
//            }
        }
    }


}
