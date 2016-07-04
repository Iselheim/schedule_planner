package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Entity(name = "free_room")
public class FreeRoom extends Room{

    @ManyToMany
    private Set<Day> day;

    @Transient
    private String text;

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
