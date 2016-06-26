package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.model.dto.FacultyDTO;
import pl.bolka.aleksander.schedule.planner.model.dto.LecturerDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataLecturerController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataLecturers.fxml";

    private static final Logger logger = LogManager.getLogger(AddDataLecturerController.class);


    @FXML
    private TableView<LecturerDTO> lecturerTable;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField universityDegree;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private ComboBox facultyChooser;

    @FXML
    private Button add;

    @FXML
    private Button change;

    @FXML
    private Button back;

    @FXML
    private Button mainMenu;

    @Autowired
    private AbstractRepositoryService<Lecturer, LecturerDTO> lecturerRepositoryService;

    @Autowired
    private AbstractRepositoryService<Faculty,FacultyDTO> facultyRepositoryService;

    @FXML
    public void initialize() {
        setLecturerTable();
        //TODO wywalic wydzial z encji wykladowca, wykladowcy nie sa podpisani po wydzial
        setButtons();
    }

    private void setButtons() {
        setChangeButton();
        setAddButton();
        setNavigateButtons();
    }

    private void setAddButton() {
        add.setOnAction(event -> change.setDisable(true));
    }

    private void setChangeButton() {
        change.setDisable(true);
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }


    private void setLecturerTable() {
        lecturerTable.getColumns().clear();
        TableColumn<LecturerDTO, String> lecturerColumn = new TableColumn<>("Wykładowcy");
        lecturerColumn.setPrefWidth(lecturerTable.getPrefWidth());
        lecturerColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getUniversityDegree() + " " + p.getValue().getFirstName() + " " + p.getValue().getLastName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        lecturerTable.getColumns().setAll(lecturerColumn);
        lecturerTable.setItems(translateToObsList(lecturerRepositoryService.findAll()));

        setLecturerTableListener();

    }

    private void setLecturerTableListener() {
        lecturerTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            LecturerDTO lecturerDTO = getDTOFromTable(lecturerTable);
            firstName.appendText(lecturerDTO.getFirstName());
            lastName.appendText(lecturerDTO.getLastName());
            universityDegree.appendText(lecturerDTO.getUniversityDegree());
            change.setDisable(false);
            setSubjectTable();
            List<FacultyDTO> facultyDTOs = facultyRepositoryService.findAll();
            facultyChooser.setItems(translateToObsList(facultyDTOs));
            facultyChooser.getSelectionModel().selectFirst();
        });
    }

    private void setSubjectTable() {
        subjectTable.getColumns().clear();
        TableColumn<Subject, String> subjectColumn = new TableColumn<>("Przedmioty");
        subjectColumn.setPrefWidth(subjectTable.getPrefWidth());
        subjectColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getName() + "\n" + p.getValue().getSubjectType().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        subjectTable.getColumns().setAll(subjectColumn);
        LecturerDTO lecturerDTO = getDTOFromTable(lecturerTable);
        subjectTable.setItems(translateToObsList(lecturerDTO.getSubject()));
    }


    public AddDataLecturerController(ScreensConfig flow) {
        super(flow);
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
