package pl.bolka.aleksander.schedule.planner.model.dto;

import java.util.Set;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
public class RoomDTO extends BaseDTO{

    protected String number;

    protected int roomSpace;

    private Set<WeekDTO> week;

    public RoomDTO(){
        super();
    }

    public RoomDTO(Long id){
        super(id);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(int roomSpace) {
        this.roomSpace = roomSpace;
    }

    public Set<WeekDTO> getWeek() {
        return week;
    }

    public void setWeek(Set<WeekDTO> week) {
        this.week = week;
    }
}
