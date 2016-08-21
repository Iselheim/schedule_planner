package pl.bolka.aleksander.schedule.planner.model.mapper;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.BaseDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
@Service
public abstract class GenericDTOMapper<E extends Serializable,DTO extends BaseDTO> {

    protected abstract DTO translateToDTO(E entity);

    private List<DTO> translateToDTOs(List<E> entitys){
        ArrayList<DTO> dtos = entitys.stream().map(this::translateToDTO).collect(Collectors.toCollection(ArrayList::new));
        return dtos;
    }

    protected abstract E translateToEntity(DTO dto);

    private List<E> translateToEntitys(List<DTO> dtos){
        ArrayList<E> entitys = dtos.stream().map(this::translateToEntity).collect(Collectors.toCollection(ArrayList::new));
        return entitys;
    }

    public DTO getDto(E entity){
        return translateToDTO(entity);
    }

    public E getEntity(DTO dto){
        return translateToEntity(dto);
    }

    public List<DTO> getDtos(List<E> entitys){
        return translateToDTOs(entitys);
    }

    public List<E> getEntitys(List<DTO> dtos){
        return translateToEntitys(dtos);
    }
}