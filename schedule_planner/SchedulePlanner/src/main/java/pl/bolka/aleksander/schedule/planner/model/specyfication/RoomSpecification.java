package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;
import pl.bolka.aleksander.schedule.planner.model.filter.RoomFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */

public class RoomSpecification implements Specification<FreeRoom> {

    private RoomFilter filter;

    public RoomSpecification(RoomFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<FreeRoom> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getDate() != null) {
            p.getExpressions().add(cb.equal(root.<Day>get("day").<Date>get("date"), filter.getDate()));
        }

        if (filter.getId() != null) {
            p.getExpressions().add(cb.equal(root.<Long>get("id"), filter.getId()));
        }

        if(filter.getNumber() != null){
            p.getExpressions().add(cb.equal(root.get("number"),filter.getNumber()));
        }

        return p;
    }
}
