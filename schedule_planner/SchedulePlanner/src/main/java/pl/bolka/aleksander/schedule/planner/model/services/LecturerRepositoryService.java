package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.filter.LecturerFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.LecturerSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */

@Service
public class LecturerRepositoryService extends AbstractRepositoryService<Lecturer, LecturerFilter>{

    @Override
    public List<Lecturer> findAll(LecturerFilter filter) {
        LecturerSpecification spec = new LecturerSpecification(filter);
        return commonCustomRepository.findAll(spec);
    }

    @Override
    public Lecturer findOne(LecturerFilter filter) {
        LecturerSpecification spec = new LecturerSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }
}
