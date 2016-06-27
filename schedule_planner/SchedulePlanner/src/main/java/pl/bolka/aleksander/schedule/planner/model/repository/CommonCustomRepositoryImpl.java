package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

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
    public List<T> findAll(Specification specification) {
        return super.findAll(specification);
    }

    @Override
    public String getFirstName() {
        return "Kupa Dupa sialala";
    }
}
