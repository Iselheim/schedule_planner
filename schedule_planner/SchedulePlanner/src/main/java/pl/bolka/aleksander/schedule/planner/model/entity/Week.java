package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Week implements Serializable, Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Day> days;
	
	private int weekOfSemester;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
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

		Week week = (Week) o;

		return id != null ? id.equals(week.id) : week.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
