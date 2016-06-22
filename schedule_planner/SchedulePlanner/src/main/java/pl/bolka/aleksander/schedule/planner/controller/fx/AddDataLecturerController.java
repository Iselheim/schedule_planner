package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataLecturerController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataLecturers.fxml";

    @FXML
    private TableView<Lecturer> lecturerTable;

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

    @FXML
    public void initialize() {

    }

    public AddDataLecturerController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
