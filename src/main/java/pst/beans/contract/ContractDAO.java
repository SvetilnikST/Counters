package pst.beans.contract;

import org.primefaces.model.SortOrder;
import pst.beans.AbstractDao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class ContractDAO extends AbstractDao<TblContractEntity> {

    public ContractDAO() {
        super(TblContractEntity.class);
    }

    public int getTotalCount(Map<String, Object> filters) {
        return 1000;
    }

    public List<TblContractEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        EntityManager entityManager = getEntityManager();

        String sql = "select * from " + getTableName() + " entity WHERE 1 ";
        String number = (String) filters.get("number");
        String contract = (String) filters.get("contract");

        if (number != null) {
            sql += " and number = " + number + " ";
        }

        if (contract != null) {
            sql += " and contract like '%" + contract + "%' ";
        }

        if (sortField != null) {
            sql += " order by " + sortField + " "
                    + (sortOrder.equals(SortOrder.ASCENDING) ?
                    "ASC" :
                    "DESC");
        } else {
        }

        List<TblContractEntity> rez = entityManager.createNativeQuery(sql, TblContractEntity.class)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
        return rez;

    }

    //специально, чтобы получать имя таблицы из сущности, иначе возможны ошибки с именами таблиц
    private String getTableName() {
        Table table = TblContractEntity.class.getAnnotation(Table.class);
        return table.name();
    }
}
