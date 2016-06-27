package pl.bolka.aleksander.schedule.planner.model.dto;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
public abstract class BaseDTO {

    private long id;

    public BaseDTO() {

    }

    public BaseDTO(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
