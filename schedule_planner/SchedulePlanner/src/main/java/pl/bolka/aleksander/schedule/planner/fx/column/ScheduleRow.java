package pl.bolka.aleksander.schedule.planner.fx.column;

import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.sql.Time;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class ScheduleRow {

    private SemesterForSchedule semester;

    private StudentGroupForSchedule studentGroup;

    private SubjectForSchedule subject;

    private LecturerForSchedule lecturer;

    private FreeRoomForSchedule room;

    private DayForSchedule day;

    private Time hoursFrom;

    private Time hoursTo;

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

    public FreeRoomForSchedule getRoom() {
        return room;
    }

    public void setRoom(FreeRoomForSchedule room) {
        this.room = room;
    }

    public DayForSchedule getDay() {
        return day;
    }

    public void setDay(DayForSchedule day) {
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