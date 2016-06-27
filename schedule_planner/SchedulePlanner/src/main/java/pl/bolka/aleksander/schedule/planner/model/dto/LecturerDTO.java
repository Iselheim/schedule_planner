package pl.bolka.aleksander.schedule.planner.model.dto;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
public class LecturerDTO extends BaseDTO {

    private String firstName = "";

    private String lastName = "";

    private String universityDegree = "";

    private FacultyDTO faculty;

    private List<SubjectDTO> subject;

    public LecturerDTO(){
        super();
    }

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

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public List<SubjectDTO> getSubject() {
        return subject;
    }

    public void setSubject(List<SubjectDTO> subject) {
        this.subject = subject;
    }
}
