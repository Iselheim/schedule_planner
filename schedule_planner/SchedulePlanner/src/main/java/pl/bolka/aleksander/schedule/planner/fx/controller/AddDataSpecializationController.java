package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.SpecializationColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.StudentGroupColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;
import pl.bolka.aleksander.schedule.planner.model.entity.StudiesType;
import pl.bolka.aleksander.schedule.planner.model.filter.FacultyFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SpecializationFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.util.List;

//TODO skonczyć dodawanie kierunków
/**
 * Created by Aleksander on 2016-08-21.
 */
public class AddDataSpecializationController  extends  FXController{

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataSpecialization.fxml";

    @FXML
    public TableView<Specialization> specializationTable;

    @FXML
    public TextField name;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @FXML
    public Button add;

    @FXML
    public Button delete;

    @FXML
    public ComboBox<Faculty> facultyComboBx;

    @FXML
    public ComboBox<String> studiesType;

    @FXML
    public Button change;

    @FXML
    public TextField semesterCount;

    @FXML
    public TextField shortCut;

    @FXML
    public TableView<StudentGroup> studentGroupsTable;

    @Autowired
    private AbstractRepositoryService<Specialization, SpecializationFilter> specializationRepositoryService;

    @Autowired
    private AbstractRepositoryService<Faculty,FacultyFilter> facultyRepositoryService;

    public AddDataSpecializationController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public void initialize() {
        setButtons();

        setSpecializationTable();
        setSpecializationTableListener();

        setPickers();
    }

    private void setSpecializationTableListener() {
        specializationTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            change.setDisable(false);
            delete.setDisable(false);
            add.setDisable(true);
            Specialization specialization = getSelectedItemFromTable(specializationTable);
            if(specialization!=null){
                name.setText(specialization.getName());
                facultyComboBx.getSelectionModel().select(specialization.getFaculty());
                studiesType.getSelectionModel().select(specialization.getType().getName());
                semesterCount.setText(specialization.getSemesterCount() + "");
                shortCut.setText(specialization.getShortcut());
                setColumns(studentGroupsTable, new StudentGroupColumn("Grupy"), specialization.getGroup());
            }

        });
    }

    private void setPickers() {
        setConverterForFacultyPicker();
        setFacultyPickerForEmpty();
        setDefaultItemsForTypePicker();
    }

    private void setDefaultItemsForTypePicker() {
        List<String> stringList = StudiesType.getAllAsList();
        studiesType.getItems().addAll(stringList);
    }

    private void setFacultyPickerForEmpty() {

        facultyComboBx.getItems().addAll(facultyRepositoryService.findAll());

    }

    private void setConverterForFacultyPicker() {
        facultyComboBx.setConverter(new StringConverter<Faculty>() {

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

    private void setCreationButtons() {
        add.setDisable(true);
        change.setDisable(true);
        delete.setDisable(true);
    }

    @Transactional
    private void setSpecializationTable() {
        setColumns(specializationTable,new SpecializationColumn("Kierunek"),translateToObsList(specializationRepositoryService.findAll()));
    }

    private void setButtons() {
        setNavigateButtons();
        setCreationButtons();
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
