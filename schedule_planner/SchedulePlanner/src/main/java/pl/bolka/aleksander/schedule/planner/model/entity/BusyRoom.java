package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Set;

@Entity(name = "busy_room")
public class BusyRoom extends Room {

	private static final long serialVersionUID = 1L;

	@OneToMany
    @JoinColumn
    private Set<Day> day;

    @Transient
    private String text;

    public BusyRoom() {

    }

    public BusyRoom(FreeRoom room) {
        super();
//        this.day = room.getDay();
        this.number = (room.getNumber());
        this.roomSpace = (room.getRoomSpace());
        this.text = (room.getText());
    }

    public String getText() {
        setText(this.toString());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public Set<Day> getDay() {
//        return day;
//    }

    public void setDay(Set<Day> day) {
        this.day = day;
    }

}
