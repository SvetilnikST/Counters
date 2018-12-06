package pst.beans.contract;

import pst.beans.device.DeviceDAO;
import pst.beans.device.TblDeviceEntity;

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

@ManagedBean (name="contractAdministration")
@ViewScoped
public class contractAdministration implements Serializable {

    private TblContractEntity tblContractEntity;
    private List<contractAdministration> contractAdministrations;
    private List<TblContractEntity> tblContractEntitysList;
    TblDeviceEntity deviceEntity;

    @EJB
    private ContractDAO contractDAO;

    @EJB
    private DeviceDAO deviceDAO;



    @Default
    private int idContract;
    private String contract;
    private List<TblDeviceEntity> deviceEntityList;



    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idContract");
        if (param != null) {
            idContract = Integer.parseInt(param);
            this.tblContractEntity = contractDAO.read(idContract);
            if (tblContractEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblContractEntity);

        } else {
            this.contract= "";
        }
        tblContractEntitysList = contractDAO.findAll();
        deviceEntityList = deviceDAO.findAll();
    }


    public void load(TblContractEntity contractEntity) {
        this.setIdContract(contractEntity.getIdContract());
        this.setContract(contractEntity.getContract());
        this.setDeviceEntity(contractEntity.getDeviceEntity());
    }

    public String save() {
        tblContractEntity = contractDAO.find(this.idContract);
        if (tblContractEntity == null) {
            tblContractEntity = new TblContractEntity();
        }
        tblContractEntity.setContract(this.contract);
        tblContractEntity.setDeviceEntity(deviceEntity);
        if (tblContractEntity.getIdContract() == 0) {

            contractDAO.create(tblContractEntity);
        } else {
            contractDAO.update(tblContractEntity);
        }
        return "listContract.xhtml?faces-redirect=true&id=" + String.valueOf(tblContractEntity.getIdContract());
    }

    public TblContractEntity getTblContractEntity() {
        return tblContractEntity;
    }

    public void setTblContractEntity(TblContractEntity tblContractEntity) {
        this.tblContractEntity = tblContractEntity;
    }

    public List<contractAdministration> getContractAdministrations() {
        return contractAdministrations;
    }

    public void setContractAdministrations(List<contractAdministration> contractAdministrations) {
        this.contractAdministrations = contractAdministrations;
    }

    public List<TblContractEntity> getTblContractEntitysList() {
        return tblContractEntitysList;
    }

    public void setTblContractEntitysList(List<TblContractEntity> tblContractEntitysList) {
        this.tblContractEntitysList = tblContractEntitysList;
    }

    public ContractDAO getContractDAO() {
        return contractDAO;
    }

    public void setContractDAO(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public TblDeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(TblDeviceEntity deviceEntity) {
        this.deviceEntity = deviceEntity;
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<TblDeviceEntity> getDeviceEntityList() {
        return deviceEntityList;
    }

    public void setDeviceEntityList(List<TblDeviceEntity> deviceEntityList) {
        this.deviceEntityList = deviceEntityList;
    }

}
