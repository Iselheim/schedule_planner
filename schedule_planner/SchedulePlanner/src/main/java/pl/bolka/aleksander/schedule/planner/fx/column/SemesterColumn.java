package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import org.neo4j.cypher.internal.compiler.v2_1.functions.Str;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

/**
 * Created by Aleksander on 2016-08-17.
 */
public class SemesterColumn extends TableColumn<Semester,String> {

    public SemesterColumn(String name) {
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
