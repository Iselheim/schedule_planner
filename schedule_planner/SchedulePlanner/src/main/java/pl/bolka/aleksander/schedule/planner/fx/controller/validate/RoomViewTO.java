package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class RoomViewTO extends ViewTO{

    private Map<Long,WeekViewTO> weeks;

    private String number;

    private String roomSpace;

    public Map<Long, WeekViewTO> getWeeks() {
        return weeks;
    }

    public void setWeeks(Map<Long, WeekViewTO> weeks) {
        this.weeks = weeks;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(String roomSpace) {
        this.roomSpace = roomSpace;
    }
}
