package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.WeekViewTO;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class WeekViewTOColumn extends TableColumn<WeekViewTO, String> {

    public WeekViewTOColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getWeekOfSemester() + " tydzień " + p.getValue().getDates());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }


}
