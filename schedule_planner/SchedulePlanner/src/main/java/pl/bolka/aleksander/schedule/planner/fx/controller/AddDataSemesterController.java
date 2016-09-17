package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.SemesterColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.filter.DayFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SemesterFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;
import pl.bolka.aleksander.schedule.planner.util.CallbackForDatePickers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2016-08-17.
 */
public class AddDataSemesterController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataSemester.fxml";

    @FXML
    public TableView<Semester> semesterTable;

    @FXML
    public TextField number;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @FXML
    public Button add;

    @FXML
    public Button delete;

    @FXML
    public DatePicker datePickerFrom;

    @FXML
    public DatePicker datePickerTo;

    @Autowired
    private AbstractRepositoryService<Semester, SemesterFilter> semesterRepositoryService;

    @Autowired
    private AbstractRepositoryService<Day, DayFilter> dayRepositoryService;

    @Override
    public void initialize() {
        setButtons();

        setSemesterTable();

        setSemesterTableListener();

        setDatePickerFrom(datePickerFrom);
        setDatePickerFrom(datePickerTo);
    }

    private void setButtons() {
        setNavigateButtons();
        setAddButton();
        setDeleteButton();
    }

    private void setDeleteButton() {
        delete.setOnAction(event -> {
            Semester semester = getSelectedItemFromTable(semesterTable);
            semesterRepositoryService.delete(semester);
        });
    }

    private void setSemesterTableListener() {
        semesterTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Semester semester = getSelectedItemFromTable(semesterTable);
            if (semester != null) {
                number.setText(semester.getNumber() + "");
            }
            datePickerFrom.setValue(semester != null ? semester.getFromDate().toLocalDate() : null);
            datePickerTo.setValue(semester.getToDate().toLocalDate());
        });
    }

    private void setAddButton() {
        add.setOnAction(event -> semesterRepositoryService.save(getNewSemester()));
    }

    public Semester getNewSemester(){
        Semester semester = new Semester();
        semester.setNumber(Integer.parseInt(number.getText()));
        semester.setFromDate(Date.valueOf(datePickerFrom.getValue()));
        semester.setToDate(Date.valueOf(datePickerTo.getValue()));
        semester.setYear(datePickerFrom.getValue().getYear());
        return semester;
    }

    private void setDatePickerFrom(DatePicker datePicker) {
        datePicker.setOnAction(event -> {
            LocalDate localDate = datePicker.getValue();
            DayFilter dayFilter = new DayFilter();
            dayFilter.setDate(Date.valueOf(localDate));
            Day day = dayRepositoryService.findOne(dayFilter);
            Hibernate.initialize(day.getHour());
        });
        List<Day> days = dayRepositoryService.findAll();
        datePicker.setDayCellFactory(CallbackForDatePickers.getCallBackForDates(getLocalDates(days)));
    }

    private List<LocalDate> getLocalDates(List<Day> days) {
        return days.stream().map(d -> d.getDate().toLocalDate()).collect(Collectors.toList());
    }

    private void setSemesterTable() {
        List<Semester> semesters = semesterRepositoryService.findAll();
        setColumn(semesterTable, new SemesterColumn("Semestry"), semesters);
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    public AddDataSemesterController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
