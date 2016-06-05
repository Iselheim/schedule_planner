package pl.bolka.aleksander.schedule.planner.config;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import pl.bolka.aleksander.schedule.planner.controller.fx.FXController;
import pl.bolka.aleksander.schedule.planner.controller.fx.ManualSelectByGroupsController;
import pl.bolka.aleksander.schedule.planner.controller.fx.StartPageController;

@Configuration
@Lazy
public class ScreensConfig implements Observer {
	private static final Logger logger = LogManager.getLogger(ScreensConfig.class);

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String STYLE_FILE = "/pl/bolka/aleksander/schedule/planner/fx/css/main.css";

	private Stage stage;
	private Scene scene;
	private StackPane root;
	
	public ScreensConfig() {
		super();
	}

	@Bean
	@Scope("prototype")
	StartPageController getStartPageController(){
		return new StartPageController(this);
	}

	@Bean
	@Scope("prototype")
	ManualSelectByGroupsController getManualSelectByGroupsController() {
		return new ManualSelectByGroupsController(this);
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.stage = primaryStage;
	}

	public void showMainScreen() {
		root = new StackPane();
		root.getStylesheets().add(STYLE_FILE);
		root.getStyleClass().add("main-window");
		stage.setTitle("Test");
		scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setResizable(false);

		stage.setOnHiding(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});

		stage.show();
	}

	public void loadStartPageController() {
		setNode(getNode(getStartPageController()));
	}

	public void loadManualSelectByGroupsController() {
		setNode(getNode(getManualSelectByGroupsController()));
	}

	private void setNode(Node node) {
		root.getChildren().setAll(node);
	}

	// FXController getController(Class<? extends FXController> controller) {
	// try {
	// Constructor<? extends FXController> constructor =
	// controller.getConstructor(ScreensConfig.class);
	// return (FXController) constructor.newInstance(this);
	// } catch (Exception e) {
	// logger.error("Nie mo�na zbudowa� obiektu", e);
	// }
	// throw new RuntimeException("Nie mo�na zbudowa� obiektu");
	// }

	private Node getNode(final FXController controller) {
		URL location = getClass().getResource(controller.getPath());
		FXMLLoader loader = new FXMLLoader(location);
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			public Object call(Class<?> aClass) {
				return controller;
			}
		});

		try {
			return (Node) loader.load();
		} catch (Exception e) {
			logger.error("Error casting node", e);
			return null;
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void update(Observable arg0, Object arg1) {

	}

}
