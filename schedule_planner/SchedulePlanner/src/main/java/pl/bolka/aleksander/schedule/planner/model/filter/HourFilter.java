package pl.bolka.aleksander.schedule.planner.model.filter;

import java.sql.Date;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class HourFilter implements Filter {

    private Date date;

    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
