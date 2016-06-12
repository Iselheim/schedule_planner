package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

@org.springframework.stereotype.Repository
public interface SemesterRepository extends Repository<Semester, Long>, JpaSpecificationExecutor<Semester>{
	

}
