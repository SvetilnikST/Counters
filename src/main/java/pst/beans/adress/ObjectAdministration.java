package pst.beans.adress;

import pst.beans.city.CityDAO;
import pst.beans.city.TblCityEntity;
import pst.beans.street.StreetDAO;
import pst.beans.street.TblStreetEntity;

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



@ManagedBean(name = "objectAdministration")
@ViewScoped
public class ObjectAdministration implements Serializable {

    private TblObjectEntity tblObjectEntity;
    private List<ObjectAdministration> objectAdministrations;
    TblCityEntity cityEntity;
    TblStreetEntity streetEntity;

    @EJB
    private ObjectDAO objectDAO;

    @EJB
    private CityDAO cityDAO;

    @EJB
    private StreetDAO streetDAO;

    @Default
    private int idObject;
    private String home;

    private List<TblCityEntity> cityEntityList;
    private List<TblStreetEntity> streetEntityList;

    private List<TblObjectEntity> tblObjectEntitysList;

    @PostConstruct
    void start() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idObject");
        if (param != null) {
            idObject = Integer.parseInt(param);
            this.tblObjectEntity = objectDAO.read(idObject);
            if (tblObjectEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblObjectEntity);

        } else {
            this.home = "";
        }
        tblObjectEntitysList = objectDAO.findAll();
        cityEntityList = cityDAO.findAll();
        streetEntityList = streetDAO.findAll();
    }

    private void load(TblObjectEntity objectEntity) {
        this.setIdObject(objectEntity.getIdObject());
        this.setHome(objectEntity.getHome());
        this.setCityEntity(objectEntity.getCityEntity());
        this.setStreetEntity(objectEntity.getStreetEntity());
    }


    public String save() {
        tblObjectEntity = objectDAO.find(this.idObject);
        if (tblObjectEntity == null) {
            tblObjectEntity = new TblObjectEntity();
        }
        tblObjectEntity.setHome(this.home);
        tblObjectEntity.setCityEntity(cityEntity);
        tblObjectEntity.setStreetEntity(streetEntity);
        if (tblObjectEntity.getIdObject() == 0) {

            objectDAO.create(tblObjectEntity);
        } else {
            objectDAO.update(tblObjectEntity);
        }
        return "listObject.xhtml?faces-redirect=true&id=" + String.valueOf(tblObjectEntity.getIdObject());
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

    public TblCityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(TblCityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public TblStreetEntity getStreetEntity() {
        return streetEntity;
    }

    public void setStreetEntity(TblStreetEntity streetEntity) {
        this.streetEntity = streetEntity;
    }

    public ObjectDAO getObjectDAO() {
        return objectDAO;
    }

    public void setObjectDAO(ObjectDAO objectDAO) {
        this.objectDAO = objectDAO;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public StreetDAO getStreetDAO() {
        return streetDAO;
    }

    public void setStreetDAO(StreetDAO streetDAO) {
        this.streetDAO = streetDAO;
    }

    public int getIdObject() {
        return idObject;
    }

    public void setIdObject(int idObject) {
        this.idObject = idObject;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public List<TblCityEntity> getCityEntityList() {
        return cityEntityList;
    }

    public void setCityEntityList(List<TblCityEntity> cityEntityList) {
        this.cityEntityList = cityEntityList;
    }

    public List<TblStreetEntity> getStreetEntityList() {
        return streetEntityList;
    }

    public void setStreetEntityList(List<TblStreetEntity> streetEntityList) {
        this.streetEntityList = streetEntityList;
    }

    public List<TblObjectEntity> getTblObjectEntitysList() {
        return tblObjectEntitysList;
    }

    public void setTblObjectEntitysList(List<TblObjectEntity> tblObjectEntitysList) {
        this.tblObjectEntitysList = tblObjectEntitysList;
    }
}
