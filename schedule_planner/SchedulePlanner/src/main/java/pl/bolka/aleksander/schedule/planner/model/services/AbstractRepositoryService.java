package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.filter.Filter;
import pl.bolka.aleksander.schedule.planner.model.repository.CommonCustomRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public abstract class AbstractRepositoryService<E extends Serializable, F extends Filter> {

    @Autowired
    protected CommonCustomRepository<E,Long> commonCustomRepository;

    @Transactional
    public List<E> findAll(){
        List<E> all = commonCustomRepository.findAll();
        return all;
    }

    @Transactional
    public void add(E entity){
        commonCustomRepository.save(entity);
    }

    @Transactional
    public abstract  List<E> findAll(F filter);

}
