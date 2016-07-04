package pl.bolka.aleksander.schedule.planner.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
public class CustomLecturerRepositoryImpl implements CustomLecturerRepository {

    @PersistenceContext
    private EntityManager em;

}
