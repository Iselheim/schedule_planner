package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class DayViewTO extends ViewTO{

    private Map<Long,HourViewTO> hours;

    private String name;

    public Map<Long, HourViewTO> getHours() {
        return hours;
    }

    public void setHours(Map<Long, HourViewTO> hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
