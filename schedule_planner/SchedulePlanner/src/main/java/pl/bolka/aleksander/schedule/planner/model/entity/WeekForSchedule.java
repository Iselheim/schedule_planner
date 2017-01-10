package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class WeekForSchedule implements Serializable, Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private int weekOfSemester;

    public WeekForSchedule() {
        super();
     }

    public WeekForSchedule(Week week) {
        super();
        this.weekOfSemester = week.getWeekOfSemester();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWeekOfSemester() {
		return weekOfSemester;
	}

	public void setWeekOfSemester(int weekOfSemester) {
		this.weekOfSemester = weekOfSemester;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WeekForSchedule week = (WeekForSchedule) o;

		return id != null ? id.equals(week.id) : week.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
