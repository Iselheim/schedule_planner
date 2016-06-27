package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class LecturerColumn extends TableColumn<Lecturer,String> {
    
    private String name = "Wykładowcy";
    
    public LecturerColumn(){
        super();
        this.setText(name);
        setCellFactory();
    }

    public LecturerColumn(String header){
        super(header);
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


    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setText(name);
    }
}
