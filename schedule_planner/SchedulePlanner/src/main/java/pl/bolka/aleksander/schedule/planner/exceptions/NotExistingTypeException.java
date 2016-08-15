package pl.bolka.aleksander.schedule.planner.exceptions;

/**
 * Created by Aleksander on 2016-08-15.
 */
public class NotExistingTypeException extends Throwable {

    private String message;

    public NotExistingTypeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
