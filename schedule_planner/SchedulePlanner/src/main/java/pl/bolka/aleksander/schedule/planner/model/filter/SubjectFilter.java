package pl.bolka.aleksander.schedule.planner.model.filter;

import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SubjectFilter implements Filter{

    private Lecturer lecturer;
    private Semester semester;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Semester getSemester() {
        return semester;
    }
}
