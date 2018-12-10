package pst.beans.organization;

import pst.beans.city.CityDAO;
import pst.beans.city.TblCityEntity;
import pst.beans.object.TblObjectEntity;
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

@ManagedBean (name="organizationAdministration")
@ViewScoped
public class OrganizAdministration implements Serializable {

    private TblOrganizationEntity tblOrganizationEntity;
    private List<OrganizAdministration> organizationAdministrations;
    TblCityEntity cityEntity;
    TblStreetEntity streetEntity;

    @EJB
    private CityDAO cityDAO;

    @EJB
    private StreetDAO streetDAO;

    @EJB
    private OrganizationDAO organizationDAO;

    @Default
    private int idOrganization;
    private String nameOrganization;
    private int UNP;
    private String phone;
    private List<TblCityEntity> cityEntityList;
    private List<TblStreetEntity> streetEntityList;
    private String home;

    private List<TblObjectEntity> objectEntityList;

    private List<TblOrganizationEntity> tblOrganizationEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idOrganization");
        if (param != null) {
            idOrganization = Integer.parseInt(param);
            this.tblOrganizationEntity = organizationDAO.read(idOrganization);
            if (tblOrganizationEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblOrganizationEntity);

        } else {
            this.nameOrganization = "";
            this.UNP = 0;
            this.phone="";
            this.home="";
        }
        tblOrganizationEntitysList = organizationDAO.findAll();
        cityEntityList = cityDAO.findAll();
        streetEntityList = streetDAO.findAll();
    }

    public void load(TblOrganizationEntity organizationEntity) {
        this.setIdOrganization(organizationEntity.getIdOrganization());
        this.setNameOrganization(organizationEntity.getNameOrganization());
        this.setIdOrganization(organizationEntity.getIdOrganization());
        this.setUNP(organizationEntity.getUNP());
        this.setPhone(organizationEntity.getPhone());
        this.setStreetEntity(organizationEntity.getStreetEntity());
        this.setCityEntity(organizationEntity.getCityEntity());
    }

    public String save() {
        tblOrganizationEntity = organizationDAO.find(this.idOrganization);
        if (tblOrganizationEntity == null) {
            tblOrganizationEntity= new TblOrganizationEntity();
        }
        tblOrganizationEntity.setNameOrganization(this.nameOrganization);
        tblOrganizationEntity.setUNP(this.UNP);
        tblOrganizationEntity.setPhone(this.phone);
        tblOrganizationEntity.setHome(this.home);

        if (tblOrganizationEntity.getIdOrganization() == 0) {

            organizationDAO.create(tblOrganizationEntity);
        } else {
            organizationDAO.update(tblOrganizationEntity);
        }
        return "listOrganization.xhtml?faces-redirect=true&id=" + String.valueOf(tblOrganizationEntity.getIdOrganization());
    }

    public TblOrganizationEntity getTblOrganizationEntity() {
        return tblOrganizationEntity;
    }

    public void setTblOrganizationEntity(TblOrganizationEntity tblOrganizationEntity) {
        this.tblOrganizationEntity = tblOrganizationEntity;
    }

    public List<OrganizAdministration> getOrganizationAdministrations() {
        return organizationAdministrations;
    }

    public void setOrganizationAdministrations(List<OrganizAdministration> organizationAdministrations) {
        this.organizationAdministrations = organizationAdministrations;
    }

    public OrganizationDAO getOrganizationDAO() {
        return organizationDAO;
    }

    public void setOrganizationDAO(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public List<TblOrganizationEntity> getTblOrganizationEntitysList() {
        return tblOrganizationEntitysList;
    }

    public void setTblOrganizationEntitysList(List<TblOrganizationEntity> tblOrganizationEntitysList) {
        this.tblOrganizationEntitysList = tblOrganizationEntitysList;
    }

    public List<TblObjectEntity> getObjectEntityList() {
        return objectEntityList;
    }

    public void setObjectEntityList(List<TblObjectEntity> objectEntityList) {
        this.objectEntityList = objectEntityList;
    }

    public int getUNP() {
        return UNP;
    }

    public void setUNP(int UNP) {
        this.UNP = UNP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
