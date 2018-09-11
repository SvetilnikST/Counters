package pst.beans.user;

import pst.beans.roles.RolesEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;


@LocalBean
@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "counters-app")
    private EntityManager entityManager;

    //чтение записи по id
    public UserEntity read(int id) {
        return entityManager.find(UserEntity.class, id);
    }

    //чтение записи по логину
    public UserEntity readLogin(String login) {
        if (login.isEmpty()) {
            return null;
        }
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "select entity from  UserEntity entity where entity.username=:login",
                UserEntity.class).setParameter("login", login);
        UserEntity userEntity = query.getSingleResult();
        return userEntity;
    }

    public List<RolesEntity> readRolesList() {
        TypedQuery<RolesEntity> query = entityManager.createQuery(
                "select entity from RolesEntity entity",
                RolesEntity.class);
        List<RolesEntity> rolesEntities = query.getResultList();
        return rolesEntities;
    }

    public List<UserEntity> readUserList(){
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "select entity from UserEntity entity",
                UserEntity.class
        );
        List<UserEntity> userEntities=query.getResultList();
        return userEntities;
    }

    public int TotalCount() {
        Long rez = entityManager.createQuery(
                "select count (entity.id) from UserEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }

    public boolean create(UserEntity userEntity) {
        UserEntity existingUser = entityManager.find(UserEntity.class, userEntity.getId());
        long update = new Date().getTime();
        userEntity.setCreated_at((int) (update / 1000));
        userEntity.setUpdated_at((int) (update / 1000));
        entityManager.merge(userEntity);
        return true;
    }

    public boolean update(UserEntity userEntity){
        UserEntity existingUser = entityManager.find(UserEntity.class, userEntity.getId());
        long update = new Date().getTime();
        userEntity.setUpdated_at((int)(update/1000));
        entityManager.merge(userEntity);
        return true;
    }

    public boolean delete(int id){
        UserEntity existingUser = entityManager.find(UserEntity.class, id);
        if (existingUser==null){
            return false;
        }
        entityManager.remove(existingUser);
        return true;
    }
}
