package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SubjectSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public class SubjectRepositoryService extends AbstractRepositoryService<Subject, SubjectFilter> {

    @Transactional
    public List<Subject> findAll(SubjectFilter subjectFilter){
        SubjectSpecification spec = new SubjectSpecification(subjectFilter);
        return commonCustomRepository.findAll(spec);
    }

    @Override
    public Subject findOne(SubjectFilter filter) {
        SubjectSpecification spec = new SubjectSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }

    //TODO cos generycznego to jest do kitu
    @Transactional
    public Subject getRoomFromSubject(Subject subject){
        commonCustomRepository.getOne(subject.getId());
        subject.getRoom().size();
        return subject;
    }

}
