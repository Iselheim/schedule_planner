package pl.bolka.aleksander.schedule.planner.model.dto.mapper;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.LecturerDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
@Service
public class LecturerDTOMapper extends GenericDTOMapper<Lecturer, LecturerDTO> {

    @Override
    protected LecturerDTO translateToDTO(Lecturer entity) {
        LecturerDTO dto = new LecturerDTO(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setFaculty(entity.getFaculty());
        dto.setSubject(entity.getSubject());
        dto.setUniversityDegree(entity.getUniversityDegree());
        return dto;
    }

    @Override
    protected Lecturer translateToEntity(LecturerDTO dto) {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(dto.getId());
        lecturer.setLastName(dto.getLastName());
        lecturer.setFirstName(dto.getFirstName());
        lecturer.setFaculty(dto.getFaculty());
        lecturer.setSubject(dto.getSubject());
        lecturer.setUniversityDegree(dto.getUniversityDegree());
        return lecturer;
    }
}
