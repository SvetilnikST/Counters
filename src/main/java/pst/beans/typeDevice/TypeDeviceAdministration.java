package pst.beans.typeDevice;

import pst.beans.schedule.AutoSheduleBean;

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

@ManagedBean(name = "typeDeviceAdministration")
@ViewScoped
public class TypeDeviceAdministration implements Serializable {

    private TblTypeDeviceEntity tblTypeDeviceEntity;
    private List<TypeDeviceAdministration> typeDeviceAdministrations;
    private AutoSheduleBean myBean;

    @EJB
    private TypeDeviceDAO typeDeviceDAO;

    @Default
    private int id;
    private String name;
    private int typeInt;

    private List<TblTypeDeviceEntity> tblTypeDeviceEntitysList;

    @PostConstruct
    void start(){
        myBean = new AutoSheduleBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        //если не пустой id, делаем чтение записи по id
        if(param!=null){

            id = Integer.parseInt(param);
            //чтение записи по id
            this.tblTypeDeviceEntity = typeDeviceDAO.read(id);
            //проверка если пустая entity, сообщение об ощибке
            if(tblTypeDeviceEntity==null){
                String message = "Bad request. Unknown type device.";
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,message,null));
            }
            //вызываем метод загрузки данный в сущность
            load(tblTypeDeviceEntity);
        }
        else {
            name="";
            typeInt=9999;
        }
        tblTypeDeviceEntitysList=typeDeviceDAO.findAll();

    }

    public void load(TblTypeDeviceEntity typeDeviceEntity){
        this.setId(typeDeviceEntity.getId());
        this.setName(typeDeviceEntity.getName());
        this.setTypeInt(typeDeviceEntity.getTypeInt());
    }

    public String save(){
        tblTypeDeviceEntity = typeDeviceDAO.find(this.id);
        if(tblTypeDeviceEntity==null){
            tblTypeDeviceEntity = new TblTypeDeviceEntity();
        }
        tblTypeDeviceEntity.setName(this.name);
        tblTypeDeviceEntity.setTypeInt(this.typeInt);
        if(tblTypeDeviceEntity.getId()==0){
            typeDeviceDAO.create(tblTypeDeviceEntity);
        }
        else {
            typeDeviceDAO.update(tblTypeDeviceEntity);
        }
        return "listTypeDevice.xhtml?faces-redirect=true&id"+String.valueOf(tblTypeDeviceEntity.getId());
    }

    public String remove(){
        tblTypeDeviceEntity = typeDeviceDAO.find(this.id);
            typeDeviceDAO.remove(tblTypeDeviceEntity);
        return "listTypeDevice.xhtml";
    }

    public TblTypeDeviceEntity getTblTypeDeviceEntity() {
        return tblTypeDeviceEntity;
    }

    public void setTblTypeDeviceEntity(TblTypeDeviceEntity tblTypeDeviceEntity) {
        this.tblTypeDeviceEntity = tblTypeDeviceEntity;
    }

    public List<TypeDeviceAdministration> getTypeDeviceAdministrations() {
        return typeDeviceAdministrations;
    }

    public void setTypeDeviceAdministrations(List<TypeDeviceAdministration> typeDeviceAdministrations) {
        this.typeDeviceAdministrations = typeDeviceAdministrations;
    }

    public TypeDeviceDAO getTypeDeviceDAO() {
        return typeDeviceDAO;
    }

    public void setTypeDeviceDAO(TypeDeviceDAO typeDeviceDAO) {
        this.typeDeviceDAO = typeDeviceDAO;
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

    public int getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(int typeInt) {
        this.typeInt = typeInt;
    }

    public List<TblTypeDeviceEntity> getTblTypeDeviceEntitysList() {
        return tblTypeDeviceEntitysList;
    }

    public void setTblTypeDeviceEntitysList(List<TblTypeDeviceEntity> tblTypeDeviceEntitysList) {
        this.tblTypeDeviceEntitysList = tblTypeDeviceEntitysList;
    }

}
