package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Specialization implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;

    private String name = "";
    ;

	@ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private StudiesType type;

    private int semesterCount;

    private Grade grade;

    private String shortcut = "";
    ;

	@OneToMany(mappedBy = "specialization")
    private List<StudentGroup> group;

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

    public StudiesType getType() {
        return type;
    }

    public void setType(StudiesType type) {
        this.type = type;
    }

    public int getSemesterCount() {
        return semesterCount;
    }

    public void setSemesterCount(int semesterCount) {
        this.semesterCount = semesterCount;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public List<StudentGroup> getGroup() {
        return group;
    }

    public void setGroup(List<StudentGroup> group) {
        this.group = group;
    }

}
