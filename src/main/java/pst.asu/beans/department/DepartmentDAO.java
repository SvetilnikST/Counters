package pst.asu.beans.department;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
@Stateless
public class DepartmentDAO  {

    @PersistenceContext(unitName = "counters-app")
    private EntityManager entityManager;

    //чтение по id
    public TblDepartmentEntity read(int id) {
        return entityManager.find(TblDepartmentEntity.class, id);
    }

    //чтение листа значений таблицы
    public List<TblDepartmentEntity> readList() {
        TypedQuery<TblDepartmentEntity> query = entityManager.createQuery(
                "from TblDepartmentEntity entity", TblDepartmentEntity.class);
        List<TblDepartmentEntity> tblDepartmentEntities = query.getResultList();
        return tblDepartmentEntities;
    }

    //подсчет колличества записей в таблице
    public int getTotalCount(){
        Long rez = entityManager.createQuery(
                "select count (entity.idDepartment) from TblDepartmentEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }

}
