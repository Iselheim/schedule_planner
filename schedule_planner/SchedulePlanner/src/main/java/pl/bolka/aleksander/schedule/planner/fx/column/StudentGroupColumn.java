package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class StudentGroupColumn extends TableColumn<StudentGroup,String> {

    public StudentGroupColumn(String name){
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty("Grupa: " + p.getValue().getNumber() + " sem. " + p.getValue().getSemester());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
