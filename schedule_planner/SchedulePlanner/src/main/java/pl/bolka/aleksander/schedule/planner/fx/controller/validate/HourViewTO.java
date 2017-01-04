package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class HourViewTO extends ViewTO{

    private String timeFrom;

    private String timeTo;

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

}
