package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;

@Entity
public class Hour implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private Time timeFrom;

    private Time timeTo;
    
    public Hour() {
        super();
    }

    public Hour(long id, Time od, Time doo) {
        super();
        this.id = id;
        this.timeFrom = od;
        this.timeTo = doo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return timeFrom + "-" + timeTo;
    }

}
