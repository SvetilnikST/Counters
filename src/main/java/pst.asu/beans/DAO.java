package pst.asu.beans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAO {
    @PersistenceContext(unitName = "counters-app")
    public EntityManager entityManager;
}
