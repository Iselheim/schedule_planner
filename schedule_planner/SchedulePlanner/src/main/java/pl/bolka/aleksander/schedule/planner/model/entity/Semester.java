package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Semester implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private Set<Week> week;

	@OneToMany(mappedBy = "semester")
	private List<StudentGroup> group;

	@OneToMany(mappedBy = "semester")
	private List<Subject> subject;

	private int number;

	private int year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<StudentGroup> getGroup() {
		return group;
	}

	public void setGroup(List<StudentGroup> group) {
		this.group = group;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Week> getWeek() {
		return week;
	}

	public void setWeek(Set<Week> week) {
		this.week = week;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
}
