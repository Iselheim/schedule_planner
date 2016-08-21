package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.stereotype.Repository;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
@Repository
public interface SpecializationRepository extends CommonCustomRepository<Specialization, Long> {
}
