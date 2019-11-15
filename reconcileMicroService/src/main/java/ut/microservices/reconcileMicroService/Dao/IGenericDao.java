package ut.microservices.reconcileMicroService.Dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
	void setClazz( Class< T > clazzToSet);
    void save(T entity);
    void delete(final T entity);
    int findCountByColumn(String Column,String Value);
    List<T> findByColumn(String Column,String Value);
    List<T> findLatestRecord();
 }