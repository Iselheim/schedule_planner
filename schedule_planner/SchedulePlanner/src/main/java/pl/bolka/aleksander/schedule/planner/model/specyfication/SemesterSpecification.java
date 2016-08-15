package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.filter.SemesterFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SemesterSpecification implements Specification<Semester> {

    private SemesterFilter filter;

    public SemesterSpecification(SemesterFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Semester> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        return p;
    }
}
