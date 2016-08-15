package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;
import pl.bolka.aleksander.schedule.planner.model.filter.FacultyFilter;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public class FacultyRepositoryService extends AbstractRepositoryService<Faculty, FacultyFilter> {

    @Override
    public List<Faculty> findAll(FacultyFilter filter) {
        return null;
    }

    @Override
    public Faculty findOne(FacultyFilter filter) {
        return null;
    }
}
