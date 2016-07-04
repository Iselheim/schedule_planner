package pl.bolka.aleksander.schedule.planner.model.services;

import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.filter.SemesterFilter;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-02.
 */
public class SemesterRepositoryService extends AbstractRepositoryService<Semester, SemesterFilter> {


    @Override
    public List<Semester> findAll(SemesterFilter filter) {
        return null;
    }
}
