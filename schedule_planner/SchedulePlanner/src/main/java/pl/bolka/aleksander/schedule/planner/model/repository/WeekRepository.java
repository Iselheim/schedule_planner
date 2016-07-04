package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Week;

/**
 * Created by Aleksander Bołka on 2016-07-04.
 */
@Repository
public interface WeekRepository extends CommonCustomRepository<Week, Long> {
}
