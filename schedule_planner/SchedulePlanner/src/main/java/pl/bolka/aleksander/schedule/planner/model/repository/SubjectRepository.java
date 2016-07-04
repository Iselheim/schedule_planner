package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Repository
public interface SubjectRepository extends CommonCustomRepository<Subject, Long> {
}
