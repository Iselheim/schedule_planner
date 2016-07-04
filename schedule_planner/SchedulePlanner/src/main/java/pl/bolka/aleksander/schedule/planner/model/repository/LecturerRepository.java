package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
@Repository
public interface LecturerRepository extends CommonCustomRepository<Lecturer, Long> {
}