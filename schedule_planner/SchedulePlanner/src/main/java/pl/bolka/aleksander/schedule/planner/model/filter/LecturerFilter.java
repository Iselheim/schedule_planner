package pl.bolka.aleksander.schedule.planner.model.filter;

import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class LecturerFilter implements Filter {

    private Subject subject;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
}
