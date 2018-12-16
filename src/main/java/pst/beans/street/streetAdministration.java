package pst.beans.street;

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

@ManagedBean (name="streetAdministration")
@ViewScoped
public class streetAdministration implements Serializable {

    private TblStreetEntity tblStreetEntity;
    private List<streetAdministration> streetAdministrations;

    @EJB
    private StreetDAO streetDAO;

    @Default
    private int idStreet;
    private String nameStreet;

    private List<TblStreetEntity> tblStreetEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idStreet");
        if (param != null) {
            idStreet = Integer.parseInt(param);
            this.tblStreetEntity = streetDAO.read(idStreet);
            if (tblStreetEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblStreetEntity);

        } else {
            this.nameStreet = "";
        }
        tblStreetEntitysList = streetDAO.findAll();
    }


    public void load(TblStreetEntity streetEntity) {
        this.setIdStreet(streetEntity.getIdStreet());
        this.setNameStreet(streetEntity.getNameStreet());
    }

    public String save() {
        tblStreetEntity = streetDAO.find(this.idStreet);
        if (tblStreetEntity == null) {
            tblStreetEntity = new TblStreetEntity();
        }
        tblStreetEntity.setNameStreet(this.nameStreet);
        if (tblStreetEntity.getIdStreet() == 0) {

            streetDAO.create(tblStreetEntity);
        } else {
            streetDAO.update(tblStreetEntity);
        }
//        return "listStreet.xhtml?faces-redirect=true&id=" + String.valueOf(tblStreetEntity.getIdStreet());
        return "list.xhtml";
    }


    public String remove(){
        tblStreetEntity = streetDAO.find(this.idStreet);
        streetDAO.remove(tblStreetEntity);
        return "listStreet.xhtml";
    }

    public TblStreetEntity getTblStreetEntity() {
        return tblStreetEntity;
    }

    public void setTblStreetEntity(TblStreetEntity tblStreetEntity) {
        this.tblStreetEntity = tblStreetEntity;
    }

    public List<streetAdministration> getStreetAdministrations() {
        return streetAdministrations;
    }

    public void setStreetAdministrations(List<streetAdministration> streetAdministrations) {
        this.streetAdministrations = streetAdministrations;
    }

    public StreetDAO getStreetDAO() {
        return streetDAO;
    }

    public void setStreetDAO(StreetDAO streetDAO) {
        this.streetDAO = streetDAO;
    }

    public int getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(int idStreet) {
        this.idStreet = idStreet;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public List<TblStreetEntity> getTblStreetEntitysList() {
        return tblStreetEntitysList;
    }

    public void setTblStreetEntitysList(List<TblStreetEntity> tblStreetEntitysList) {
        this.tblStreetEntitysList = tblStreetEntitysList;
    }

}
