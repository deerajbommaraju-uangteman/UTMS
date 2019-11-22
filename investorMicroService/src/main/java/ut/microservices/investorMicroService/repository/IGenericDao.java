package ut.microservices.investormicroservice.repository;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T extends Serializable> {
 
    void update(final T entity);
    void delete(final T entity);
    void save(T entity);
    List<T> findBy(String column,String value);
    List<T> findBy(String column1,String value1,String column2,String value2);
    void setClazz(Class<T> class1);
    public List<T> findAll();
 }