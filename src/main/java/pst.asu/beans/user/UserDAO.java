package pst.asu.beans.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;


@LocalBean
@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "counters-app")
    private EntityManager entityManager;

    //чтение записи по id
    public UserEntity read(int id){
        return entityManager.find(UserEntity.class, id);
    }

    //чтение записи по логину
    public UserEntity readLogin(String login){
        if (login.isEmpty()){
            return null;
        }
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "select entity from  UserEntity entity where entity.username=:login",
                UserEntity.class).setParameter("login",login);
        UserEntity userEntity= query.getSingleResult();
        return userEntity;
    }

    public int TotalCount(){
        Long rez = entityManager.createQuery(
                "select count (entity.id) from UserEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }

    public boolean create(UserEntity userEntity){
        UserEntity existingUser = entityManager.find(UserEntity.class, userEntity.getId());
        long update = new Date().getTime();
        userEntity.setCreated_at((int)(update/1000));
        userEntity.setUpdated_at((int)(update/1000));
        entityManager.merge(userEntity);
        return true;
    }

}
