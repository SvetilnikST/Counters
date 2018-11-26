package pst.beans.city;

import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class CityDAO extends AbstractDao<TblCityEntity> {

    public CityDAO(){
        super(TblCityEntity.class);
    }
}
