package pl.bolka.aleksander.schedule.planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ScreensConfig.class, PersistenceConfig.class })

public class AppConfig {

	// @Bean
	// LanguageModel languageModel() {
	// return new LanguageModel();
	// }+

	@Bean
	Database getDatabase() {
		return new Database();
	}
	
	
	
	
}