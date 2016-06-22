package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
public class CommonCustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonCustomRepository<T, ID> {
    public CommonCustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public CommonCustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                      EntityManager entityManager) {
        super(entityInformation, entityManager);
    }


    @Override
    public String getFirstName() {
        return "Kupa Dupa sialala";
    }
}
