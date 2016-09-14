package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

import java.sql.Time;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class TimeColumn extends TableColumn<Time,String > {

    public TimeColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().toString());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
