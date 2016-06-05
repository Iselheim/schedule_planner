package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * The persistent class for the dzien model table.
 *
 */
@Entity
public class Day implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name = "";

    @ManyToMany
    private Set<Hour> hour;

    @Transient
    private String text;

    public String getText() {
        setText(this.toString());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Day() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hour> getHour() {
        return hour;
    }

    public void setHour(Set<Hour> hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return name;
    }

}
