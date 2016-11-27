package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class WeekViewTO {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Map<Long, DayViewTO> days;

    public Map<Long, DayViewTO> getDays() {
        return days;
    }

    public void setDays(Map<Long, DayViewTO> days) {
        this.days = days;
    }
}
