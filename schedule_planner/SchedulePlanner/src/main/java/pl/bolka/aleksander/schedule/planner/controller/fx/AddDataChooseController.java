package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataChooseController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataChoose.fxml";

    public AddDataChooseController(ScreensConfig flow) {
        super(flow);
    }
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
    private Button mainMain;

    @FXML
    public void initialize(){

    }
    @Override
    public String getPath() {
        return PATH;
    }
}
