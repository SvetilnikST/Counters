package pst.beans.unitQ;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.rpc.server.ServletEndpointContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean (name="unitQAdministration")
@ViewScoped
public class UnitQAdministration implements Serializable {

    private TblUnitQEntity tblUnitQEntity;
    private List<UnitQAdministration> unitQAdministrations;

    @EJB
    private UnitQDAO unitQDAO;

    @Default
    private int id;
    private String name;

    private List<TblUnitQEntity> tblUnitQEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        if (param != null) {
            id = Integer.parseInt(param);
            this.tblUnitQEntity = unitQDAO.read(id);
            if (tblUnitQEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblUnitQEntity);

        } else {
            this.name = "";
        }
        tblUnitQEntitysList = unitQDAO.findAll();
    }

    public void load(TblUnitQEntity unitQEntity) {
        this.setId(unitQEntity.getId());
        this.setName(unitQEntity.getName());
    }

    public String save() {
        tblUnitQEntity = unitQDAO.find(this.id);
        if (tblUnitQEntity == null) {
            tblUnitQEntity = new TblUnitQEntity();
        }
        tblUnitQEntity.setName(this.name);
        if (tblUnitQEntity.getId() == 0) {

            unitQDAO.create(tblUnitQEntity);
        } else {
            unitQDAO.update(tblUnitQEntity);
        }
        return "viewUnitQ.xhtml?faces-redirect=true&id=" + String.valueOf(tblUnitQEntity.getId());
    }

    public TblUnitQEntity getTblUnitQEntity() {
        return tblUnitQEntity;
    }

    public void setTblUnitQEntity(TblUnitQEntity tblUnitQEntity) {
        this.tblUnitQEntity = tblUnitQEntity;
    }

    public List<UnitQAdministration> getUnitQAdministrations() {
        return unitQAdministrations;
    }

    public void setUnitQAdministrations(List<UnitQAdministration> unitQAdministrations) {
        this.unitQAdministrations = unitQAdministrations;
    }

    public UnitQDAO getUnitQDAO() {
        return unitQDAO;
    }

    public void setUnitQDAO(UnitQDAO unitQDAO) {
        this.unitQDAO = unitQDAO;
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

    public List<TblUnitQEntity> getTblUnitQEntitysList() {
        return tblUnitQEntitysList;
    }

    public void setTblUnitQEntitysList(List<TblUnitQEntity> tblUnitQEntitysList) {
        this.tblUnitQEntitysList = tblUnitQEntitysList;
    }

}
