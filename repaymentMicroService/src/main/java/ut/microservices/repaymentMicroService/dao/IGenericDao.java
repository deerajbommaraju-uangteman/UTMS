package ut.microservices.repaymentmicroservice.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T extends Serializable> {

    void setClazz(Class<T> entity);
    void save(T entity);
    void update(final T entity);
    void delete(final T entity);
    List<T> findByApplicantId(String ApplicantID);
    List<T> findByVANumber(String VaNumber);
    List<T> findVAInLogs(String VaNumber);
    List<T> findValueByColumn(String column, String value);
 }