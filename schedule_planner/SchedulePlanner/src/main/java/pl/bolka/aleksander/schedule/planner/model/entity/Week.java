package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Week implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
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
