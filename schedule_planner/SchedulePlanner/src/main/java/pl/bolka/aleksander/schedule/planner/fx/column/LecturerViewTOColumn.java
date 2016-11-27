package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.LecturerViewTO;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class LecturerViewTOColumn extends TableColumn<LecturerViewTO,String> {

    public LecturerViewTOColumn(String name){
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getUniversityDegree() + " " + p.getValue().getFirstName() + " " + p.getValue().getLastName());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
