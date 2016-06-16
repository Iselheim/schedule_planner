package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String number;

    protected int roomSpace;

    @OneToMany
    private Set<Week> week;

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

    public Set<Week> getWeek() {
        return week;
    }

    public void setWeek(Set<Week> week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "nr. " + number + ", ilość miejsc: " + roomSpace;
    }

}
