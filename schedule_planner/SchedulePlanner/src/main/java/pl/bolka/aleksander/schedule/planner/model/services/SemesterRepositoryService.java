package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.filter.SemesterFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SemesterSpecification;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SubjectSpecyfication;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-02.
 */
@Service
public class SemesterRepositoryService extends AbstractRepositoryService<Semester, SemesterFilter> {

    @Override
    public List<Semester> findAll(SemesterFilter filter) {
        SemesterSpecification spec = new SemesterSpecification(filter);
        return commonCustomRepository.findAll(spec);
    }

    public Semester findOne(SemesterFilter filter){
        SemesterSpecification spec = new SemesterSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }
}
