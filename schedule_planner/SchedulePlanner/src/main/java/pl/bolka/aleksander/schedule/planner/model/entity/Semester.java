package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

	@OneToOne(mappedBy = "semester")
	private StudentGroup group;

	@OneToMany(mappedBy = "semester")
	private Set<Subject> subject;

	private int number;

	private int year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentGroup getGroup() {
		return group;
	}

	public void setGroup(StudentGroup group) {
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

	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

}
