package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FreeRoomForSchedule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    private Integer roomSpace;

    public FreeRoomForSchedule() {
        super();
    }
    public FreeRoomForSchedule(FreeRoom room) {
        super();
        this.number = room.getNumber();
        this.roomSpace = room.getRoomSpace();
    }

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

    public Integer getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(int roomSpace) {
        this.roomSpace = roomSpace;
    }

    @Override
    public String toString() {
        return "sala " + getNumber() + " miejsce: " + getRoomSpace();
    }
}
