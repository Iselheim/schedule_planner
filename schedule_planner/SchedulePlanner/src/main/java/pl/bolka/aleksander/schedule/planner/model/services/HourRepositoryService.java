package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.filter.HourFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.HourSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
@Service
public class HourRepositoryService extends AbstractRepositoryService<Hour, HourFilter> {

    @Override
    public List<Hour> findAll(HourFilter filter) {
        HourSpecification specification = new HourSpecification(filter);
        return commonCustomRepository.findAll(specification);
    }

    @Override
    public Hour findOne(HourFilter filter){
        HourSpecification spec = new HourSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }
}
