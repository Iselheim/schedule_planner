package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * The persistent class for the dzien model table.
 *
 */
@Entity
public class DayForSchedule implements Serializable, Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;

	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
	private Date date;

	public DayForSchedule() {
		super();
	}

	public DayForSchedule(Day day) {
		super();
		this.name = day.getName();
		this.date = day.getDate();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return getName();
	}
}
