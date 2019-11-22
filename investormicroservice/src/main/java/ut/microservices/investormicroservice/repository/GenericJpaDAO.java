package ut.microservices.investormicroservice.repository;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


import org.springframework.beans.factory.config.BeanDefinition;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDAO<T extends Serializable> extends AbstractJpaDAO<T> implements IGenericDAO<T> {
}