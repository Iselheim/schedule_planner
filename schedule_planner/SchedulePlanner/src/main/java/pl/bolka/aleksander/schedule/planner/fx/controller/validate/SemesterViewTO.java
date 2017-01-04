package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class SemesterViewTO extends ViewTO{

    private String number;

    private String year;

    private Map<Long,StudentGroupViewTO> groups;

    public Map<Long, StudentGroupViewTO> getGroups() {
        return groups;
    }

    public void setGroups(Map<Long, StudentGroupViewTO> groups) {
        this.groups = groups;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
