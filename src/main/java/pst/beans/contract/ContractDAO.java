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
//        String sql1 = "";

        String number = (String) filters.get("number");
        String contract = (String) filters.get("contract");

        if (number != null) {
            sql += " and number = " + number + " ";
        }

        if (contract != null) {
            sql += " and contract = " + contract + " ";
        }


//        if (!sql1.isEmpty()) {
//            sql = sql + "and" + sql1;
//        } else {
//            sql = sql + sql1;
//        }


        if (sortField != null) {
            sql += " order by " + sortField + " "
                    + (sortOrder.equals(SortOrder.ASCENDING) ?
                    "ASC" :
                    "DESC");
        } else {
//            sql += " order by id DESC";
        }

        List<TblContractEntity> rez = entityManager.createNativeQuery(sql, TblContractEntity.class)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();


//        TypedQuery<TblContractEntity> query = entityManager.createQuery(
//                "select entity from TblContractEntity  entity  order by entity.:field :sortOrder ",
//                TblContractEntity.class)
//                .setParameter("field",sortField)
//                .setParameter("sortOrder",sortOrder.equals(SortOrder.ASCENDING) ?"ASC" : "DESC")
//                .setFirstResult(first)
//                .setMaxResults(pageSize);
//
//        List<TblContractEntity> rez =query.getResultList();


//
//
//        String sql = "select * from "+getTableName()+" entity where status&:statusFilter and " +
//                "(completeness IS NULL " +
//                "or completeness between :dateFirst AND :dateLast) ";
//
//        String statusFilter = (String) filters.get("status");
//        String dateFirstFilter = (String) filters.get("dateFrom");
//        String dateLastFilter = (String) filters.get("dateLast");
//        String departmentFilter = (String) filters.get("department");
//
//        if (departmentFilter != null) {
//            sql += " and department = "+ departmentFilter + " ";
//        }
//
//        if (sortField != null) {
//            sql += " order by " + sortField + " "
//                    + (sortOrder.equals(SortOrder.ASCENDING) ?
//                    "ASC" :
//                    "DESC");
//        }else{
//            sql += " order by id DESC";
//        }
//
//        List<OrderEntity> rez = entityManager.createNativeQuery(sql ,OrderEntity.class)
//                .setParameter("statusFilter",statusFilter)
//                .setParameter("dateFirst",dateFirstFilter)
//                .setParameter("dateLast",dateLastFilter)
//                .setFirstResult(first)
//                .setMaxResults(pageSize)
//                .getResultList();
//
//
//
//


        return rez;

    }

    //специально, чтобы получать имя таблицы из сущности, иначе возможны ошибки с именами таблиц
    private String getTableName() {
        Table table = TblContractEntity.class.getAnnotation(Table.class);
        return table.name();
    }
}
