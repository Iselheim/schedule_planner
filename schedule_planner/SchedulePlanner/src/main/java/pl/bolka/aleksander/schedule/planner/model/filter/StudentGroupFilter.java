package pl.bolka.aleksander.schedule.planner.model.filter;

import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

/**
 * Created by Aleksander on 2016-09-04.
 */
public class StudentGroupFilter implements Filter{

    private Semester semester;

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
