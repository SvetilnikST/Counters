package pst.beans.department;

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

@ManagedBean (name="departmentAdministration")
@ViewScoped
public class DepartmentAdministration implements Serializable {

    private TblDepartmentEntity tblDepartmentEntity;
    private List<DepartmentAdministration> departmentAdministrations;

    @EJB
    private DepartmentDAO departmentDAO;

    @Default
    private int idDepartment;
    private String department;

    private List<TblDepartmentEntity> tblDepartmentEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idDepartment");
        if (param != null) {
            idDepartment = Integer.parseInt(param);
            this.tblDepartmentEntity = departmentDAO.read(idDepartment);
            if (tblDepartmentEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblDepartmentEntity);

        } else {
            this.department = "";
        }
        tblDepartmentEntitysList = departmentDAO.findAll();
    }

    public void load(TblDepartmentEntity departmentEntity) {
        this.setIdDepartment(departmentEntity.getIdDepartment());
        this.setDepartment(departmentEntity.getDepartment());
    }

    public String save() {
        tblDepartmentEntity = departmentDAO.find(this.idDepartment);
        if (tblDepartmentEntity == null) {
            tblDepartmentEntity = new TblDepartmentEntity();
        }
        tblDepartmentEntity.setDepartment(this.department);
        if (tblDepartmentEntity.getIdDepartment() == 0) {

            departmentDAO.create(tblDepartmentEntity);
        } else {
            departmentDAO.update(tblDepartmentEntity);
        }
        return "listDepartment.xhtml?faces-redirect=true&idDepartment=" + String.valueOf(tblDepartmentEntity.getIdDepartment());
    }

    public TblDepartmentEntity getTblDepartmentEntity() {
        return tblDepartmentEntity;
    }

    public void setTblDepartmentEntity(TblDepartmentEntity tblDepartmentEntity) {
        this.tblDepartmentEntity = tblDepartmentEntity;
    }

    public List<DepartmentAdministration> getDepartmentAdministrations() {
        return departmentAdministrations;
    }

    public void setDepartmentAdministrations(List<DepartmentAdministration> departmentAdministrations) {
        this.departmentAdministrations = departmentAdministrations;
    }

    public DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<TblDepartmentEntity> getTblDepartmentEntitysList() {
        return tblDepartmentEntitysList;
    }

    public void setTblDepartmentEntitysList(List<TblDepartmentEntity> tblDepartmentEntitysList) {
        this.tblDepartmentEntitysList = tblDepartmentEntitysList;
    }
}
