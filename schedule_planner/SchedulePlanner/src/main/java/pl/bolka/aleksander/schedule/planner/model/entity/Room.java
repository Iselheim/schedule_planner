package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Aleksander on 2016-09-17.
 */
@MappedSuperclass
public class Room implements Identifiable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String number;

    protected int roomSpace;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Week> week;



    public Long getId() {
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

}
