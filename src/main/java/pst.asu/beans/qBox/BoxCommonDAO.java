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

    public TblBoxCommonEntity read(long id){
        return entityManager.find(TblBoxCommonEntity.class,id);
    }

    public List<TblBoxCommonEntity> readBoxCommonList(){
        TypedQuery<TblBoxCommonEntity> query = entityManager.createQuery(
                "from TblBoxCommonEntity entity",
                TblBoxCommonEntity.class);
        List<TblBoxCommonEntity> tblBoxCommonEntities = query.getResultList();
        return tblBoxCommonEntities;
    }

    public boolean create(TblBoxCommonEntity tblBoxCommonEntity){
        TblBoxCommonEntity existingUser = entityManager.find(TblBoxCommonEntity.class, tblBoxCommonEntity.getId());
        entityManager.persist(tblBoxCommonEntity);
        return true;
    }

    public int getTotalCount() {
        Long rez = entityManager.createQuery(
                "select count (entity.id) from TblBoxCommonEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }
}
