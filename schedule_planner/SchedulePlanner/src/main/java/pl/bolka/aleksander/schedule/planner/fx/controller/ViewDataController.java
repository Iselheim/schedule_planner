package pl.bolka.aleksander.schedule.planner.fx.controller;

import pl.bolka.aleksander.schedule.planner.fx.controller.validate.*;
import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2016-11-27.
 */
public class ViewDataController {

    private List<Semester> semesters;

    private List<StudentGroup> groups;

    private List<Subject> subjects;

    private List<Lecturer> lecturers;

    private List<FreeRoom> rooms;

    private List<Schedule> schedules;

    private Map<Long, SemesterViewTO> viewTOStructure;

    public ViewDataController() {
    }

    public void fillValidateStructure() {
        for (Semester semester : semesters) {
            SemesterViewTO semesterValidates = getSemesterValidates(semester);
            viewTOStructure = new HashMap<>();
            viewTOStructure.put(semesterValidates.getId(), semesterValidates);
        }
    }

    private SemesterViewTO getSemesterValidates(Semester semester) {
        SemesterViewTO semesterViewTO = new SemesterViewTO();
        Map<Long, StudentGroupViewTO> groupValidates = getGroupValidates(semester);
        semesterViewTO.setId(semester.getId());
        semesterViewTO.setGroups(groupValidates);
        semesterViewTO.setNumber(semester.getNumber() + "");
        semesterViewTO.setYear(semester.getYear() + "");
        return semesterViewTO;
    }


    private Map<Long, StudentGroupViewTO> getGroupValidates(Semester semester) {
        List<StudentGroup> groups = getGroups(semester);
        Map<Long, StudentGroupViewTO> studentGroupValidates = new HashMap<>();
        for (StudentGroup group : groups) {
            StudentGroupViewTO studentGroupViewTO = new StudentGroupViewTO();
            studentGroupViewTO.setId(group.getId());
            studentGroupViewTO.setSubjects(getSubjectValidates(group));
            studentGroupViewTO.setNumber(group.getNumber() + "");
            studentGroupViewTO.setSemesterNumber(group.getSemester().getNumber() + "");
            studentGroupValidates.put(group.getId(), studentGroupViewTO);
        }
        return studentGroupValidates;
    }

    private Map<Long, SubjectViewTO> getSubjectValidates(StudentGroup group) {
        List<Subject> subjects = getSubjects(group);
        Map<Long, SubjectViewTO> subjectValidates = new HashMap<>();
        for (Subject subject : subjects) {
            SubjectViewTO subjectViewTO = new SubjectViewTO();
            subjectViewTO.setId(subject.getId());
            subjectViewTO.setLecturers(getLecturerValidates(subject));
            subjectViewTO.setName(subject.getName());
            subjectViewTO.setSubjectType(subject.getSubjectType().getName());
            subjectValidates.put(subject.getId(), subjectViewTO);
        }
        return subjectValidates;
    }

    private Map<Long, LecturerViewTO> getLecturerValidates(Subject subject) {
        List<Lecturer> lecturers = getLecturers(subject);
        Map<Long, LecturerViewTO> lecturerValidates = new HashMap<>();
        for (Lecturer lecturer : lecturers) {
            LecturerViewTO lecturerViewTO = new LecturerViewTO();
            lecturerViewTO.setId(lecturer.getId());
            lecturerViewTO.setRooms(getRooms(subject));
            lecturerViewTO.setUniversityDegree(lecturer.getUniversityDegree());
            lecturerViewTO.setFirstName(lecturer.getFirstName());
            lecturerViewTO.setLastName(lecturer.getLastName());
            lecturerValidates.put(lecturer.getId(), lecturerViewTO);
        }
        return lecturerValidates;
    }

    private Map<Long, RoomViewTO> getRooms(Subject subject) {
        List<Subject> subjects = this.subjects.stream().filter(subject1 -> subject1.equals(subject)).collect(Collectors.toList());
        Map<Long, RoomViewTO> roomValidates = new HashMap<>();
        for (Subject subject1 : subjects) {
            List<FreeRoom> freeRoom = subject1.getFreeRoom();
            for (FreeRoom room : freeRoom) {
                RoomViewTO roomViewTO = new RoomViewTO();
                roomViewTO.setId(room.getId());
                roomViewTO.setWeeks(getWeeks(room));
                roomValidates.put(room.getId(), roomViewTO);
            }
        }
        return roomValidates;
    }

    private Map<Long, WeekViewTO> getWeeks(FreeRoom room) {
        Map<Long, WeekViewTO> map = new HashMap<>();
        for (Week week : room.getWeek()) {
            WeekViewTO weekViewTO = new WeekViewTO();
            weekViewTO.setId(week.getId());
            weekViewTO.setDays(getDays(week));
        }
        return map;
    }

    private Map<Long, DayViewTO> getDays(Week week) {
        Map<Long, DayViewTO> map = new HashMap<>();
        List<Day> days = week.getDays();
        for (Day day : days) {
            DayViewTO dayViewTO = new DayViewTO();
            dayViewTO.setId(day.getId());
            dayViewTO.setHours(getHours(day));
            dayViewTO.setName(day.getName());
            map.put(day.getId(), dayViewTO);
        }
        return map;
    }

    private Map<Long, HourViewTO> getHours(Day day) {
        Map<Long, HourViewTO> hourValidates = new HashMap<>();
        List<Hour> hours = day.getHour();
        for (Hour hour : hours) {
            HourViewTO hourViewTO = new HourViewTO();
            hourViewTO.setId(hour.getId());
            hourValidates.put(hour.getId(), hourViewTO);
        }
        return hourValidates;
    }

    private List<Lecturer> getLecturers(Subject subject) {
        return this.lecturers.stream().filter(lecturer -> lecturer.getSubject().equals(subject)).collect(Collectors.toList());
    }

    public void updateData(Schedule schedule) {
        //TODO
    }

    private List<StudentGroup> getGroups(Semester semester) {
        return groups.stream().filter(group -> group.getSemester().equals(semester)).collect(Collectors.toList());
    }


    private List<Subject> getSubjects(StudentGroup studentGroup) {
        return subjects.stream().filter(subject -> subject.getSemester().equals(studentGroup.getSemester())).collect(Collectors.toList());
    }


    public Map<Long, SemesterViewTO> getViewTOStructure() {
        return viewTOStructure;
    }

    public List<StudentGroupViewTO> getGroupViewTOs(SemesterViewTO semester) {
        return new ArrayList<>(viewTOStructure.get(semester.getId()).getGroups().values());
    }

    public List<SubjectViewTO> getSubjectViewTOs(StudentGroupViewTO studentGroup) {
        List<SubjectViewTO> subjectViewTOs = new ArrayList<>();
        viewTOStructure.forEach((aLong, semesterViewTO) -> {
            subjectViewTOs.addAll(semesterViewTO.getGroups().get(studentGroup).getSubjects().values());
        });
        return subjectViewTOs;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public void setGroups(List<StudentGroup> groups) {
        this.groups = groups;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void setRooms(List<FreeRoom> rooms) {
        this.rooms = rooms;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setViewTOStructure(Map<Long, SemesterViewTO> viewTOStructure) {
        this.viewTOStructure = viewTOStructure;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
