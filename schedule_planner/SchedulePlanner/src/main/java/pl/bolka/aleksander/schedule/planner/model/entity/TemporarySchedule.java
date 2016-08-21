package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

//TODO relacje do przemyślenia
@Entity
public class TemporarySchedule implements ScheduleInterface,Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Lecturer lecturer;

    @OneToOne
    private FreeRoom room;

    @OneToOne
    private Day day;

    @OneToOne
    private Hour hour;

    @OneToOne
    private StudentGroup group;

    @Transient
    private String text;

    public String getText() {
        setText(this.toString());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TemporarySchedule() {
    }

    public TemporarySchedule(ScheduleInterface scheduleInterface) {
        this.subject = scheduleInterface.getSubject();
        this.lecturer = scheduleInterface.getLecturer();
        this.room = scheduleInterface.getFreeRoom();
        this.day = scheduleInterface.getDay();
        this.hour = scheduleInterface.getHour();
        this.group = scheduleInterface.getGroup();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Subject getSubject() {
        return subject;
    }

    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Lecturer getLecturer() {
        return lecturer;
    }

    @Override
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public FreeRoom getFreeRoom() {
        return room;
    }

    @Override
    public void setFreeRoom(FreeRoom room) {
        this.room = room;
    }

    @Override
    public Day getDay() {
        return day;
    }

    @Override
    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public Hour getHour() {
        return hour;
    }

    @Override
    public void setHour(Hour hour) {
        this.hour = hour;
    }

    @Override
    public StudentGroup getGroup() {
        return group;
    }

    @Override
    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "PlanZajec [id=" + id + ", przedmiot=" + subject + ", wykladowca=" + lecturer + ", sala=" + room
                + ", dzien=" + day + ", godzina=" + hour + ", grupa=" + group + "]";
    }

}
