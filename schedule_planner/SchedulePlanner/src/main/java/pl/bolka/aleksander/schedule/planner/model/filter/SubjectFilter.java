package pl.bolka.aleksander.schedule.planner.model.filter;

import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SubjectFilter implements Filter{

    private Lecturer lecturer;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
