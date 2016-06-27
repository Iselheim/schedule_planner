package pl.bolka.aleksander.schedule.planner.model.dto;

import pl.bolka.aleksander.schedule.planner.model.entity.Faculty;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.entity.SubjectType;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
public class SubjectDTO extends BaseDTO {

    private String name;

    private Semester semester;

    private Faculty faculty;

    private SubjectType subjectType;

    private List<LecturerDTO> lecturer;

    private List<RoomDTO> room;

    private int hours;

    public SubjectDTO(){
        super();
    }

    public SubjectDTO(Long id){
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public List<LecturerDTO> getLecturer() {
        return lecturer;
    }

    public void setLecturer(List<LecturerDTO> lecturer) {
        this.lecturer = lecturer;
    }

    public List<RoomDTO> getRoom() {
        return room;
    }

    public void setRoom(List<RoomDTO> room) {
        this.room = room;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
