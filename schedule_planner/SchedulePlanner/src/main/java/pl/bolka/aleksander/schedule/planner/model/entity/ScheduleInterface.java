/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bolka.aleksander.schedule.planner.model.entity;

/**
 *
 * @author Aleksander Bołka
 */
public interface ScheduleInterface {
    
    Long getId();

    void setId(long id);

    Subject getSubject();

    void setSubject(Subject subject);

    Lecturer getLecturer();

    void setLecturer(Lecturer lecturer);

    FreeRoom getFreeRoom();

    void setFreeRoom(FreeRoom room);

    Day getDay();

    void setDay(Day day);

    Hour getHour();

    void setHour(Hour hour);

    StudentGroup getGroup() ;

    void setGroup(StudentGroup group);
}
