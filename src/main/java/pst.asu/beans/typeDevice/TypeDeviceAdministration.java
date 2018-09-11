package pst.asu.beans.typeDevice;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "typeDeviceAdministration")
public class TypeDeviceAdministration {

    private TblTypeDeviceEntity typeDeviceEntity;


    private List<TypeDeviceAdministration> typeDeviceAdministrations;

    @EJB
    private TypeDeviceDAO typeDeviceDAO;

    @Default
    private int id;
    private String name;

    public TblTypeDeviceEntity getTypeDeviceEntity() {
        return typeDeviceEntity;
    }

    public void setTypeDeviceEntity(TblTypeDeviceEntity typeDeviceEntity) {
        this.typeDeviceEntity = typeDeviceEntity;
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
}
