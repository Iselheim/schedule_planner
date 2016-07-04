package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.filter.DayFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.DaySpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
@Service
public class DayRepositoryService extends AbstractRepositoryService<Day, DayFilter> {
    @Override
    public List<Day> findAll(DayFilter filter) {
        DaySpecification daySpecification = new DaySpecification(filter);
        return commonCustomRepository.findAll(daySpecification);
    }

    public Day findOne(DayFilter filter) {
        DaySpecification daySpecification = new DaySpecification(filter);
        return commonCustomRepository.findOne(daySpecification);
    }

}
