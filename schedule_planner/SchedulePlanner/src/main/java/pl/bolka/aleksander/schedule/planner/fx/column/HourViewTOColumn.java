package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.HourViewTO;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class HourViewTOColumn extends TableColumn<HourViewTO, String> {

    public HourViewTOColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getTimeFrom() + " - " + p.getValue().getTimeTo());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }
}
