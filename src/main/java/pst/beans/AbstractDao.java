package pst.beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;

/**
 * Abstract Data-Access Object class to be implemented by all DAO's.
 */
public abstract class AbstractDao<T> {
    protected Class<T> entityClass;

    @PersistenceContext(unitName = "counters-app")
    private EntityManager entityManager;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * Retrieves the meta-model for a certain entity.
     *
     * @return the meta-model of a certain entity.
     */
    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    @Transactional
    public void create(T entity) {
        getEntityManager().persist(entity);
    }



    @Transactional
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Transactional
    public void remove(Long entityId) {
        T entity = find(entityId);

        if (entity != null)
            remove(entity);
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public T read(int id){
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        return getEntityManager().createQuery(cq).getResultList();
    }

    public T readLogin(String login){
        if(login.isEmpty()){
            return null;
        }
        return  getEntityManager().find(entityClass, login);
    }


//    чтение записи по логину
//    public UserEntity readLogin(String login) {
//        if (login.isEmpty()) {
//            return null;
//        }
//        TypedQuery<UserEntity> query = entityManager.createQuery(
//                "select entity from  UserEntity entity where entity.username=:login",
//                UserEntity.class).setParameter("login", login);
//        UserEntity userEntity = query.getSingleResult();
//        return userEntity;
//    }



    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        Long count = getEntityManager().createQuery(cq).getSingleResult();

        return count.intValue();
    }

}