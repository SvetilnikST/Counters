package pst.beans.qBox.System;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class BoxSystemDAO {

    @PersistenceContext(name = "counters-app")
    private EntityManager entityManager;

    public boolean create(TblBoxSystemEntity boxSystemEntity) {
        entityManager.persist(boxSystemEntity);
        return true;
    }
}
