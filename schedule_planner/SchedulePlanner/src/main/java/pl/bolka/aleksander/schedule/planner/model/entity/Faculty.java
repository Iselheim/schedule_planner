package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;

@Entity
public class Faculty implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String name = "";

    private String shortcut = "";

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
    private List<Specialization> specialization;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
    private List<Lecturer> lecturers;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
    private List<Subject> subjects;

    @Transient
    private String text;

    public String getText() {
        setText(this.toString());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Faculty() {
        // TODO Auto-generated constructor stub
    }

    public Faculty(long id, String nazwa, String skrot, List<Specialization> kierunek, List<Lecturer> wykladowcy,
            List<Subject> przedmiot) {
        super();
        this.id = id;
        this.name = nazwa;
        this.shortcut = skrot;
        this.specialization = kierunek;
        this.lecturers = wykladowcy;
        this.subjects = przedmiot;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
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
        return "Wydzial [id=" + id + ", nazwa=" + name + ", skrot=" + shortcut + ", kierunek=" + specialization
                + ", wykladowcy=" + lecturers + ", przedmiot=" + subjects + "]";
    }

}
