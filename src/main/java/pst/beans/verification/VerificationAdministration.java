package pst.beans.verification;

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

@ManagedBean (name="verificationAdministration")
@ViewScoped
public class VerificationAdministration implements Serializable {

    private TblVerificationEntity tblVerificationEntity;
    private List<VerificationAdministration> verificationAdministrations;

    @EJB
    private VerificationDAO verificationDAO;

    @Default
    private int idVerification;
    private int PPR;
    private int IVB;
    private int TCP;
    private int additionalDevice;

    private List<TblVerificationEntity> tblVerificationEntitysList;

    @PostConstruct
    void start(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("id");
        if (param != null) {
            idVerification = Integer.parseInt(param);
            this.tblVerificationEntity = verificationDAO.read(idVerification);
            if (tblVerificationEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblVerificationEntity);

        } else {
            this.PPR = 0;
            this.IVB=0;
            this.TCP=0;
            this.additionalDevice=0;
        }
        tblVerificationEntitysList = verificationDAO.findAll();
    }

    public void load(TblVerificationEntity verificationEntity) {
        this.setIdVerification(verificationEntity.getIdVerification());
        this.setPPR(verificationEntity.getPPR());
        this.setIVB(verificationEntity.getIVB());
        this.setTCP(verificationEntity.getTCP());
        this.setAdditionalDevice(verificationEntity.getAdditionalDevice());

    }

    public String save() {
        tblVerificationEntity = verificationDAO.find(this.idVerification);
        if (tblVerificationEntity == null) {
            tblVerificationEntity = new TblVerificationEntity();
        }
        tblVerificationEntity.setPPR(this.PPR);
        tblVerificationEntity.setIVB(this.IVB);
        tblVerificationEntity.setTCP(this.TCP);
        tblVerificationEntity.setAdditionalDevice(this.additionalDevice);
        if (tblVerificationEntity.getIdVerification() == 0) {

            verificationDAO.create(tblVerificationEntity);
        } else {
            verificationDAO.update(tblVerificationEntity);
        }
        return "listUnitQ.xhtml?faces-redirect=true&id=" + String.valueOf(tblVerificationEntity.getIdVerification());
    }


    public TblVerificationEntity getTblVerificationEntity() {
        return tblVerificationEntity;
    }

    public void setTblVerificationEntity(TblVerificationEntity tblVerificationEntity) {
        this.tblVerificationEntity = tblVerificationEntity;
    }

    public List<VerificationAdministration> getVerificationAdministrations() {
        return verificationAdministrations;
    }

    public void setVerificationAdministrations(List<VerificationAdministration> verificationAdministrations) {
        this.verificationAdministrations = verificationAdministrations;
    }

    public VerificationDAO getVerificationDAO() {
        return verificationDAO;
    }

    public void setVerificationDAO(VerificationDAO verificationDAO) {
        this.verificationDAO = verificationDAO;
    }

    public int getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(int idVerification) {
        this.idVerification = idVerification;
    }

    public int getPPR() {
        return PPR;
    }

    public void setPPR(int PPR) {
        this.PPR = PPR;
    }

    public int getIVB() {
        return IVB;
    }

    public void setIVB(int IVB) {
        this.IVB = IVB;
    }

    public int getTCP() {
        return TCP;
    }

    public void setTCP(int TCP) {
        this.TCP = TCP;
    }

    public int getAdditionalDevice() {
        return additionalDevice;
    }

    public void setAdditionalDevice(int additionalDevice) {
        this.additionalDevice = additionalDevice;
    }

    public List<TblVerificationEntity> getTblVerificationEntitysList() {
        return tblVerificationEntitysList;
    }

    public void setTblVerificationEntitysList(List<TblVerificationEntity> tblVerificationEntitysList) {
        this.tblVerificationEntitysList = tblVerificationEntitysList;
    }
}
