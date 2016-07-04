package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.filter.DayFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class DaySpecification implements Specification<Day> {

    private DayFilter filter;

    public DaySpecification(DayFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Day> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getDate() != null) {
            p.getExpressions().add(cb.equal(root.get("date"), filter.getDate()));
        }

        return p;
    }
}
