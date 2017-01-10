package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class HourForSchedule implements Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Time timeFrom;

    @Column(nullable = false)
    private Time timeTo;

    public HourForSchedule() {
        super();
    }

    public HourForSchedule(Hour hour) {
        super();
        this.timeFrom = hour.getTimeFrom();
        this.timeTo = hour.getTimeTo();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HourForSchedule hour = (HourForSchedule) o;

        return id != null ? id.equals(hour.id) : hour.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
