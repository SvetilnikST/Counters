package pst.beans.organization;




import pst.beans.object.ObjectDAO;
import pst.beans.object.TblObjectEntity;

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

//    TblObjectEntity objectEntity;

    @EJB
    private OrganizationDAO organizationDAO;

//    @EJB
//    private ObjectDAO objectDAO;

    @Default
    private int idOrganization;
    private String nameOrganization;

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
        }
        tblOrganizationEntitysList = organizationDAO.findAll();
//        objectEntityList = objectDAO.findAll();
    }

    public void load(TblOrganizationEntity organizationEntity) {
        this.setIdOrganization(organizationEntity.getIdOrganization());
        this.setNameOrganization(organizationEntity.getNameOrganization());
        this.setIdOrganization(organizationEntity.getIdOrganization());
//        this.setObjectEntity(organizationEntity.);
    }

    public String save() {
        tblOrganizationEntity = organizationDAO.find(this.idOrganization);
        if (tblOrganizationEntity == null) {
            tblOrganizationEntity= new TblOrganizationEntity();
        }
        tblOrganizationEntity.setNameOrganization(this.nameOrganization);
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

//    public ObjectDAO getObjectDAO() {
//        return objectDAO;
//    }
//
//    public void setObjectDAO(ObjectDAO objectDAO) {
//        this.objectDAO = objectDAO;
//    }

    public List<TblObjectEntity> getObjectEntityList() {
        return objectEntityList;
    }

    public void setObjectEntityList(List<TblObjectEntity> objectEntityList) {
        this.objectEntityList = objectEntityList;
    }

//    public TblObjectEntity getObjectEntity() {
//        return objectEntity;
//    }
//
//    public void setObjectEntity(TblObjectEntity objectEntity) {
//        this.objectEntity = objectEntity;
//    }
}
