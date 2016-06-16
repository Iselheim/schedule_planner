package pl.bolka.aleksander.schedule.planner.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * The persistent class for the dzien model table.
 *
 */
@Entity
public class Day implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;

    private String name = "";

    @ManyToMany
    private List<Hour> hour;

	private Date date;
	
	private boolean isFree = false;
    
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

	public Day() {
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


}
