package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.StudentGroupViewTO;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class StudentGroupViewTOColumn extends TableColumn<StudentGroupViewTO,String> {

    public StudentGroupViewTOColumn(String name){
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty("Grupa: " + p.getValue().getNumber() + " sem. " + p.getValue().getSemesterNumber());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
