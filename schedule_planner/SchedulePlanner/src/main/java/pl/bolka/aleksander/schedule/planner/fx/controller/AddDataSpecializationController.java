package pl.bolka.aleksander.schedule.planner.fx.controller;

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
import pl.bolka.aleksander.schedule.planner.fx.column.SpecializationColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.StudentGroupColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.SubjectColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.*;
import pl.bolka.aleksander.schedule.planner.model.filter.FacultyFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SpecializationFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.StudentGroupFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.util.List;

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

    @FXML
    public TableView subjectsTable;

    @FXML
    public Button addNew;

    @Autowired
    private AbstractRepositoryService<Specialization, SpecializationFilter> specializationRepositoryService;

    @Autowired
    private AbstractRepositoryService<Faculty,FacultyFilter> facultyRepositoryService;

    @Autowired
    private AbstractRepositoryService<Subject,SubjectFilter> subjectRepositoryService;

    @Autowired
    private AbstractRepositoryService<StudentGroup,StudentGroupFilter> studentRepositoryService;

    private Specialization pickedSpec;

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

    private void setSubjectTable() {
        setColumn(subjectsTable,new SubjectColumn("Przedmioty"),subjectRepositoryService.findAll());
    }

    @Transactional
    private void setSpecializationTableListener() {
        specializationTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            change.setDisable(false);
            delete.setDisable(false);
            add.setDisable(true);
            Specialization specialization = getSelectedItemFromTable(specializationTable);
            pickedSpec = specialization;
            if(specialization!=null){
                name.setText(specialization.getName());
                facultyComboBx.getSelectionModel().select(specialization.getFaculty());
                studiesType.getSelectionModel().select(specialization.getType().getName());
                semesterCount.setText(specialization.getSemesterCount() + "");
                shortCut.setText(specialization.getShortcut());
                List<StudentGroup> group = specialization.getGroup();
                setColumn(studentGroupsTable, new StudentGroupColumn("Grupy"), group);
                setColumn(subjectsTable,new SubjectColumn("Przedmioty"),specialization.getSubject());
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

        setAddNewButton();
        setAddButton();
        setDeleteButton();
    }

    private void setDeleteButton() {
        //TODO nie dzala poprawnie
        delete.setOnAction(event -> specializationRepositoryService.delete(pickedSpec));
    }

    @Transactional
    private void setAddButton() {
        add.setOnAction(event -> {
            Specialization specialization = new Specialization();
            specialization.setFaculty(facultyComboBx.getValue());
            specialization.setGroup(getSelectedItemsFromTable(studentGroupsTable));
            specialization.setName(name.getText());
            specialization.setSemesterCount(Integer.parseInt(semesterCount.getText()));
            specialization.setShortcut(shortCut.getText());
            specialization.setSubject(getSelectedItemsFromTable(subjectsTable));
            specialization.setType(StudiesType.valueOf(studiesType.getValue().toUpperCase()));
            specializationRepositoryService.save(specialization);
        });
    }

    private void setAddNewButton() {
        addNew.setOnAction(event -> {
            add.setDisable(false);
            change.setDisable(true);
            delete.setDisable(true);
            setSelectionModeMultiple(subjectsTable);
            setSelectionModeMultiple(studentGroupsTable);
            setSubjectTable();
            setStudentGroupsTable();
        });
    }


    private void setStudentGroupsTable() {
        setColumn(studentGroupsTable,new StudentGroupColumn("Grupy"),studentRepositoryService.findAll());
    }

    @Transactional
    private void setSpecializationTable() {
        setColumn(specializationTable,new SpecializationColumn("Kierunek"),translateToObsList(specializationRepositoryService.findAll()));
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
