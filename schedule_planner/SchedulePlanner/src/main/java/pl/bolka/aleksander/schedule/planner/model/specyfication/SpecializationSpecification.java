package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;
import pl.bolka.aleksander.schedule.planner.model.filter.SpecializationFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */

public class SpecializationSpecification implements Specification<Specialization> {

    private SpecializationFilter filter;

    public SpecializationSpecification(SpecializationFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Specialization> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        return p;
    }
}
