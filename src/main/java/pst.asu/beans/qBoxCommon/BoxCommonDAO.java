package pst.asu.beans.qBoxCommon;

import pst.asu.beans.qBoxSystem.TblBoxSystemEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class BoxCommonDAO {

    @PersistenceContext(unitName = "counters-app")
    private EntityManager entityManager;

    public boolean create(TblBoxCommonEntity boxCommonEntity){
        entityManager.persist(boxCommonEntity);
        return true;
    }

}
