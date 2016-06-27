package pl.bolka.aleksander.schedule.planner.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.SubjectDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
@Service
public class SubjectDTOMapper extends GenericDTOMapper<Subject,SubjectDTO> {

    @Autowired
    LecturerDTOMapper lecturerDtoMapper;

    @Autowired
    RoomDTOMapper roomDTOMapper;

    @Override
    protected SubjectDTO translateToDTO(Subject entity) {
        SubjectDTO subjectDTO = new SubjectDTO(entity.getId());
        subjectDTO.setName(entity.getName());
        subjectDTO.setFaculty(entity.getFaculty());
        subjectDTO.setHours(entity.getHours());
//        subjectDTO.setLecturer(lecturerDtoMapper.getDtos(entity.getLecturer()));
        subjectDTO.setRoom(roomDTOMapper.getDtos(entity.getRoom()));
        subjectDTO.setSubjectType(entity.getSubjectType());
        subjectDTO.setSemester(entity.getSemester());
        return subjectDTO;
    }

    @Override
    protected Subject translateToEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setFaculty(dto.getFaculty());
        subject.setHours(dto.getHours());
        subject.setLecturer(lecturerDtoMapper.getEntitys(dto.getLecturer()));
        subject.setRoom(roomDTOMapper.getEntitys(dto.getRoom()));
        subject.setSubjectType(dto.getSubjectType());
        subject.setSemester(dto.getSemester());
        return subject;
    }
}
