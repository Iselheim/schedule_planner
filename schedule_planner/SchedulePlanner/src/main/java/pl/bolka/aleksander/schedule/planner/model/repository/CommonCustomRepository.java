package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
// Add here methods for all repos
@NoRepositoryBean
public interface CommonCustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    // przykładowa metoda
    String getFirstName();
}