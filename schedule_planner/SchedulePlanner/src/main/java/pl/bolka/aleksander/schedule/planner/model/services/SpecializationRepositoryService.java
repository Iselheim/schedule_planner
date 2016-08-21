package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;
import pl.bolka.aleksander.schedule.planner.model.filter.SpecializationFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SpecializationSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
@Service
public class SpecializationRepositoryService extends AbstractRepositoryService<Specialization, SpecializationFilter> {
    @Override
    public List<Specialization> findAll(SpecializationFilter filter) {
        SpecializationSpecification spec = new SpecializationSpecification(filter);
        return commonCustomRepository.findAll(spec);
    }

    public Specialization findOne(SpecializationFilter filter) {
        SpecializationSpecification spec = new SpecializationSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }

}
