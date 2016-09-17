package pl.bolka.aleksander.schedule.planner.model.filter;

import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

import java.sql.Date;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class RoomFilter implements Filter {

    private Long id;

    private Date date;
    private static Subject subject;
    private String number;

    public static void setSubject(Subject subject) {
        RoomFilter.subject = subject;
    }

    public static Subject getSubject() {
        return subject;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
