package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

//TODO relacje do przemyślenia
@Entity
public class Schedule implements Serializable, ScheduleInterface {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    public Schedule() {
        // TODO Auto-generated constructor stub
    }

    public Schedule(Subject subject, Lecturer lecturer, FreeRoom room, Day day, Hour hour, StudentGroup group) {
        this.subject = subject;
        this.lecturer = lecturer;
        this.room = room;
        this.day = day;
        this.hour = hour;
        this.group = group;
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public FreeRoom getFreeRoom() {
        return room;
    }

    public void setFreeRoom(FreeRoom room) {
        this.room = room;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "PlanZajec [id=" + id + ", przedmiot=" + subject + ", wykladowca=" + lecturer + ", sala=" + room
                + ", dzien=" + day + ", godzina=" + hour + ", grupa=" + group + "]";
    }

}
