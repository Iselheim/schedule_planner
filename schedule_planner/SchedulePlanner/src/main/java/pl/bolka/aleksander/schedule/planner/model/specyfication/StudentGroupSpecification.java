package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;
import pl.bolka.aleksander.schedule.planner.model.filter.StudentGroupFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander on 2016-09-04.
 */
public class StudentGroupSpecification implements Specification<StudentGroup> {

    private StudentGroupFilter filter;

    public StudentGroupSpecification(StudentGroupFilter filter){
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<StudentGroup> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if(filter.getSemester() != null){
            p.getExpressions().add(cb.equal(root.get("semester"),filter.getSemester()));
        }

        return p;
    }
}
