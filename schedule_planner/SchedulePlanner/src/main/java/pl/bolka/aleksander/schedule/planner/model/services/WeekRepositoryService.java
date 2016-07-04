package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Week;
import pl.bolka.aleksander.schedule.planner.model.filter.WeekFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.WeekSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-04.
 */
@Service
public class WeekRepositoryService extends AbstractRepositoryService<Week, WeekFilter> {

    @Override
    public List<Week> findAll(WeekFilter filter) {
        WeekSpecification specification = new WeekSpecification(filter);
        return commonCustomRepository.findAll(specification);
    }
}
