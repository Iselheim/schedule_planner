package pl.bolka.aleksander.schedule.planner.model.filter;

import java.sql.Date;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class RoomFilter implements Filter {

    private Long id;

    private Date date;

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
}
