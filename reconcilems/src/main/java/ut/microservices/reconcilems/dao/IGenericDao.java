package ut.microservices.reconcilems.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
	void setClazz( Class< T > clazzToSet);
    void save(T entity);
    void delete(final T entity);
    int findCountByColumn(String Column,String Value);
    List<T> findByColumn(String Column,String Value);
    List<T> findLatestRecord();
    List<T> findByTwoColumns(String Column1,String Value1, String Column2,String Value2);
 }