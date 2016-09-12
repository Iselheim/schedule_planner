package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;


public class StartPageController extends FXController {

    public StartPageController(ScreensConfig flow) {
        super(flow);
    }

    private static final Logger logger = LogManager.getLogger(StartPageController.class);

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/StartPage.fxml";

    @FXML
    private Button mainWindowButtonGrupy;

    @FXML
    private Button mainWindowButtonAddData;

    @FXML
    public void initialize() {
        setButtons();

    }

    private void setButtons() {
        setSchedulePlannerController();
        setMainWindowButtonAddData();
    }

    private void setMainWindowButtonAddData() {
        mainWindowButtonAddData.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setSchedulePlannerController() {
        mainWindowButtonGrupy.setOnAction((ActionEvent event) -> flow.loadSchedulePlannerController());
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
