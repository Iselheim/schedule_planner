package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.dto.BaseDTO;
import pl.bolka.aleksander.schedule.planner.model.dto.mapper.GenericDTOMapper;
import pl.bolka.aleksander.schedule.planner.model.repository.CommonCustomRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public abstract class AbstractRepositoryService<E extends Serializable,DTO extends BaseDTO> {

    @Autowired
    protected CommonCustomRepository<E,Long> commonCustomRepository;

    @Autowired
    protected GenericDTOMapper<E,DTO> genericDTOMapper;

    @Transactional
    public List<DTO> findAll(){
        List<E> allLecturers = commonCustomRepository.findAll();
        List<DTO> dtos = genericDTOMapper.getDtos(allLecturers);
        return dtos;
    }

//    @Transactional
//    public List<DTO> findAllEager(){
//        List<E> allLecturers = commonCustomRepository.findAll();
////        getEagerList(allLecturers);
//        List<DTO> dtos = genericDTOMapper.getDtos(allLecturers);
//        return dtos;
//    }

    @Transactional
    public void add(DTO dto){
//        E entity = genericDTOMapper.getEntity(dto);
        commonCustomRepository.save(genericDTOMapper.getEntity(dto));
    }

//    @Transactional
//    public abstract DTO getEager(DTO dto);
//
//    private void getEagerList(List<E> entity){
//        for(E e: entity){
//            Hibernate.initialize(e);
//        }
//    }
}
