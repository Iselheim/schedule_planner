package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SubjectColumn extends TableColumn<Subject,String> {

    private String name = "Przedmioty";

    public SubjectColumn(){
        super();
        this.setText(name);
        setCellFactory();
    }

    public SubjectColumn(String header){
        super(header);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getName() + "\n" + p.getValue().getSubjectType().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setText(name);
    }
}
