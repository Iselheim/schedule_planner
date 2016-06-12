package pl.bolka.aleksander.schedule.planner.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

@Repository
public class SemesterRepositoryImpl implements SemesterRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long count(Specification<Semester> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Semester> findAll(Specification<Semester> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Semester> findAll(Specification<Semester> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Semester> findAll(Specification<Semester> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Semester findOne(Specification<Semester> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
