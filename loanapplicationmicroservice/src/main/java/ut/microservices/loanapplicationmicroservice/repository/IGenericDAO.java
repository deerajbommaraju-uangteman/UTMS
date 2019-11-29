package ut.microservices.loanapplicationmicroservice.repository;

import java.io.Serializable;
import java.util.List;

import com.mysql.cj.util.DataTypeUtil;

import org.hibernate.Session;

import ut.microservices.loanapplicationmicroservice.model.*;

public interface IGenericDAO<T extends Serializable> {

    T update(final T entity);
    void updateOne(final T entity);
    Serializable save(T entity);
    T findOne(int ID);
    void setClazz(Class< T > clazzToSet);
    List<T> findValueByColumn(String columnName, String value);
    List<T> findValueByColumnOrder(String column, String value,String sortedColumn, String orderType);
    T findValueByColumnSingleEntity(String column, String value);    
}