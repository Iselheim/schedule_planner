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
    
    public long getId();

    public void setId(long id);

    public Subject getSubject();

    public void setSubject(Subject subject);

    public Lecturer getLecturer();

    public void setLecturer(Lecturer lecturer);

    public FreeRoom getFreeRoom();

    public void setFreeRoom(FreeRoom room);

    public Day getDay(); 

    public void setDay(Day day);

    public Hour getHour();

    public void setHour(Hour hour);

    public StudentGroup getGroup() ;

    public void setGroup(StudentGroup group);
}
