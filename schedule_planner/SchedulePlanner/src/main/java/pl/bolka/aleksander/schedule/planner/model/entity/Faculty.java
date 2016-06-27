package pl.bolka.aleksander.schedule.planner.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Faculty implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String name = "";

    private String shortcut = "";

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
    private List<Specialization> specialization;

//    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
//    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
//    private List<Lecturer> lecturers;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
    private List<Subject> subjects;

    public Faculty() {
        // TODO Auto-generated constructor stub
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return getName();
    }

}
