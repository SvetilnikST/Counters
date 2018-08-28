//package pst.asu.beans.qBox;
//
//import com.google.gson.Gson;
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.Serializable;
//import java.util.List;
//
//@ManagedBean(name = "boxCommonAdministration")
//@ViewScoped
//public class BoxCommonAdministration implements Serializable {
//    private TblBoxCommonEntity tblBoxCommonEntity;
//    private List<BoxCommonAdministration> boxCommonAdministrations;
//
//    private final String FILENAME = "D:/1_SVETILNIK_NEED/Java/Counters/sku.json";
//
//    @EJB
//    private  BoxCommonDAO boxCommonDAO;
//
//    @Inject
//    private int id;
//    private String serial;
//    private int unitQ;
//    private int timeRequest;
//    private int timeDevice;
//    private int timeOn;
//    private int timeRunCommon;
//    private int instore1;
//    private int instore2;
//    private int instore3;
//
//    private List<TblBoxCommonEntity> tblBoxCommonEntityList;
//
//    //парсинг JSON
//    public void readJSON(){
//        Gson gson = new Gson();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
//            TblBoxCommonEntity qbox_data_common = gson.fromJson(reader, TblBoxCommonEntity.class);
//            //написать запись в базу полученные данные
//            System.out.println(qbox_data_common.getSerial());
//        } catch (FileNotFoundException ex) {
//            int a=0;
//        }
//    }
//
//
//
//    public String save(){
//        tblBoxCommonEntity = boxCommonDAO.read(this.id);
//        if (tblBoxCommonEntity== null){
//            tblBoxCommonEntity = new TblBoxCommonEntity();
//        }
//        tblBoxCommonEntity.setSerial(this.serial);
//        tblBoxCommonEntity.setUnitQ(this.unitQ);
////        tblBoxCommonEntity.setUnitQ(this.unitQ);
//        tblBoxCommonEntity.setTimeRequest(this.timeRequest);
//        tblBoxCommonEntity.setTimeDevice(this.timeDevice);
//        tblBoxCommonEntity.setTimeOn(this.timeOn);
//        tblBoxCommonEntity.setTimeRunCommon(this.timeRunCommon);
//        tblBoxCommonEntity.setInstore1(this.instore1);
//        tblBoxCommonEntity.setInstore2(this.instore2);
//        tblBoxCommonEntity.setInstore3(this.instore3);
//        return String.valueOf(tblBoxCommonEntity.getId());
//    }
//
//
//    public List<BoxCommonAdministration> getBoxCommonAdministrations() {
//        return boxCommonAdministrations;
//    }
//
//    public void setBoxCommonAdministrations(List<BoxCommonAdministration> boxCommonAdministrations) {
//        this.boxCommonAdministrations = boxCommonAdministrations;
//    }
//
//    public List<TblBoxCommonEntity> getTblBoxCommonEntityList() {
//        return tblBoxCommonEntityList;
//    }
//
//    public void setTblBoxCommonEntityList(List<TblBoxCommonEntity> tblBoxCommonEntityList) {
//        this.tblBoxCommonEntityList = tblBoxCommonEntityList;
//    }
//
//    public TblBoxCommonEntity getTblBoxCommonEntity() {
//        return tblBoxCommonEntity;
//    }
//
//    public void setTblBoxCommonEntity(TblBoxCommonEntity tblBoxCommonEntity) {
//        this.tblBoxCommonEntity = tblBoxCommonEntity;
//    }
//
//    public BoxCommonDAO getBoxCommonDAO() {
//        return boxCommonDAO;
//    }
//
//    public void setBoxCommonDAO(BoxCommonDAO boxCommonDAO) {
//        this.boxCommonDAO = boxCommonDAO;
//    }
//
//
//
//    public String getSerial() {
//        return serial;
//    }
//
//    public void setSerial(String serial) {
//        this.serial = serial;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUnitQ() {
//        return unitQ;
//    }
//
//    public void setUnitQ(int unitQ) {
//        this.unitQ = unitQ;
//    }
//
//    public int getTimeRequest() {
//        return timeRequest;
//    }
//
//    public void setTimeRequest(int timeRequest) {
//        this.timeRequest = timeRequest;
//    }
//
//    public int getTimeDevice() {
//        return timeDevice;
//    }
//
//    public void setTimeDevice(int timeDevice) {
//        this.timeDevice = timeDevice;
//    }
//
//    public int getTimeOn() {
//        return timeOn;
//    }
//
//    public void setTimeOn(int timeOn) {
//        this.timeOn = timeOn;
//    }
//
//    public int getTimeRunCommon() {
//        return timeRunCommon;
//    }
//
//    public void setTimeRunCommon(int timeRunCommon) {
//        this.timeRunCommon = timeRunCommon;
//    }
//
//    public int getInstore1() {
//        return instore1;
//    }
//
//    public void setInstore1(int instore1) {
//        this.instore1 = instore1;
//    }
//
//    public int getInstore2() {
//        return instore2;
//    }
//
//    public void setInstore2(int instore2) {
//        this.instore2 = instore2;
//    }
//
//    public int getInstore3() {
//        return instore3;
//    }
//
//    public void setInstore3(int instore3) {
//        this.instore3 = instore3;
//    }
//}
