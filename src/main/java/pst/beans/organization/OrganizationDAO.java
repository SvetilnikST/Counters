package pst.beans.organization;

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
public class OrganizationDAO extends AbstractDao<TblOrganizationEntity>{
    public OrganizationDAO(){
        super(TblOrganizationEntity.class);
    }

    public int getTotalCount(Map<String, Object> filters) {
        return 1000;
    }


    public List<TblOrganizationEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        String sql = "select * from " + getTableName() + " entity WHERE 1 ";
        String UNP = (String) filters.get("UNP");


        if (UNP != null) {
            sql += " and UNP = " + UNP + " ";
        }


        if (sortField != null) {
            sql += " order by " + sortField + " "
                    + (sortOrder.equals(SortOrder.ASCENDING) ?
                    "ASC" :
                    "DESC");
        } else {
        }

        List<TblOrganizationEntity> rez = entityManager.createNativeQuery(sql, TblOrganizationEntity.class)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
        return rez;

    }

    //специально, чтобы получать имя таблицы из сущности, иначе возможны ошибки с именами таблиц
    private String getTableName() {
        Table table = TblOrganizationEntity.class.getAnnotation(Table.class);
        return table.name();
    }


}
