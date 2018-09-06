package pst.asu.beans.qBox;

import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.*;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "boxCommonAdministration")

public class BoxCommonAdministration implements Serializable {
    private TblBoxCommonEntity tblBoxCommonEntity;
    private List<BoxCommonAdministration> boxCommonAdministrations;

    private final String FILENAME = "D:/1_SVETILNIK_NEED/Java/Counters/sku.json";

    @EJB
    private BoxCommonDAO boxCommonDAO;

    @Default
    private int idd;
    private String serial;
    private int unitQ;
    private int timeRequest;
    private int timeDevice;
    private int timeOn;
    private int timeRunCommon;
    private int instore1;
    private int instore2;
    private int instore3;

    private List<TblBoxCommonEntity> tblBoxCommonEntitysList;

    //парсинг JSON
    public void readJSON() {
        String gsonString = getJsonString();
        Gson gson = new Gson();
//        String gsonString="{\n" +
//                "  \"serial\":\"00002128\",\n" +
//                "  \"unitQ\":1,\n" +
//                "  \"timeRequest\":1527706940,\n" +
//                "  \"timeDevice\":1525115100,\n" +
//                "  \"timeOn\":24209554,\n" +
//                "  \"timeRunCommon\":23694812\n" +
//                "}\n";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            TblBoxCommonEntity qbox_data_common = gson.fromJson(gsonString, TblBoxCommonEntity.class);
            boxCommonDAO.create(qbox_data_common);
            //написать запись в базу полученные данные
            System.out.println(qbox_data_common.getSerial());
        } catch (FileNotFoundException ex) {
            int a = 0;
        }
    }

    private String getJsonString() {
        ProcessBuilder procBuilder;
        String pathCmd = "D:\\1_SVETILNIK_NEED\\Java\\Counters\\1.cmd";
        String pathJson = "D:\\1_SVETILNIK_NEED\\Java\\Counters\\scu.json";
        procBuilder = new ProcessBuilder(pathCmd, pathJson);
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

    public TblBoxCommonEntity getTblBoxCommonEntity() {
        return tblBoxCommonEntity;
    }

    public List<BoxCommonAdministration> getBoxCommonAdministrations() {
        return boxCommonAdministrations;
    }

    public void setBoxCommonAdministrations(List<BoxCommonAdministration> boxCommonAdministrations) {
        this.boxCommonAdministrations = boxCommonAdministrations;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public BoxCommonDAO getBoxCommonDAO() {
        return boxCommonDAO;
    }

    public void setBoxCommonDAO(BoxCommonDAO boxCommonDAO) {
        this.boxCommonDAO = boxCommonDAO;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getUnitQ() {
        return unitQ;
    }

    public void setUnitQ(int unitQ) {
        this.unitQ = unitQ;
    }

    public int getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(int timeRequest) {
        this.timeRequest = timeRequest;
    }

    public int getTimeDevice() {
        return timeDevice;
    }

    public void setTimeDevice(int timeDevice) {
        this.timeDevice = timeDevice;
    }

    public int getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(int timeOn) {
        this.timeOn = timeOn;
    }

    public int getTimeRunCommon() {
        return timeRunCommon;
    }

    public void setTimeRunCommon(int timeRunCommon) {
        this.timeRunCommon = timeRunCommon;
    }

    public int getInstore1() {
        return instore1;
    }

    public void setInstore1(int instore1) {
        this.instore1 = instore1;
    }

    public int getInstore2() {
        return instore2;
    }

    public void setInstore2(int instore2) {
        this.instore2 = instore2;
    }

    public int getInstore3() {
        return instore3;
    }

    public void setInstore3(int instore3) {
        this.instore3 = instore3;
    }

    public List<TblBoxCommonEntity> getTblBoxCommonEntitysList() {
        return tblBoxCommonEntitysList;
    }

    public void setTblBoxCommonEntitysList(List<TblBoxCommonEntity> tblBoxCommonEntitysList) {
        this.tblBoxCommonEntitysList = tblBoxCommonEntitysList;
    }
}
