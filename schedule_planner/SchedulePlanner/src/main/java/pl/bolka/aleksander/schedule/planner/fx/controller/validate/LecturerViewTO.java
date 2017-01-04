package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

import java.util.Map;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class LecturerViewTO extends ViewTO{

    private Map<Long,RoomViewTO> rooms;

    private String universityDegree;

    private String firstName;

    private String lastName;

    public Map<Long, RoomViewTO> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Long, RoomViewTO> rooms) {
        this.rooms = rooms;
    }

    public String getUniversityDegree() {
        return universityDegree;
    }

    public void setUniversityDegree(String universityDegree) {
        this.universityDegree = universityDegree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
