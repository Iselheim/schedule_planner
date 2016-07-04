package pl.bolka.aleksander.schedule.planner.model.filter;

import java.util.Date;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class DayFilter implements Filter {

    private Date date;

    private boolean first;

    private boolean last;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFirst() {

        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
