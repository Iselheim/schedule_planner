package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.bolka.aleksander.schedule.planner.model.entity.Identifiable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
// Add here methods for all repos
@NoRepositoryBean
public interface CommonCustomRepository<T extends Identifiable, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findAll(Specification specification);

    T findOne(Specification specification);

    T getEager(T t);

    EntityManager getEntityManager();

}