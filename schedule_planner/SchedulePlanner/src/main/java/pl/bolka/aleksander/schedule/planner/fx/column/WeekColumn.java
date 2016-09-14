package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Week;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class WeekColumn extends TableColumn<Week,String> {

    public WeekColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getWeekOfSemester() + " tydzień semestru");
            } else {
                return new SimpleStringProperty("");
            }
        });
    }
}
