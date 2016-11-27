package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.SemesterViewTO;

/**
 * Created by Aleksander on 2016-08-17.
 */
public class SemesterViewTOColumn extends TableColumn<SemesterViewTO,String> {

    public SemesterViewTOColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty("Semestr " + p.getValue().getNumber() + "\n rok: " + p.getValue().getYear());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }
}
