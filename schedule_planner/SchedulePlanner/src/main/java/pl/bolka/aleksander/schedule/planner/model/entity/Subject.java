package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;

    private String name = "";

	@ManyToOne
	private Semester semester;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private SubjectType subjectType;

    @ManyToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Lecturer> lecturer;

    @ManyToMany(fetch= FetchType.EAGER)
    private List<FreeRoom> freeRoom;

    private int hours;
    
    public List<FreeRoom> getFreeRoom() {
        return freeRoom;
    }

    public void setFreeRoom(List<FreeRoom> freeRoom) {
        this.freeRoom = freeRoom;
    }

    public List<Lecturer> getLecturer() {
        return lecturer;
    }

    public void setLecturer(List<Lecturer> lecturer) {
        this.lecturer = lecturer;
    }

    public Long getId() {
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

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return getName() + "\n" + getSubjectType().getName();
    }
}
