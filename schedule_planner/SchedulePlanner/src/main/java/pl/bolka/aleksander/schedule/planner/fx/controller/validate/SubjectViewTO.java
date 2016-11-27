package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class SubjectViewTO {

    private Long id;

    private String name;

    private String subjectType;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Map<Long, LecturerViewTO> lecturers;

    public Map<Long, LecturerViewTO> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Map<Long, LecturerViewTO> lecturers) {
        this.lecturers = lecturers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
