package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

/**
 * Created by Aleksander Bołka on 2016-07-02.
 */
@Repository
public interface SemesterRepository extends CommonCustomRepository<Semester, Long> {
}
