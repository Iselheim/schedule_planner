package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
	private Set<Day> days;
	
	private int weekOfSemester;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Day> getDays() {
		return days;
	}

	public void setDays(Set<Day> days) {
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
	
		
}
