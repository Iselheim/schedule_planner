package pl.bolka.aleksander.schedule.planner.model.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import pl.bolka.aleksander.schedule.planner.model.entity.Identifiable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Aleksander Bołka on 2016-06-21.
 */
public class CommonCustomRepositoryImpl<T extends Identifiable, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonCustomRepository<T, ID> {
    public CommonCustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public CommonCustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                      EntityManager entityManager) {
        super(entityInformation, entityManager);
    }


    @Override
    public List<T> findAll(Specification specification) {
        return super.findAll(specification);
    }

    @Override
    public T findOne(Specification specification) {
        return (T) super.findOne(specification);
    }

    @Override
    public T getEager(T entity) {
        T t = super.save(entity);
        t.getId();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        List<Field> fields = Arrays.asList(declaredFields);
        fields.stream().filter(field -> field.getType().equals(List.class) || field.getType().equals(Set.class)).forEach(field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(t);
                ((Collection) o).size();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return t;
    }

//    @Override
//    public T findOne(Specification<T> spec) {
//       return super.findOne(spec);
//    }

}
