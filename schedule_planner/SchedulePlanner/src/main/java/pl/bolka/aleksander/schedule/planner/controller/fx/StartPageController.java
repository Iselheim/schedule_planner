package pl.bolka.aleksander.schedule.planner.controller.fx;

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
        setMainWindowButtonGrupy();
        setMainWindowButtonAddData();
    }

    private void setMainWindowButtonAddData() {
        mainWindowButtonAddData.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setMainWindowButtonGrupy() {
        mainWindowButtonGrupy.setOnAction((ActionEvent event) -> flow.loadManualSelectByGroupsController());
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
