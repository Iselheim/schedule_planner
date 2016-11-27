package pl.bolka.aleksander.schedule.planner.fx.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.Week;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Aleksander on 2016-09-14.
 */
public class WeekColumn extends TableColumn<Week, String> {

    public WeekColumn(String name) {
        super(name);
        setCellFactory();
    }

    private void setCellFactory() {
        setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getWeekOfSemester() + " tydzień " + getDates(p.getValue().getDays()));
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

    private String getDates(List<Day> days) {
        Date from = null;
        Date to = null;
        for (Day day : days) {
            Date date = day.getDate();
            if (from == null) {
                from = date;
                to = date;
            }
            if (from.compareTo(date) > 0){
                from = date;
            }
            if (to.compareTo(date) < 0){
                to = date;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
        return simpleDateFormat.format(from) + " - " + simpleDateFormat.format(to);
    }
}
