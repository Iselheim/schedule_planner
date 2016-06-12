package pl.bolka.aleksander.schedule.planner.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.bolka.aleksander.schedule.planner.model.services.SemesterSearchService;

@Component
public class Test {

	private static final Logger logger = LogManager.getLogger(Test.class);
	
	@Autowired
	@Qualifier("repositorySemesterSearchService")
	private SemesterSearchService semesterSearchService;

	public void testujemy() {
		logger.error(
				"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n                 Zaczynam szukać                   \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		semesterSearchService.findBySearchTerm(1l);
		logger.error(
				"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n                 Nawet działa                   \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
