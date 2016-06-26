package pl.bolka.aleksander.schedule.planner.model.dto;

import pl.bolka.aleksander.schedule.planner.model.entity.Lecturer;
import pl.bolka.aleksander.schedule.planner.model.entity.Specialization;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
public class FacultyDTO extends BaseDTO{

    private String name = "";

    private String shortcut = "";

    private List<Specialization> specialization;

    private List<Lecturer> lecturers;

    private List<Subject> subjects;

    public FacultyDTO(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return name;
    }
}
