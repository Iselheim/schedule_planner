package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;
import pl.bolka.aleksander.schedule.planner.model.filter.ScheduleFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander on 2016-09-12.
 */
public class ScheduleSpecification implements Specification<Schedule> {

    private ScheduleFilter filter;

    public ScheduleSpecification(ScheduleFilter scheduleFilter){
        this.filter = scheduleFilter;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.disjunction();

        return p;
    }
}
