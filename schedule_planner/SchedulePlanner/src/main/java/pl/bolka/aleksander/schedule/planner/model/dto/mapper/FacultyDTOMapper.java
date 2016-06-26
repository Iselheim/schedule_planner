package pl.bolka.aleksander.schedule.planner.model.dto.mapper;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.FacultyDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public class FacultyDTOMapper extends GenericDTOMapper<Faculty,FacultyDTO> {

    @Override
    protected FacultyDTO translateToDTO(Faculty entity) {
        FacultyDTO dto = new FacultyDTO(entity.getId());
        dto.setName(entity.getName());
        dto.setShortcut(entity.getShortcut());
        dto.setLecturers(entity.getLecturers());
        dto.setSpecialization(entity.getSpecialization());
        dto.setSubjects(entity.getSubjects());
        return dto;
    }

    @Override
    protected Faculty translateToEntity(FacultyDTO dto) {
       Faculty faculty = new Faculty();
        faculty.setId(dto.getId());
        faculty.setName(dto.getName());
        faculty.setShortcut(dto.getShortcut());
        faculty.setSubjects(dto.getSubjects());
        faculty.setSpecialization(dto.getSpecialization());
        faculty.setLecturers(dto.getLecturers());
        return faculty;
    }
}
