package pst.beans.object;

import org.primefaces.model.SortOrder;
import pst.beans.AbstractDao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class ObjectDAO extends AbstractDao<TblObjectEntity>{
    public ObjectDAO(){
        super(TblObjectEntity.class);
    }

    public int getTotalCount(Map<String, Object> filters) {
        return 1000;
    }

    public List<TblObjectEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        String sql = "select * from " + getTableName() + " entity WHERE 1 ";
        String nameObject = (String) filters.get("nameObject");


        if (nameObject != null) {
            sql += " and nameObject = " + nameObject + " ";
        }

        if (sortField != null) {
            sql += " order by " + sortField + " "
                    + (sortOrder.equals(SortOrder.ASCENDING) ?
                    "ASC" :
                    "DESC");
        } else {
        }

        List<TblObjectEntity> rez = entityManager.createNativeQuery(sql, TblObjectEntity.class)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
        return rez;

    }

    //специально, чтобы получать имя таблицы из сущности, иначе возможны ошибки с именами таблиц
    private String getTableName() {
        Table table = TblObjectEntity.class.getAnnotation(Table.class);
        return table.name();
    }
}
