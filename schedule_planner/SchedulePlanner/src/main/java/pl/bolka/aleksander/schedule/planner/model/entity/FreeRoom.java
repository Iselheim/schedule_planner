package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FreeRoom extends Room {


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Day> day;

    public List<Day> getDay() {
        return day;
    }

    public void setDay(List<Day> day) {
        this.day = day;
    }
}
