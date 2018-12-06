package pst.beans.contract;

import pst.beans.AbstractDao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ContractDAO extends AbstractDao<TblContractEntity> {

    public ContractDAO(){
        super(TblContractEntity.class);
    }
}
