package pst.asu.beans.qBox;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
