package pl.bolka.aleksander.schedule.planner.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * The persistent class for the dzien model table.
 *
 */
@Entity
public class Day implements Serializable, Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;

    private String name = "";

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Hour> hour;

	private Date date;
	
	private boolean isFree = false;

    public Day() {
    }

    public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
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

	public List<Hour> getHour() {
		return hour;
	}

	public void setHour(List<Hour> hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return getName();
	}
}
