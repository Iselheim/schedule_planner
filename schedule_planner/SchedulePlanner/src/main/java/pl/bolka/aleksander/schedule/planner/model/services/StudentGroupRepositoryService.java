package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;
import pl.bolka.aleksander.schedule.planner.model.filter.StudentGroupFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.StudentGroupSpecification;

import java.util.List;

/**
 * Created by Aleksander on 2016-09-04.
 */
@Service
public class StudentGroupRepositoryService extends AbstractRepositoryService<StudentGroup, StudentGroupFilter> {

    @Override
    public List<StudentGroup> findAll(StudentGroupFilter filter) {
        StudentGroupSpecification spec = new StudentGroupSpecification(filter);
        return commonCustomRepository.findAll(spec);
    }

    @Override
    public StudentGroup findOne(StudentGroupFilter filter) {
        StudentGroupSpecification spec = new StudentGroupSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }
}
