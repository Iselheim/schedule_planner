package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void addLecturer(Lecturer lecturer){
        lecturerRepository.save(lecturer);
    }

    @Transactional(readOnly = true)
    public List<Lecturer> findAllLecturers(){
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        return allLecturers;
    }
}
