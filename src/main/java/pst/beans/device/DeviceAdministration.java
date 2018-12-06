package pst.beans.device;

import pst.beans.object.ObjectDAO;
import pst.beans.object.TblObjectEntity;
import pst.beans.typeDevice.TblTypeDeviceEntity;
import pst.beans.typeDevice.TypeDeviceDAO;
import pst.beans.unitQ.TblUnitQEntity;
import pst.beans.unitQ.UnitQDAO;

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

@ManagedBean(name = "deviceAdministration")
@ViewScoped
public class DeviceAdministration implements Serializable {
    private TblDeviceEntity tblDeviceEntity;
    private List<DeviceAdministration> deviceAdministrations;

    private List<TblTypeDeviceEntity> typeDeviceList;
    private List<TblUnitQEntity> unitQList;
    private List<TblObjectEntity> objectList;

    @EJB
    private DeviceDAO deviceDAO;

    @EJB
    private TypeDeviceDAO typeDeviceDAO;

    @EJB
    private UnitQDAO unitQDAO;

    @EJB
    private ObjectDAO objectDAO;


    @Default
    private int id;
    private String name;
    private String serial;
    private int lastRequestDate;
    private int requestsCount;
    private String ip;
    private int num_port;
    private int requestInterval;
    TblTypeDeviceEntity typeDeviceEntity;
    TblUnitQEntity unitQEntity;
    TblObjectEntity objectEntity;

    private List<TblDeviceEntity> tblDeviceEntitysList;

    @PostConstruct
    void start() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if (param != null) {

            id = Integer.parseInt(param);
            //чтение записи по id
            this.tblDeviceEntity = deviceDAO.read(id);
            //проверка если пустая entity, сообщение об ощибке
            if (tblDeviceEntity == null) {
                String message = "Bad request. Unknown type device.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            //вызываем метод загрузки данный в сущность
            load(tblDeviceEntity);
        } else {
            name = "";
            serial = "";
            lastRequestDate = 0;
            requestsCount = 0;
            ip = "";
            num_port = 0;
            requestInterval = 0;
        }
        tblDeviceEntitysList = deviceDAO.findAll();
        unitQList = unitQDAO.findAll();
        typeDeviceList = typeDeviceDAO.findAll();
        objectList = objectDAO.findAll();

    }

    public void load(TblDeviceEntity deviceEntity) {
        this.setId(deviceEntity.getId());
        this.setName(deviceEntity.getName());
        this.setTypeDeviceEntity(deviceEntity.getTypeDeviceEntity());
        this.setUnitQEntity(deviceEntity.getUnitQEntity());
        this.setSerial(deviceEntity.getSerial());
        this.setLastRequestDate(deviceEntity.getLastRequestDate());
        this.setRequestsCount(deviceEntity.getRequestsCount());
        this.setIp(deviceEntity.getIp());
        this.setNum_port(deviceEntity.getNum_port());
        this.setRequestInterval(deviceEntity.getRequestInterval());
        this.setObjectEntity(deviceEntity.getObjectEntity());
    }

    public String save() {
        tblDeviceEntity = deviceDAO.find(this.id);
        if (tblDeviceEntity == null) {
            tblDeviceEntity = new TblDeviceEntity();
        }

        tblDeviceEntity.setName(this.name);
        tblDeviceEntity.setTypeDeviceEntity(typeDeviceEntity);
        tblDeviceEntity.setUnitQEntity(unitQEntity);
        tblDeviceEntity.setSerial(this.serial);
        tblDeviceEntity.setLastRequestDate(this.lastRequestDate);
        tblDeviceEntity.setRequestsCount(this.requestsCount);
        tblDeviceEntity.setIp(this.ip);
        tblDeviceEntity.setNum_port(this.num_port);
        tblDeviceEntity.setRequestInterval(this.requestInterval);
        tblDeviceEntity.setObjectEntity(objectEntity);

        if (tblDeviceEntity.getId() == 0) {
            deviceDAO.create(tblDeviceEntity);
        } else {
            deviceDAO.update(tblDeviceEntity);
        }
        return "listDevice.xhtml?faces-redirect=true&id" + String.valueOf(tblDeviceEntity.getId());
    }

    public TblDeviceEntity getTblDeviceEntity() {
        return tblDeviceEntity;
    }

    public void setTblDeviceEntity(TblDeviceEntity tblDeviceEntity) {
        this.tblDeviceEntity = tblDeviceEntity;
    }

    public List<DeviceAdministration> getDeviceAdministrations() {
        return deviceAdministrations;
    }

    public void setDeviceAdministrations(List<DeviceAdministration> deviceAdministrations) {
        this.deviceAdministrations = deviceAdministrations;
    }

    public List<TblTypeDeviceEntity> getTypeDeviceList() {
        return typeDeviceList;
    }

    public void setTypeDeviceList(List<TblTypeDeviceEntity> typeDeviceList) {
        this.typeDeviceList = typeDeviceList;
    }

    public List<TblUnitQEntity> getUnitQList() {
        return unitQList;
    }

    public void setUnitQList(List<TblUnitQEntity> unitQList) {
        this.unitQList = unitQList;
    }

    public List<TblObjectEntity> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<TblObjectEntity> objectList) {
        this.objectList = objectList;
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public TypeDeviceDAO getTypeDeviceDAO() {
        return typeDeviceDAO;
    }

    public void setTypeDeviceDAO(TypeDeviceDAO typeDeviceDAO) {
        this.typeDeviceDAO = typeDeviceDAO;
    }

    public UnitQDAO getUnitQDAO() {
        return unitQDAO;
    }

    public void setUnitQDAO(UnitQDAO unitQDAO) {
        this.unitQDAO = unitQDAO;
    }

    public ObjectDAO getObjectDAO() {
        return objectDAO;
    }

    public void setObjectDAO(ObjectDAO objectDAO) {
        this.objectDAO = objectDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getLastRequestDate() {
        return lastRequestDate;
    }

    public void setLastRequestDate(int lastRequestDate) {
        this.lastRequestDate = lastRequestDate;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNum_port() {
        return num_port;
    }

    public void setNum_port(int num_port) {
        this.num_port = num_port;
    }

    public int getRequestInterval() {
        return requestInterval;
    }

    public void setRequestInterval(int requestInterval) {
        this.requestInterval = requestInterval;
    }

    public TblTypeDeviceEntity getTypeDeviceEntity() {
        return typeDeviceEntity;
    }

    public void setTypeDeviceEntity(TblTypeDeviceEntity typeDeviceEntity) {
        this.typeDeviceEntity = typeDeviceEntity;
    }

    public TblUnitQEntity getUnitQEntity() {
        return unitQEntity;
    }

    public void setUnitQEntity(TblUnitQEntity unitQEntity) {
        this.unitQEntity = unitQEntity;
    }

    public TblObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(TblObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public List<TblDeviceEntity> getTblDeviceEntitysList() {
        return tblDeviceEntitysList;
    }

    public void setTblDeviceEntitysList(List<TblDeviceEntity> tblDeviceEntitysList) {
        this.tblDeviceEntitysList = tblDeviceEntitysList;
    }
}
