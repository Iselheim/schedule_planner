package pl.bolka.aleksander.schedule.planner.model.repository;

import javax.persistence.TypedQuery;

public interface CriteriaQueryRepository<T>{

	public T find(TypedQuery<T> query);
	
}
