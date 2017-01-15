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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SemesterForSchedule semester;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StudentGroupForSchedule studentGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SubjectForSchedule subject;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LecturerForSchedule lecturer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FreeRoomForSchedule freeRoom;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WeekForSchedule> week;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DayForSchedule> day;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HourForSchedule> hour;

    @Transient
    private boolean printed = false;

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SemesterForSchedule getSemester() {
        return semester;
    }

    public void setSemester(SemesterForSchedule semester) {
        this.semester = semester;
    }

    public StudentGroupForSchedule getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroupForSchedule studentGroup) {
        this.studentGroup = studentGroup;
    }

    public SubjectForSchedule getSubject() {
        return subject;
    }

    public void setSubject(SubjectForSchedule subject) {
        this.subject = subject;
    }

    public LecturerForSchedule getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerForSchedule lecturer) {
        this.lecturer = lecturer;
    }

    public FreeRoomForSchedule getFreeRoom() {
        return freeRoom;
    }

    public void setFreeRoom(FreeRoomForSchedule freeRoom) {
        this.freeRoom = freeRoom;
    }

    public List<WeekForSchedule> getWeek() {
        return week;
    }

    public void setWeek(List<WeekForSchedule> week) {
        this.week = week;
    }

    public List<DayForSchedule> getDay() {
        return day;
    }

    public void setDay(List<DayForSchedule> day) {
        this.day = day;
    }

    public List<HourForSchedule> getHour() {
        return hour;
    }

    public void setHour(List<HourForSchedule> hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        return id != null ? id.equals(schedule.id) : schedule.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
