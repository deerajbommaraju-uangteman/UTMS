package ut.microservices.loanApplicationMicroService.repository;

import java.io.Serializable;
import java.util.List;

import com.mysql.cj.util.DataTypeUtil;

import org.hibernate.Session;

import ut.microservices.loanApplicationMicroService.model.*;

public interface IGenericDao<T extends Serializable> {

    T update(final T entity);
    void updateOne(final T entity);
    //void delete(final T entity);
    Serializable save(T entity);
    T findOne(int ID);
    void setClazz(Class< T > clazzToSet);
    List<T> findValueByColumn(String columnName, String value);
    //void setClazz(Class<T> class1);
    // List<T> findBy(T entity);
    // List<T> findByLoanState(String state);
    // void updateLoanState(String loanAppID,String state);
    // List<T> findByLoanAppID(String loanAppID);
    // List<T> findVANumberByInvestorID(Integer investorID);
 }