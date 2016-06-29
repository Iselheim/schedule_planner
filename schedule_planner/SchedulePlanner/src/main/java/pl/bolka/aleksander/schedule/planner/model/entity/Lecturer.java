package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//TODO dni i godziny preferencji pracy
@Entity
public class Lecturer implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String firstName = "";

    private String lastName = "";

    private String universityDegree = "";

    @ManyToMany(fetch= FetchType.EAGER)
    private List<Subject> subject;

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return universityDegree + " " + firstName + " " + lastName;
    }

}
