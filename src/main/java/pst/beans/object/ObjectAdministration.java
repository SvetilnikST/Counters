package pst.beans.object;

import pst.beans.city.CityDAO;
import pst.beans.city.TblCityEntity;
import pst.beans.organization.OrganizationDAO;
import pst.beans.organization.TblOrganizationEntity;
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
    TblOrganizationEntity organizationEntity;

    @EJB
    private ObjectDAO objectDAO;

    @EJB
    private CityDAO cityDAO;

    @EJB
    private StreetDAO streetDAO;

    @EJB
    private OrganizationDAO organizationDAO;



    @Default
    private int idObject;
    private String home;
    private String nameObject;
    private double hotWater;
    private double heating;
    private double ventilation;

    private List<TblCityEntity> cityEntityList;
    private List<TblStreetEntity> streetEntityList;
    private List<TblOrganizationEntity> organizationEntityList;
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
            this.nameObject="";
            this.hotWater=0.0;
            this.heating=0.0;
            this.ventilation=0.0;
        }
        tblObjectEntitysList = objectDAO.findAll();
        cityEntityList = cityDAO.findAll();
        streetEntityList = streetDAO.findAll();
        organizationEntityList = organizationDAO.findAll();
    }

    private void load(TblObjectEntity objectEntity) {
        this.setIdObject(objectEntity.getIdObject());
        this.setHome(objectEntity.getHome());
        this.setNameObject(objectEntity.getNameObject());
        this.setCityEntity(objectEntity.getCityEntity());
        this.setStreetEntity(objectEntity.getStreetEntity());
        this.setOrganizationEntity(objectEntity.getOrganizationEntity());
        this.setHotWater(objectEntity.getHotWater());
        this.setHeating(objectEntity.getHeating());
        this.setVentilation(objectEntity.getVentilation());
    }


    public String save() {
        tblObjectEntity = objectDAO.find(this.idObject);
        if (tblObjectEntity == null) {
            tblObjectEntity = new TblObjectEntity();
        }
        tblObjectEntity.setNameObject(this.nameObject);
        tblObjectEntity.setHome(this.home);
        tblObjectEntity.setCityEntity(cityEntity);
        tblObjectEntity.setStreetEntity(streetEntity);
        tblObjectEntity.setOrganizationEntity(organizationEntity);
        tblObjectEntity.setHotWater(this.hotWater);
        tblObjectEntity.setHeating(this.heating);
        tblObjectEntity.setVentilation(this.ventilation);
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

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public TblOrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(TblOrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    public OrganizationDAO getOrganizationDAO() {
        return organizationDAO;
    }

    public void setOrganizationDAO(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    public List<TblOrganizationEntity> getOrganizationEntityList() {
        return organizationEntityList;
    }

    public void setOrganizationEntityList(List<TblOrganizationEntity> organizationEntityList) {
        this.organizationEntityList = organizationEntityList;
    }

    public double getHotWater() {
        return hotWater;
    }

    public void setHotWater(double hotWater) {
        this.hotWater = hotWater;
    }

    public double getHeating() {
        return heating;
    }

    public void setHeating(double heating) {
        this.heating = heating;
    }

    public double getVentilation() {
        return ventilation;
    }

    public void setVentilation(double ventilation) {
        this.ventilation = ventilation;
    }
}
