package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;


public class StartPageController extends FXController {

	private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/StartPage.fxml";

	public StartPageController(ScreensConfig config) {
		super(config);
	}

	@FXML
	private Button mainWindowButtonGrupy;

	@FXML
	public void initialize() {
		mainWindowButtonGrupy.setOnAction((ActionEvent event) -> {
			flow.loadManualSelectByGroupsController();
		});
	}

	@Override
	public String getPath() {
		return PATH;
	}
}
