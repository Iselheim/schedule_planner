package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.filter.HourFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class HourSpecification implements Specification<Hour> {

    private HourFilter filter;

    public HourSpecification(HourFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Hour> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();
        return p;
    }
}
