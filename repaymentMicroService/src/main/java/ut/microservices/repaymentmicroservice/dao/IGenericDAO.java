package ut.microservices.repaymentmicroservice.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IGenericDAO<T extends Serializable> {

    void setClazz(Class<T> entity);
    List<T> findAll();
    void save(T entity);
    void update(final T entity);
    void delete(final T entity);
    List<T> findByApplicantId(String applicantID);
    List<T> findByTwoColumns(String column1,String value1,String column2,String value2);
    List<T> findByVANumber(String VaNumber);
    List<T> findActiveVAByApplicantID(String ApplicantID);
    List<T> findVAInLogs(String VaNumber);
    List<T> findValueByColumn(String column, String value);
    List<T> findInstallmentRepayment(Integer value);
    List<T> findInMrLoanScheme(Double loanAmount, Date loanStartDateTime, Integer MlsLoanIncrement);
    // List<T> findByJoin();    
 }