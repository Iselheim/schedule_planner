package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;
import pl.bolka.aleksander.schedule.planner.model.filter.SubjectFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Aleksander Bołka on 2016-06-27.
 */
public class SubjectSpecyfication implements Specification<Subject> {

    private SubjectFilter filter;

    public SubjectSpecyfication(SubjectFilter filter) {
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if(filter.getLecturer() != null){
            p.getExpressions()
                    .add(cb.isNotMember(filter.getLecturer(),root.get("lecturer")));
        }

//        if (filter.getName() != null) {
//            p.getExpressions()
//                    .add(cb.equal(root.get("name"), filter.getName()));
//        }
//
//        if (filter.getSurname() != null && filter.getAge() != null) {
//            p.getExpressions().add(
//                    cb.and(cb.equal(root.get("surname"), filter.getSurname()),
//                            cb.equal(root.get("age"), filter.getAge())));
//        }

        return p;

    }

}