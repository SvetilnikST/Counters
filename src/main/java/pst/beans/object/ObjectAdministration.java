package pst.beans.object;

import pst.beans.adress.AdressDAO;
import pst.beans.adress.TblAdressEntity;
import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;
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

@ManagedBean (name="objectAdministration")
@ViewScoped
public class ObjectAdministration implements Serializable {

    private TblObjectEntity tblObjectEntity;
    private List<ObjectAdministration> objectAdministrations;
    private List<TblObjectEntity> tblObjectEntitysList;

    @EJB
    private ObjectDAO objectDAO;

    @EJB
    private AdressDAO adressDAO;

    @EJB
    private DeviceDAO deviceDAO;


    @Default
    private int id;
    private String nameObject;
    TblAdressEntity adressEntity;
    TblDeviceEntity deviceEntity;




    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        if (param != null) {
            id = Integer.parseInt(param);
            this.tblObjectEntity = objectDAO.read(id);
            if (tblObjectEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblObjectEntity);

        } else {
            this.nameObject = "";
        }
        tblObjectEntitysList = objectDAO.findAll();
    }

    public void load(TblObjectEntity objectEntity) {
        this.setId(objectEntity.getId());
        this.setNameObject(objectEntity.getNameObject());
        this.setAdressEntity(objectEntity.getAdressEntity());
        //TODO дописать сюда устройство
//        this.setDeviceEntity(objectEntity.get);
    }

    public String save() {
        tblObjectEntity = objectDAO.find(this.id);
        if (tblObjectEntity == null) {
            tblObjectEntity = new TblObjectEntity();
        }
        tblObjectEntity.setNameObject(this.nameObject);
        if (tblObjectEntity.getId() == 0) {

            objectDAO.create(tblObjectEntity);
        } else {
            objectDAO.update(tblObjectEntity);
        }
        return "listObject.xhtml?faces-redirect=true&id=" + String.valueOf(tblObjectEntity.getId());
    }

    public TblObjectEntity getTblObjectEntity() {
        return tblObjectEntity;
    }

    public void setTblObjectEntity(TblObjectEntity tblObjectEntity) {
        this.tblObjectEntity = tblObjectEntity;
    }

    public List<ObjectAdministration> getObjectAdministrations() {
        return objectAdministrations;
    }

    public void setObjectAdministrations(List<ObjectAdministration> objectAdministrations) {
        this.objectAdministrations = objectAdministrations;
    }

    public ObjectDAO getObjectDAO() {
        return objectDAO;
    }

    public void setObjectDAO(ObjectDAO objectDAO) {
        this.objectDAO = objectDAO;
    }

    public AdressDAO getAdressDAO() {
        return adressDAO;
    }

    public void setAdressDAO(AdressDAO adressDAO) {
        this.adressDAO = adressDAO;
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public TblAdressEntity getAdressEntity() {
        return adressEntity;
    }

    public void setAdressEntity(TblAdressEntity adressEntity) {
        this.adressEntity = adressEntity;
    }

    public TblDeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(TblDeviceEntity deviceEntity) {
        this.deviceEntity = deviceEntity;
    }

    public List<TblObjectEntity> getTblObjectEntitysList() {
        return tblObjectEntitysList;
    }

    public void setTblObjectEntitysList(List<TblObjectEntity> tblObjectEntitysList) {
        this.tblObjectEntitysList = tblObjectEntitysList;
    }

}
