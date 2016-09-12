package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;

/**
 * Created by Aleksander on 2016-09-12.
 */
@Repository
public interface ScheduleRepository extends CommonCustomRepository<Schedule,Long> {
}
