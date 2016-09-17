package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;
import pl.bolka.aleksander.schedule.planner.model.filter.ScheduleFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.ScheduleSpecification;

import java.util.List;

/**
 * Created by Aleksander on 2016-09-12.
 */
@Service
public class ScheduleRepositoryService extends AbstractRepositoryService<Schedule, ScheduleFilter> {

    @Override
    public List<Schedule> findAll(ScheduleFilter filter) {
        ScheduleSpecification scheduleSpecification = new ScheduleSpecification(filter);
        return commonCustomRepository.findAll(scheduleSpecification);
    }

    @Override
    public Schedule findOne(ScheduleFilter filter) {
        ScheduleSpecification scheduleSpecification = new ScheduleSpecification(filter);
        return commonCustomRepository.findOne(scheduleSpecification);
    }


}
