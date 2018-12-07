package pst.beans.verification;

import pst.beans.AbstractDao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class VerificationDAO extends AbstractDao<TblVerificationEntity> {
    public VerificationDAO(){
        super(TblVerificationEntity.class);
    }
}
