package pl.bolka.aleksander.schedule.planner.main;

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import pl.bolka.aleksander.schedule.planner.config.AppConfig;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

//@SpringBootApplication
public class Main extends Application {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		logger.info("Starting application");

		Platform.setImplicitExit(true);

		try (GenericApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfig.class);) {

			// AutowireCapableBeanFactory beanFactory =
			// springContext.getAutowireCapableBeanFactory();
			// beanFactory.autowireBean(this);
			ScreensConfig flow = springContext.getBean(ScreensConfig.class);
			flow.setPrimaryStage(stage);
			flow.showMainScreen();
			flow.loadStartPageController();
		} catch (Throwable e) {
			logger.error("Exception occurs in loading Spring context: ", e);
		}
	}
}
