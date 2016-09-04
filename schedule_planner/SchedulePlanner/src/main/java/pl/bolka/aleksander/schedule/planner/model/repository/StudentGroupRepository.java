package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;

/**
 * Created by Aleksander on 2016-09-04.
 */
@Repository
public interface StudentGroupRepository extends CommonCustomRepository<StudentGroup,Long> {
}
