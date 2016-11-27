package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class RoomViewTO {

    private Long id;

    private Map<Long,WeekViewTO> weeks;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public Map<Long, WeekViewTO> getWeeks() {
        return weeks;
    }

    public void setWeeks(Map<Long, WeekViewTO> weeks) {
        this.weeks = weeks;
    }
}
