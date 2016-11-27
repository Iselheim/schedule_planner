package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.DayViewTO;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class DayViewTOColumn extends TableColumn<DayViewTO,String>{
    public DayViewTOColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }
}
