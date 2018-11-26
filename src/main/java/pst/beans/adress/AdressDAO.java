package pst.beans.adress;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class AdressDAO extends AbstractDao<TblAdressEntity>{
    public AdressDAO(){
        super(TblAdressEntity.class);
    }
}
