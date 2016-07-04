package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String number;

    protected int roomSpace;

    @ManyToMany
    private List<Week> week;

    @ManyToMany
    private List<Day> day;

    public Room() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(int roomSpace) {
        this.roomSpace = roomSpace;
    }

    public List<Week> getWeek() {
        return week;
    }

    public void setWeek(List<Week> week) {
        this.week = week;
    }

    public List<Day> getDay() {
        return day;
    }

    public void setDay(List<Day> day) {
        this.day = day;
    }
}
