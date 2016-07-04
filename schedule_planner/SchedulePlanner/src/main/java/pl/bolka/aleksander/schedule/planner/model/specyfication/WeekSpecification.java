package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Week;
import pl.bolka.aleksander.schedule.planner.model.filter.WeekFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander Bołka on 2016-07-04.
 */
public class WeekSpecification implements Specification<Week> {

    private WeekFilter filter;

    public WeekSpecification(WeekFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Week> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
