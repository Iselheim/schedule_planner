package pl.bolka.aleksander.schedule.planner.fx.column;

import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.sql.Time;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class ScheduleRow {

    private Semester semester;

    private StudentGroup studentGroup;

    private Subject subject;

    private Lecturer lecturer;

    private FreeRoom room;

    private Day day;

    private Time hoursFrom;

    private Time hoursTo;


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

    public FreeRoom getRoom() {
        return room;
    }

    public void setRoom(FreeRoom room) {
        this.room = room;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Time getHoursFrom() {
        return hoursFrom;
    }

    public void setHoursFrom(Time hoursFrom) {
        this.hoursFrom = hoursFrom;
    }

    public Time getHoursTo() {
        return hoursTo;
    }

    public void setHoursTo(Time hoursTo) {
        this.hoursTo = hoursTo;
    }
}