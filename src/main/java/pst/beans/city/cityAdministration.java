package pst.beans.city;



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

@ManagedBean (name="cityAdministration")
@ViewScoped
public class cityAdministration implements Serializable {

    private TblCityEntity tblCityEntity;
    private List<cityAdministration> cityAdministrations;
    private List<TblCityEntity> tblCityEntitysList;

    @EJB
    private CityDAO cityDAO;

    @Default
    private int idCity;
    private String nameCity;



    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idCity");
        if (param != null) {
            idCity = Integer.parseInt(param);
            this.tblCityEntity = cityDAO.read(idCity);
            if (tblCityEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblCityEntity);

        } else {
            this.nameCity = "";
        }
        tblCityEntitysList = cityDAO.findAll();
    }


    public void load(TblCityEntity cityEntity) {
        this.setIdCity(cityEntity.getIdCity());
        this.setNameCity(cityEntity.getNameCity());
    }

    public String save() {
        tblCityEntity = cityDAO.find(this.idCity);
        if (tblCityEntity == null) {
            tblCityEntity = new TblCityEntity();
        }
        tblCityEntity.setNameCity(this.nameCity);
        if (tblCityEntity.getIdCity() == 0) {

            cityDAO.create(tblCityEntity);
        } else {
            cityDAO.update(tblCityEntity);
        }
//        return "listCity.xhtml?faces-redirect=true&id=" + String.valueOf(tblCityEntity.getIdCity());
        return "list.xhtml";
    }

    public TblCityEntity getTblCityEntity() {
        return tblCityEntity;
    }

    public void setTblCityEntity(TblCityEntity tblCityEntity) {
        this.tblCityEntity = tblCityEntity;
    }

    public List<cityAdministration> getCityAdministrations() {
        return cityAdministrations;
    }

    public void setCityAdministrations(List<cityAdministration> cityAdministrations) {
        this.cityAdministrations = cityAdministrations;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public List<TblCityEntity> getTblCityEntitysList() {
        return tblCityEntitysList;
    }

    public void setTblCityEntitysList(List<TblCityEntity> tblCityEntitysList) {
        this.tblCityEntitysList = tblCityEntitysList;
    }
}
