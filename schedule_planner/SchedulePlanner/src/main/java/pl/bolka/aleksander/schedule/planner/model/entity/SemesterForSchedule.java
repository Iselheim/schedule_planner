package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class SemesterForSchedule implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private int number;

	@Column(nullable = false)
	private int year;

	@Column(nullable = false)
    private Date fromDate;

	@Column(nullable = false)
    private Date toDate;

    public SemesterForSchedule() {
        super();
    }

    public SemesterForSchedule(Semester semester) {
        super();
        this.number = semester.getNumber();
        this.year = semester.getYear();
        this.fromDate = semester.getFromDate();
        this.toDate = semester.getToDate();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Semestr " + getNumber() + "\n rok: " + getYear();
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SemesterForSchedule semester = (SemesterForSchedule) o;

        return id != null ? id.equals(semester.id) : semester.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
