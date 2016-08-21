package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.LecturerColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.SubjectColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.filter.LecturerFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataLecturerController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataLecturers.fxml";

    private static final Logger logger = LogManager.getLogger(AddDataLecturerController.class);


    @FXML
    private TableView<Lecturer> lecturerTable;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField universityDegree;

    @FXML
    private TableView<Subject> lecturerSubjectTable;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private Button add;

    @FXML
    private Button change;

    @FXML
    private Button back;

    @FXML
    private Button mainMenu;

    @FXML
    private Button addNewLecturer;

    @FXML
    private Button delete;

    @Autowired
    private AbstractRepositoryService<Lecturer, LecturerFilter> lecturerRepositoryService;

    @Autowired
    private AbstractRepositoryService<Subject, SubjectFilter> subjectRepositoryService;

    private Lecturer selectedLecturer;

    public AddDataLecturerController(ScreensConfig flow) {
        super(flow);
    }

    @FXML
    public void initialize() {
        setLecturerTable();
        setLecturerTableListener();
        setSelectionModeMultiple(subjectTable);
        setButtons();
    }



    private void setButtons() {
        setChangeButton();
        setAddButton();
        setAddNewLecturerButton();
        setNavigateButtons();

        setDeleteButton();
    }

    private void setDeleteButton() {
        delete.setOnAction(event -> {
            List<Subject> lecturersSubjects = getSelectedItemsFromTable(lecturerSubjectTable);
            if (!lecturersSubjects.isEmpty()) {
                List<Subject> subjects = selectedLecturer.getSubject();
                subjects.removeAll(lecturersSubjects);
                selectedLecturer.setSubject(subjects);
                lecturerRepositoryService.add(selectedLecturer);
                setSubjectsColumns(selectedLecturer);
            } else {
                lecturerRepositoryService.delete(selectedLecturer);
                selectedLecturer = null;
                setLecturerTable();
            }
        });
    }

    private void setAddNewLecturerButton() {
        addNewLecturer.setOnAction(event -> {
            change.setDisable(true);
            lecturerTable.getSelectionModel().clearSelection();
            lecturerSubjectTable.getSelectionModel().clearSelection();
            firstName.clear();
            lastName.clear();
            universityDegree.clear();
            add.setDisable(false);
            subjectTable.getItems().clear();
            List<Subject> subjects = subjectRepositoryService.findAll();
            lecturerSubjectTable.getItems().clear();
            setColumns(subjectTable, new SubjectColumn("Przedmioty"), subjects);
        });
    }

    private void setAddButton() {
        add.setOnAction(event -> {
            change.setDisable(true);
            if (!firstName.getText().isEmpty()) {
                Lecturer lecturer = createNewLecturer();
                lecturerRepositoryService.add(lecturer);
                setLecturerTable();
            }
        });
    }

    private Lecturer createNewLecturer() {
        Lecturer lecturer = new Lecturer();
        return setLecturer(lecturer);
    }

    private Lecturer setLecturer(Lecturer lecturer) {
        lecturer.setFirstName(firstName.getText());
        lecturer.setLastName(lastName.getText());
        lecturer.setSubject(getSelectedItemsFromTable(lecturerSubjectTable));
        lecturer.setUniversityDegree(universityDegree.getText());

        List<Subject> subjects = getSelectedItemsFromTable(subjectTable);
        lecturer.setSubject(subjects);

        return lecturer;
    }

    private void setChangeButton() {
        change.setDisable(true);
        change.setOnAction(event -> {
            lecturerRepositoryService.add(setLecturer(selectedLecturer));
            setLecturerTable();
            setSubjectsColumns(selectedLecturer);
        });
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }

    private void setLecturerTable() {
        setColumns(lecturerTable, new LecturerColumn("Wykładowcy"), translateToObsList(lecturerRepositoryService.findAll()));

    }

    private void setLecturerTableListener() {
        lecturerTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Lecturer lecturer = getSelectedItemFromTable(lecturerTable);
            if (lecturer != null) {
                firstName.setText(lecturer.getFirstName());
                lastName.setText(lecturer.getLastName());
                universityDegree.setText(lecturer.getUniversityDegree());
                change.setDisable(false);
                add.setDisable(true);
                setSubjectsColumns(lecturer);
                selectedLecturer = lecturer;
            }
        });
    }

    private void setSubjectsColumns(Lecturer lecturer) {
        setColumns(lecturerSubjectTable, new SubjectColumn("Przedmioty wybrane"), lecturer.getSubject());
        SubjectFilter subjectFilter = new SubjectFilter();
        subjectFilter.setLecturer(lecturer);
        setColumns(subjectTable, new SubjectColumn("Przedmioty"), subjectRepositoryService.findAll(subjectFilter));
    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
