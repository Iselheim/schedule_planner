package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.SubjectViewTO;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SubjectViewTOColumn extends TableColumn<SubjectViewTO,String> {

    public SubjectViewTOColumn(String header){
        super(header);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getName() + "\n" + p.getValue().getSubjectType());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

}
