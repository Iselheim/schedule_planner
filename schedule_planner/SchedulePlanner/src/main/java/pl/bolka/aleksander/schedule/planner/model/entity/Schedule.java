package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.util.List;

//TODO relacje do przemyślenia
@Entity
public class Schedule implements Identifiable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private StudentGroup studentGroup;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToOne
    private Room room;

    @OneToMany
    private List<Week> week;

    @ManyToOne
    private Day day;

    @OneToMany
    private List<Hour> hour;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Week> getWeek() {
        return week;
    }

    public void setWeek(List<Week> week) {
        this.week = week;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }
}
