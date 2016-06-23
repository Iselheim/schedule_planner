package pl.bolka.aleksander.schedule.planner.model.dto;

import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
public class LecturerDTO extends BaseDTO {

    private String firstName = "";

    private String lastName = "";

    private String universityDegree = "";

    private Faculty faculty;

    private List<Subject> subject;

    public LecturerDTO(Long id) {
        super(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniversityDegree() {
        return universityDegree;
    }

    public void setUniversityDegree(String universityDegree) {
        this.universityDegree = universityDegree;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

}
