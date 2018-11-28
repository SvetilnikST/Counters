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



@ManagedBean(name = "adressAdministration")
@ViewScoped
public class AdressAdministration implements Serializable {

    private TblAdressEntity tblAdressEntity;
    private List<AdressAdministration> adressAdministrations;
    TblCityEntity cityEntity;
    TblStreetEntity streetEntity;

    @EJB
    private AdressDAO adressDAO;

    @EJB
    private CityDAO cityDAO;

    @EJB
    private StreetDAO streetDAO;

    @Default
    private int idAdress;
    private String home;

    private List<TblCityEntity> cityEntityList;
    private List<TblStreetEntity> streetEntityList;

    private List<TblAdressEntity> tblAdressEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idAdress");
        if (param != null) {
            idAdress = Integer.parseInt(param);
            this.tblAdressEntity = adressDAO.read(idAdress);
            if (tblAdressEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblAdressEntity);

        } else {
            this.home = "";
        }
        tblAdressEntitysList = adressDAO.findAll();
        cityEntityList = cityDAO.findAll();
        streetEntityList = streetDAO.findAll();
    }

    private void load(TblAdressEntity adressEntity){
        this.setIdAdress(adressEntity.getIdAdress());
        this.setHome(adressEntity.getHome());
        this.setCityEntity(adressEntity.getCityEntity());
        this.setStreetEntity(adressEntity.getStreetEntity());
    }


    public String save() {
        tblAdressEntity = adressDAO.find(this.idAdress);
        if (tblAdressEntity == null) {
            tblAdressEntity = new TblAdressEntity();
        }
        tblAdressEntity.setHome(this.home);
        tblAdressEntity.setCityEntity(cityEntity);
        tblAdressEntity.setStreetEntity(streetEntity);
        if (tblAdressEntity.getIdAdress() == 0) {

            adressDAO.create(tblAdressEntity);
        } else {
            adressDAO.update(tblAdressEntity);
        }
        return "listAdress.xhtml?faces-redirect=true&id=" + String.valueOf(tblAdressEntity.getIdAdress());
    }


    public TblAdressEntity getTblAdressEntity() {
        return tblAdressEntity;
    }

    public void setTblAdressEntity(TblAdressEntity tblAdressEntity) {
        this.tblAdressEntity = tblAdressEntity;
    }

    public List<AdressAdministration> getAdressAdministrations() {
        return adressAdministrations;
    }

    public void setAdressAdministrations(List<AdressAdministration> adressAdministrations) {
        this.adressAdministrations = adressAdministrations;
    }

    public AdressDAO getAdressDAO() {
        return adressDAO;
    }

    public void setAdressDAO(AdressDAO adressDAO) {
        this.adressDAO = adressDAO;
    }

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
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



    public List<TblAdressEntity> getTblAdressEntitysList() {
        return tblAdressEntitysList;
    }

    public void setTblAdressEntitysList(List<TblAdressEntity> tblAdressEntitysList) {
        this.tblAdressEntitysList = tblAdressEntitysList;
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

    public List<TblStreetEntity> getStreetEntityList() {
        return streetEntityList;
    }

    public void setStreetEntityList(List<TblStreetEntity> streetEntityList) {
        this.streetEntityList = streetEntityList;
    }
}
