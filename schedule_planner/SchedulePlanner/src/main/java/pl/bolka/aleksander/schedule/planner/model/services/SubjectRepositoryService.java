package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SubjectSpecyfication;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public class SubjectRepositoryService extends AbstractRepositoryService<Subject, SubjectFilter> {

    @Transactional
    public List<Subject> findAll(SubjectFilter subjectFilter){
        SubjectSpecyfication spec = new SubjectSpecyfication(subjectFilter);
        return commonCustomRepository.findAll(spec);
    }

    @Override
    public Subject findOne(SubjectFilter filter) {
        SubjectSpecyfication spec = new SubjectSpecyfication(filter);
        return commonCustomRepository.findOne(spec);
    }


}
