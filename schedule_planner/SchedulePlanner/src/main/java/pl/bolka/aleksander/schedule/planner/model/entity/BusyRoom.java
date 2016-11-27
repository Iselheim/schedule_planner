package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BusyRoom extends Room {

    @ManyToOne(fetch = FetchType.EAGER)
    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
