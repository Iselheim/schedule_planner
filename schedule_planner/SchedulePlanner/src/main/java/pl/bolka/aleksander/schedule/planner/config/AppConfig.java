package pl.bolka.aleksander.schedule.planner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import({ PersistenceConfig.class, ScreensConfig.class })
@ComponentScan("pl.bolka.aleksander.schedule.planner")
public class AppConfig {


}