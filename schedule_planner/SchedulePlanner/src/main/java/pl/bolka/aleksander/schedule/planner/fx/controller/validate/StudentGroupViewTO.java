package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class StudentGroupViewTO extends ViewTO{

    private String number;

    private String semesterNumber;

    private Map<Long,SubjectViewTO> subjects;

    public Map<Long, SubjectViewTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<Long, SubjectViewTO> subjects) {
        this.subjects = subjects;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(String semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}
