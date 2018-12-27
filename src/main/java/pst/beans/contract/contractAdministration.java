package pst.beans.contract;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


@ManagedBean (name="contractAdministration")
@ViewScoped
public class contractAdministration extends LazyDataModel<TblContractEntity> {

    private TblContractEntity tblContractEntity;
    private List<contractAdministration> contractAdministrations;
    TblDeviceEntity deviceEntity;

    @EJB
    private ContractDAO contractDAO;

    @EJB
    private DeviceDAO deviceDAO;

    @Default
    private int idContract;
    private String contract;
    private Date date;
    private String description;
    private String number;
    private List<TblDeviceEntity> deviceEntityList;

    public TblContractEntity selectedContract;


    @PostConstruct
    void start(){
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
//        String param = parameterMap.get("idContract");
//        if (param != null) {
//            idContract = Integer.parseInt(param);
//            this.tblContractEntity = contractDAO.read(idContract);
//            if (tblContractEntity == null) {
//                String message = "Bad request. Unknown task.";
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
//            }
//            load(tblContractEntity);
//        } else {
//            this.contract= "";
//            this.date=null;
//            this.description="";
//            this.number="";
//        }
//        tblContractEntitysList = contractDAO.findAll();
//        deviceEntityList = deviceDAO.findAll();
    }


    public void load(TblContractEntity contractEntity) {
        this.setIdContract(contractEntity.getIdContract());
        this.setContract(contractEntity.getContract());
        this.setDeviceEntity(contractEntity.getDeviceEntity());
        this.setDate(timestampFromDate(contractEntity.getDate()));
        this.setDescription(contractEntity.getDescription());
        this.setNumber(contractEntity.getNumber());
    }

    public String save() {
        tblContractEntity = contractDAO.find(this.idContract);
        if (tblContractEntity == null) {
            tblContractEntity = new TblContractEntity();
        }
        tblContractEntity.setContract(this.contract);
        tblContractEntity.setDeviceEntity(deviceEntity);
        tblContractEntity.setDate(timestampFromDate(this.date));
        tblContractEntity.setDescription(this.description);
        tblContractEntity.setNumber(this.number);
        if (tblContractEntity.getIdContract() == 0) {
            contractDAO.create(tblContractEntity);
        } else {
            contractDAO.update(tblContractEntity);
        }
        return "listContract.xhtml?faces-redirect=true&id=" + String.valueOf(tblContractEntity.getIdContract());
    }




    public Timestamp timestampFromDate(Date fromDate) {
        return new Timestamp(fromDate.getTime());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



    @Override
    public List<TblContractEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<TblContractEntity> tblContractEntitysList;

//        filters.put("first", first);
//        filters.put("pageSize", pageSize);


        //


//        filters.put("statusExecute", 1);
//        filters.put("deviceId", device.getId());

//        tblContractEntitysList = contractDAO.findAll();

        setRowCount(contractDAO.getTotalCount(filters));
        tblContractEntitysList = contractDAO.load(first, pageSize, sortField, sortOrder, filters);
        if (tblContractEntitysList.size() == 0) {
            return null;
        }

        return tblContractEntitysList;
    }




}
