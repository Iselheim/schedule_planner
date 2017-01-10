package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentGroupForSchedule implements Identifiable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;

    private int quantity;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int semesterNumber;

    public StudentGroupForSchedule() {
        super();
    }

    public StudentGroupForSchedule(StudentGroup studentGroup) {
        super();
        this.quantity = studentGroup.getQuantity();
        this.number = studentGroup.getNumber();
        this.semesterNumber = studentGroup.getNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentGroupForSchedule that = (StudentGroupForSchedule) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Grupa: " + getNumber() + " sem. " + semesterNumber;
    }
}
