package pl.bolka.aleksander.schedule.planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.bolka.aleksander.schedule.planner.util.Test;

@Configuration
//@Import({ PersistenceConfig.class, ScreensConfig.class })
@ComponentScan("pl.bolka.aleksander.schedule.planner")
public class AppConfig {

	

//	@Bean
//	public Database getDatabase() {
//		return new Database();
//	}
//
//	@Bean
//	public Test getTest(){
//		return new Test();
//	}
	
	// @Bean
	// public ScreensConfig getScreensConfig() {
	// return new ScreensConfig();
	// }

}