package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SpecializationColumn extends TableColumn<Specialization,String> {

    public SpecializationColumn(String name){
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getName()+ "\n Wydział: " + p.getValue().getFaculty().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
