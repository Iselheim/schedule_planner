package pl.bolka.aleksander.schedule.planner.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.bolka.aleksander.schedule.planner.model.services.SemesterSearchService;
import pl.bolka.aleksander.schedule.planner.util.Test;
import pl.bolka.aleksander.schedule.planner.util.TestDataGenerator;

@Repository
public class TestDataGeneratorRepositoryImpl implements TestDataGeneratorRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional("jpaTransactionManager")
	@Override
	public void generateTestData() {
		testujemy();
		TestDataGenerator dataGenerator = new TestDataGenerator();
		List<Object> data = dataGenerator.addToDatabase();
		for (Object object : data) {
			entityManager.persist(object);
		}

	}

	private static final Logger logger = LogManager.getLogger(TestDataGeneratorRepositoryImpl.class);

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
