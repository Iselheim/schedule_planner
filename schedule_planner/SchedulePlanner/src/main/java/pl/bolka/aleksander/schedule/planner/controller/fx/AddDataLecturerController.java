package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.model.dto.LecturerDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.services.LecturerRepositoryService;
import pl.bolka.aleksander.schedule.planner.util.ObservableListMapper;

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
    private TableView<Subject> facultyTable;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button mainMenu;

    @Autowired
    private LecturerRepositoryService lecturerRepositoryService;

    @FXML
    public void initialize() {
        setLecturerTable();

        setNavigateButtons();
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
        lecturerTable.setItems(ObservableListMapper.translateToObsList(lecturerRepositoryService.findAllLecturers()));

    }

    public AddDataLecturerController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
