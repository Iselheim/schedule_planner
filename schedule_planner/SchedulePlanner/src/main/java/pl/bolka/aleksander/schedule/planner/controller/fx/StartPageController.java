package pl.bolka.aleksander.schedule.planner.controller.fx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.model.repository.TestDataGeneratorRepository;
import pl.bolka.aleksander.schedule.planner.model.services.RepositorySemesterSearchService;
import pl.bolka.aleksander.schedule.planner.util.Test;


public class StartPageController extends FXController {

	public StartPageController(ScreensConfig flow) {
		super(flow);
	}

	private static final Logger logger = LogManager.getLogger(StartPageController.class);
	
	private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/StartPage.fxml";
	
	@Autowired
	@Qualifier("testDataGeneratorRepositoryImpl")
	TestDataGeneratorRepository testDataGeneratorRepository;
	
	@FXML
	private Button mainWindowButtonGrupy;

	@FXML
	public void initialize() {
		mainWindowButtonGrupy.setOnAction((ActionEvent event) -> {
			flow.loadManualSelectByGroupsController();
			testDataGeneratorRepository.generateTestData();
		});
	}

	@Override
	public String getPath() {
		return PATH;
	}
}
