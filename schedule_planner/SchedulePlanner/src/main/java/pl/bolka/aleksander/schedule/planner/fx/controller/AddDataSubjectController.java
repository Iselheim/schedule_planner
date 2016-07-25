package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.LecturerColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.RoomColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.SubjectColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.*;
import pl.bolka.aleksander.schedule.planner.model.filter.FacultyFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;
import pl.bolka.aleksander.schedule.planner.model.repository.FacultyRepository;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-05.
 */
public class AddDataSubjectController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataSubject.fxml";

    @FXML
    private TextField name;

    @FXML
    private TextField hours;

    @FXML
    private Button change;

    @FXML
    private Button back;

    @FXML
    private Button mainMenu;

    @FXML
    private Button add;

    @FXML
    private Button addNewSubject;

    @FXML
    private Button delete;

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private ComboBox<Faculty> facultyPicker;

    @FXML
    private TableView<Lecturer> lecturerTable;

    @FXML
    private ComboBox<String> typePicker;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TextField semester;

    public AddDataSubjectController(ScreensConfig flow) {
        super(flow);
    }

    @Autowired
    private AbstractRepositoryService<Faculty, FacultyFilter> facultyRepositoryService;

    @Autowired
    private AbstractRepositoryService<Subject,SubjectFilter> subjectRepositoryService;

    private Subject selectedSubject;

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public void initialize() {
        setNavigateButtons();
        setConverterForFacultyPicker();
        setDefaultItemsForTypePicker();
        setFacultyPickerForEmpty();

        setStartButtons();

        setSubjectTableListener();
        setSubjectTable();

        setSelectionModeMultiple(roomTable);
        setSelectionModeMultiple(lecturerTable);

        change.setOnAction(event -> {

        });



    }

    private void setStartButtons() {
        change.setDisable(true);
        delete.setDisable(true);
        add.setDisable(true);
    }

    private void setSubjectTableListener() {
        subjectTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Subject subject = getSelectedItemFromTable(subjectTable);
            if(subject != null){
                name.setText(subject.getName());
                hours.setText(subject.getHours() + "");
                change.setDisable(false);
                add.setDisable(true);
                typePicker.getSelectionModel().select(subject.getSubjectType().getName());
                facultyPicker.getSelectionModel().select(subject.getFaculty());
                setColumns(roomTable,new RoomColumn("Sale"), translateToObsList(subject.getRoom()));
                setColumns(lecturerTable, new LecturerColumn("Wykładowcy"), translateToObsList(subject.getLecturer()));
                semester.setText(subject.getSemester().getNumber() + "");
                selectedSubject = subject;
            }
        });
    }

    private void setSubjectTable(){
        setColumns(subjectTable,new SubjectColumn("Przedmioty"), translateToObsList(subjectRepositoryService.findAll()));
    }

    private void setFacultyPickerForEmpty() {

        facultyPicker.getItems().addAll(facultyRepositoryService.findAll());

    }

    private void setConverterForFacultyPicker() {
        facultyPicker.setConverter(new StringConverter<Faculty>() {

            @Override
            public String toString(Faculty faculty) {
                if (faculty != null)
                    return faculty.getName();
                return "";
            }

            @Override
            public Faculty fromString(String string) {
                throw new RuntimeException("not required for non editable ComboBox");
            }
        });
    }

    private void setDefaultItemsForTypePicker() {
        List<String> stringList = SubjectType.getAllAsList();
        typePicker.getItems().addAll(stringList);
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


}
