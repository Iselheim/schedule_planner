package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Subject implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String name = "";
    ;

	@ManyToMany(mappedBy = "subject")
    private List<StudentGroup> group;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private SubjectType subjectType;

    @ManyToMany(mappedBy = "subject")
    private List<Lecturer> lecturer;

    @OneToMany
    private List<Room> room;

    @Transient
    private String text;

    public String getText() {
        setText(this.toString());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public List<Lecturer> getLecturer() {
        return lecturer;
    }

    public void setLecturer(List<Lecturer> lecturer) {
        this.lecturer = lecturer;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public List<StudentGroup> getGroup() {
        return group;
    }

    public void setGroup(List<StudentGroup> group) {
        this.group = group;
    }

    @Override
    public String toString() {
        if (subjectType != null) {
            return name + " " + subjectType.getShortcut();
        } else {
            return "";
        }
    }

}
