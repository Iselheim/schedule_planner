package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.dto.LecturerDTO;
import pl.bolka.aleksander.schedule.planner.model.dto.mapper.GenericDTOMapper;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.repository.LecturerRepository;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */

@Service
public class LecturerRepositoryService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private GenericDTOMapper<Lecturer,LecturerDTO> genericDTOMapper;

    @Transactional
    public void addLecturer(LecturerDTO lecturerDto){
        Lecturer entity = genericDTOMapper.getEntity(lecturerDto);
        lecturerRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<LecturerDTO> findAllLecturers(){
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        List<LecturerDTO> dtos = genericDTOMapper.getDtos(allLecturers);
        return dtos;
    }
}
