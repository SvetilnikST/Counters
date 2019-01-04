package pst.beans.schedule;

import com.google.gson.Gson;
import pst.beans.qBox.Common.BoxCommonDAO;
import pst.beans.qBox.Common.TblBoxCommonEntity;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MessageDriven(name = "CountersToAskMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/CountersToAskMDBQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})

public class QueueMDB implements MessageListener {

    @EJB
    private BoxCommonDAO boxCommonDAO;

    @EJB
    private SheduleDAO sheduleDAO;


    private static final Logger LOGGER = Logger.getLogger(QueueMDB.class.toString());

    public void onMessage(Message rcvMessage) {
        MapMessage msg = null;

        Map<String, Object> bodyValue;
        Enumeration en;

        try {
            if (rcvMessage instanceof MapMessage) {
                msg = (MapMessage) rcvMessage;
                bodyValue =new HashMap();
                en = msg.getMapNames();

                while (en.hasMoreElements()) {
                    String property = (String) en.nextElement();
                    String mapObject = (String) msg.getObject(property);
                    bodyValue.put(property, mapObject);
                }

                String reciveMsg = (String) bodyValue.get("send");
                LOGGER.info("Received Message from queue: " + reciveMsg);
                try {
                    doWork(bodyValue);
                }catch (InterruptedException ex){
                    ex.toString();
                }
                finally {

                }


            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWork(Map<String, Object> rcvMsg) throws InterruptedException {
        int shedulerId= Integer.parseInt((String) rcvMsg.get("id"));
        String stringToSend = (String) rcvMsg.get("send");

        ProcessBuilder procBuilder;
        procBuilder = new ProcessBuilder("java","-jar","D:\\1_SVETILNIK_NEED\\Java\\Counters\\qBoxSimulator-0.0.1.jar",stringToSend);
        String[] toExec = stringToSend.split(" ");
        procBuilder = new ProcessBuilder(toExec);

        Process process = null;

        try {
            process = procBuilder.start();
            // читаем стандартный поток вывода
            // и выводим на экран
            InputStream stdout = process.getInputStream();
            InputStreamReader isrStdout = new InputStreamReader(stdout);
            BufferedReader brStdout = new BufferedReader(isrStdout);
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = brStdout.readLine()) != null) {
                stringBuilder.append(line);
            }
            // ждем пока завершится вызванная программа
            // и сохраняем код, с которым она завершилась в
            // в переменную exitVal
            int exitVal = process.waitFor();

            processJson(shedulerId, stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(" [x] begin "+rcvMsg);
//        for (char ch: rcvMsg.toCharArray()) {
//            if (ch == '.') Thread.sleep(1000);
//        }
//        System.out.println(" [x] END "+rcvMsg);
    }

    private void processJson(int shedulerId, String gsonString) {

        Gson gson = new Gson();
            TblBoxCommonEntity tblBoxCommon = gson.fromJson(gsonString, TblBoxCommonEntity.class);
            tblBoxCommon.refilAfterJson();
            TblScheduleEntity scheduleEntity = sheduleDAO.read(shedulerId);
            scheduleEntity.setStatusexecute(1);
            tblBoxCommon.setTblScheduleEntity(scheduleEntity);
            boxCommonDAO.create(tblBoxCommon);
            sheduleDAO.update(scheduleEntity);

    }

    private String getJsonString() {
        ProcessBuilder procBuilder;
//        String pathCmd = "D:\\1_SVETILNIK_NEED\\Java\\Counters\\1.cmd";
        String pathCmd = "D:\\1_SVETILNIK_NEED\\Java\\Counters\\2.cmd";
        String pathJson = "D:\\1_SVETILNIK_NEED\\Java\\Counters\\sku.json";
//        procBuilder = new ProcessBuilder(pathCmd, pathJson);
        procBuilder = new ProcessBuilder("java","-jar","D:\\1_SVETILNIK_NEED\\Java\\Counters\\qBoxSimulator-0.0.1.jar");
        // запуск программы
        Process process = null;
        try {
            process = procBuilder.start();
            // читаем стандартный поток вывода
            // и выводим на экран
            InputStream stdout = process.getInputStream();
            InputStreamReader isrStdout = new InputStreamReader(stdout);
            BufferedReader brStdout = new BufferedReader(isrStdout);
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = brStdout.readLine()) != null) {
                stringBuilder.append(line);
            }
            // ждем пока завершится вызванная программа
            // и сохраняем код, с которым она завершилась в
            // в переменную exitVal
            int exitVal = process.waitFor();

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}
