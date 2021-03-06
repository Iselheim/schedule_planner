package pl.bolka.aleksander.schedule.planner.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.bolka.aleksander.schedule.planner.fx.controller.*;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

@Configuration
public class ScreensConfig implements Observer {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final String STYLE_FILE = "/pl/bolka/aleksander/schedule/planner/fx/css/main.css";
    private static final Logger logger = LogManager.getLogger(ScreensConfig.class);
    private ApplicationContext context;

    private Stage stage;
    private Scene scene;
    private StackPane root;

    public ScreensConfig() {
        super();
    }


    @Bean
    @Scope("prototype")
    public StartPageController getStartPageController() {
        return new StartPageController(this);
    }

    public void loadStartPageController() {
        setNode(getNode(getStartPageController()));
    }

    @Bean
    @Scope("prototype")
    public ManualSelectByGroupsController getManualSelectByGroupsController() {
        return new ManualSelectByGroupsController(this);
    }

    @Bean
    @Scope("prototype")
    public AddDataChooseController getAddDataChooseController() {
        return new AddDataChooseController(this);
    }

    public void loadAddDataChooseController() {
        setNode(getNode(getAddDataChooseController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataLecturerController getAddDataLecturerController() {
        return new AddDataLecturerController(this);
    }

    public void loadAddDataLecturerController() {
        setNode(getNode(getAddDataLecturerController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataRoomController getAddDataRoomController() {
        return new AddDataRoomController(this);
    }

    public void loadAddDataRoomController() {
        setNode(getNode(getAddDataRoomController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataSubjectController getAddDataSubjectController() {
        return new AddDataSubjectController(this);
    }

    public void loadAddDataSubjectController() {
        setNode(getNode(getAddDataSubjectController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataSemesterController getAddDataSemesterController() {
        return new AddDataSemesterController(this);
    }

    public void loadAddDataSemesterController() {
        setNode(getNode(getAddDataSemesterController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataSpecializationController getAddDataSpecializationController() {
        return new AddDataSpecializationController(this);
    }

    public void loadAddDataSpecializationController() {
        setNode(getNode(getAddDataSpecializationController()));
    }

    @Bean
    @Scope("prototype")
    public AddDataStudentGroupController getAddDataStudentGroupController() {
        return new AddDataStudentGroupController(this);
    }

    public void loadAddDataStudentGroupController() {
        setNode(getNode(getAddDataStudentGroupController()));
    }

    @Bean
    @Scope("prototype")
    public SchedulePlannerController getSchedulePlannerController(){
        return new SchedulePlannerController(this);
    }

    public void loadSchedulePlannerController(){
        setNode(getNode(getSchedulePlannerController()));
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

        stage.setOnHiding(event -> System.exit(0));

        stage.show();
    }

    private void setNode(Node node) {
        root.getChildren().setAll(node);
    }

    private Node getNode(final FXController controller) {
        URL location = getClass().getResource(controller.getPath());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setControllerFactory(aClass -> controller);

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

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void loadManualSelectByGroupsController() {
        setNode(getNode(getManualSelectByGroupsController()));
    }


}
