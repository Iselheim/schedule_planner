package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class RoomColumn extends TableColumn<FreeRoom, String> {

    public RoomColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty("sala " + p.getValue().getNumber() + " miejsce: " + p.getValue().getRoomSpace());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }
}
