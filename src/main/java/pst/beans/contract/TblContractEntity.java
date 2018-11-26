package pst.beans.contract;

import javax.persistence.*;

@Entity
@Table(name = "tblContract")
public class TblContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContract")
    private int idContract;

    @Column(name = "contract")
    private String contract;


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
}
