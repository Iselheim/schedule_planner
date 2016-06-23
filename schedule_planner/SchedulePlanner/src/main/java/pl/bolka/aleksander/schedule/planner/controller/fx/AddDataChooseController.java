package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataChooseController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataChoose.fxml";

    @FXML
    private Button addLecturers;

    @FXML
    private Button addSubject;

    @FXML
    private Button addSpecialization;

    @FXML
    private Button addFaculty;

    @FXML
    private Button addSemester;

    @FXML
    private Button addGroup;

    @FXML
    private Button addDay;

    @FXML
    private Button addRoom;

    @FXML
    private Button mainMenu;

    @FXML
    public void initialize() {
        setButtons();
    }

    private void setButtons() {
        setAddLecturerButton();
        setMainMainButton();
    }

    private void setAddLecturerButton() {
        addLecturers.setOnAction(event -> flow.loadAddDataLecturerController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    public AddDataChooseController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
