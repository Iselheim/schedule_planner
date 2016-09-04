package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.filter.HourFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.LecturerFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander on 2016-09-04.
 */
public class LecturerSpecification implements Specification<Lecturer> {

    private LecturerFilter filter;

    public LecturerSpecification(LecturerFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Lecturer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();
        return p;
    }
}
