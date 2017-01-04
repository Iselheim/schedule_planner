package pl.bolka.aleksander.schedule.planner.fx.controller;

import pl.bolka.aleksander.schedule.planner.fx.controller.validate.*;
import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

    private List<Week> weeks;

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
                roomViewTO.setNumber(room.getNumber());
                roomViewTO.setRoomSpace(room.getRoomSpace() + "");
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
            weekViewTO.setWeekOfSemester(week.getWeekOfSemester() + "");
            weekViewTO.setDates(getDates(week.getDays()));
            map.put(week.getId(), weekViewTO);
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
            hourViewTO.setTimeFrom(hour.getTimeFrom().toString());
            hourViewTO.setTimeTo(hour.getTimeTo().toString());
            hourValidates.put(hour.getId(), hourViewTO);
        }
        return hourValidates;
    }

    private List<Lecturer> getLecturers(Subject subject) {
        List<Lecturer> lecturers = this.lecturers.stream().filter(lecturer -> lecturer.getSubject().contains(subject)).collect(Collectors.toList());
        return lecturers;
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

    public List<StudentGroupViewTO> getGroupViewTOs(StudentGroupViewTO semester) {
        return new ArrayList<>(viewTOStructure.get(semester.getId()).getGroups().values());
    }

    public List<SubjectViewTO> getSubjectViewTOs(StudentGroupViewTO studentGroup) {
        List<SubjectViewTO> subjectViewTOs = new ArrayList<>();
        viewTOStructure.forEach((aLong, semesterViewTO) -> {
            subjectViewTOs.addAll(semesterViewTO.getGroups().get(studentGroup).getSubjects().values());
        });
        return subjectViewTOs;
    }

    public Semester getSemester(SemesterViewTO selectedItemFromTable) {
        return semesters.stream().filter(semester -> semester.getId().equals(selectedItemFromTable.getId())).collect(Collectors.toList()).get(0);
    }

    public StudentGroup getStudentGroups(StudentGroupViewTO selectedItemsFromTable) {
        return groups.stream().filter(studentGroup -> studentGroup.getId().equals(selectedItemsFromTable.getId())).collect(Collectors.toList()).get(0);
    }

    public Subject getSubject(SubjectViewTO selectedItemFromTable) {
        return subjects.stream().filter(subject -> subject.getId().equals(selectedItemFromTable.getId())).collect(Collectors.toList()).get(0);
    }

    public Lecturer getLecturer(LecturerViewTO lecturerViewTO) {
        return lecturers.stream().filter(lecturer -> lecturer.getId().equals(lecturerViewTO.getId())).collect(Collectors.toList()).get(0);
    }

    public FreeRoom getRoom(RoomViewTO roomViewTO) {
        return rooms.stream().filter(room -> room.getId().equals(roomViewTO.getId())).collect(Collectors.toList()).get(0);
    }

    public List<Week> getWeeks(List<WeekViewTO> weekViewTOS) {
        List<Week> weekList = new ArrayList<>();
        weekViewTOS.forEach(weekViewTO -> {
            weeks.forEach(week -> {
                if (week.getId().equals(weekViewTO.getId())) {
                    weekList.add(week);
                }
            });
        });
        return weekList;
    }

    //TODO refaktor
    public Schedule getSchedule(SemesterViewTO semesterViewTO, StudentGroupViewTO studentGroup, SubjectViewTO subjectViewTO, LecturerViewTO lecturerViewTO, RoomViewTO roomViewTO, List<WeekViewTO> weekViewTOS, DayViewTO dayViewTO, List<HourViewTO> hourViewTOS) {
        Schedule schedule = new Schedule();
        StudentGroupViewTO studentGroupViewTO = viewTOStructure.get(semesterViewTO.getId())
                .getGroups().get(studentGroup.getId());
        LecturerViewTO lecturerFromStructure = studentGroupViewTO
                .getSubjects().get(subjectViewTO.getId())
                .getLecturers().get(lecturerViewTO.getId());
        Map<Long, WeekViewTO> weeks = lecturerFromStructure
                .getRooms().get(roomViewTO.getId())
                .getWeeks();
        Map<PlanType, List<WeekViewTO>> idsToRemove = setTimeOnSchedule(weekViewTOS, dayViewTO, hourViewTOS, weeks);

        removeHoursDaysAndHoursFromStudentGroup(studentGroupViewTO, idsToRemove);

        schedule.setFreeRoom(getRoom(roomViewTO));
        schedule.setLecturer(getLecturer(lecturerViewTO));
        schedule.setSubject(getSubject(subjectViewTO));
        schedule.setStudentGroup(getStudentGroups(studentGroup));
        schedule.setSemester(getSemester(semesterViewTO));
        List<Week> weekList = getWeeks(weekViewTOS);
        schedule.setWeek(weekList);
        List<Day> dayList = getDays(weekList, dayViewTO);
        schedule.setDay(dayList);
        List<Hour> hourList = getHours(hourViewTOS, dayList);
        schedule.setHour(hourList);

        if (lecturerFromStructure.getRooms().get(roomViewTO.getId()).getWeeks().isEmpty()) {
            lecturerFromStructure.getRooms().remove(roomViewTO.getId());
        }

        studentGroupViewTO.getSubjects().remove(subjectViewTO.getId());

        if (studentGroupViewTO.getSubjects().isEmpty()) {
            viewTOStructure.get(semesterViewTO.getId()).getGroups().remove(studentGroup.getId());
        }

        if (viewTOStructure.get(semesterViewTO.getId()).getGroups().isEmpty()) {
            viewTOStructure.remove(semesterViewTO.getId());
        }
        return schedule;
    }

    private List<Hour> getHours(List<HourViewTO> hourViewTOS, List<Day> dayList) {
        List<Hour> hourList = new ArrayList<>();
        dayList.get(0).getHour().forEach(hour -> {
            hourViewTOS.forEach(hourViewTO -> {
                if (hourViewTO.getId().equals(hour.getId())) {
                    hourList.add(hour);
                }
            });
        });
        return hourList;
    }

    private List<Day> getDays(List<Week> weekList, DayViewTO dayViewTO) {
        List<Day> days = new ArrayList<>();
        for (Week week : weekList) {
            week.getDays().forEach(day -> {
                if (day.getName().equals(dayViewTO.getName())) {
                    days.add(day);
                }
            });
        }
        return days;
    }

    private Map<PlanType, List<WeekViewTO>> setTimeOnSchedule(List<WeekViewTO> weekViewTOS, DayViewTO dayViewTO, List<HourViewTO> hourViewTOS, Map<Long, WeekViewTO> weeks) {
        Map<PlanType, List<WeekViewTO>> idsToRemove = new HashMap<>();
        idsToRemove.computeIfAbsent(PlanType.HOUR, k -> new ArrayList<>());
        idsToRemove.computeIfAbsent(PlanType.DAY, k -> new ArrayList<>());
        idsToRemove.computeIfAbsent(PlanType.WEEK, k -> new ArrayList<>());
        weeks.forEach((aLong, weekViewTO) -> {
            if (weekViewTOS.contains(weekViewTO)) {
                if (weekViewTO.getDays().get(dayViewTO.getId()) != null) {
                    for (HourViewTO hourViewTO : hourViewTOS) {
                        setScheduleTime(dayViewTO, hourViewTO, weekViewTO);
                        idsToRemove.get(PlanType.HOUR).add(getWeekWithHourToRemove(dayViewTO, hourViewTO,weekViewTO));
                    }
                    if (weekViewTO.getDays().get(dayViewTO.getId()).getHours().isEmpty()) {
                        idsToRemove.get(PlanType.DAY).add(getWeekWithDayToRemove(dayViewTO,weekViewTO));
                    }
                    if (weekViewTO.getDays().isEmpty()) {
                        idsToRemove.get(PlanType.WEEK).add(getWeekToRemove(weekViewTO));
                    }
                }
            }
        });
        return idsToRemove;
    }

    private WeekViewTO getWeekToRemove(WeekViewTO weekViewTO) {
        WeekViewTO weekToRemove = new WeekViewTO();
        weekToRemove.setId(weekViewTO.getId());
        return weekToRemove;
    }

    private WeekViewTO getWeekWithDayToRemove(DayViewTO dayViewTO, WeekViewTO weekViewTO) {
        Map<Long, DayViewTO> daysToRemove = new HashMap<>();
        DayViewTO dayViewTO1 = getDayViewTO(dayViewTO);
        daysToRemove.put(dayViewTO.getId(),dayViewTO1);

        WeekViewTO weekWithDayToRemove = getWeekToRemove(weekViewTO);
        weekWithDayToRemove.setDays(daysToRemove);
        return weekWithDayToRemove;
    }

    private WeekViewTO getWeekWithHourToRemove(DayViewTO dayViewTO, HourViewTO hourViewTO, WeekViewTO weekViewTO) {
        Map<Long,HourViewTO> hourToRemove = new HashMap<>();
        hourToRemove.put(hourViewTO.getId(), new HourViewTO());

        Map<Long, DayViewTO> daysWithHourToRemove = new HashMap<>();
        DayViewTO dayViewTO1 = getDayViewTO(dayViewTO);
        dayViewTO1.setHours(hourToRemove);
        daysWithHourToRemove.put(dayViewTO.getId(),dayViewTO1);

        WeekViewTO weekWthHourToRemove = getWeekToRemove(weekViewTO);
        weekWthHourToRemove.setDays(daysWithHourToRemove);
        return weekWthHourToRemove;
    }

    private DayViewTO getDayViewTO(DayViewTO dayViewTO) {
        DayViewTO dayViewTO1 = new DayViewTO();
        dayViewTO1.setId(dayViewTO.getId());
        return dayViewTO1;
    }

    private void removeHoursDaysAndHoursFromStudentGroup(StudentGroupViewTO studentGroupViewTO, Map<PlanType, List<WeekViewTO>> idsToRemove) {
        Map<Long, SubjectViewTO> subjects = studentGroupViewTO.getSubjects();
        subjects.forEach((subjectId, subjectViewTO) -> {
            subjectViewTO.getLecturers().forEach((LecturerId, lecturerViewTO) -> {
                lecturerViewTO.getRooms().forEach((roomId, roomViewTO) -> {
                    idsToRemove.get(PlanType.WEEK).forEach(idToRemove -> roomViewTO.getWeeks().remove(idToRemove.getId()));
                    roomViewTO.getWeeks().forEach((weekId, weekViewTO) -> {
                        List<WeekViewTO> weekViewTOS = idsToRemove.get(PlanType.DAY);
                        if (weekViewTOS.contains(weekViewTO)) {
                            weekViewTOS.forEach(idToRemove -> {
                                idToRemove.getDays().forEach((dayId, dayViewTO) -> {
                                    weekViewTO.getDays().remove(dayId);
                                });

                            });
                        }
                        weekViewTO.getDays().forEach((dayId, dayViewTO) -> {
                            List<WeekViewTO> weeksWithHoursToRemove = idsToRemove.get(PlanType.HOUR);
                            if (weeksWithHoursToRemove.contains(weekViewTO)) {
                                weeksWithHoursToRemove.forEach(weekWithHoursToRemove -> {
                                    Map<Long, DayViewTO> daysWithHourToRemove = weekWithHoursToRemove.getDays();
                                    if (daysWithHourToRemove.containsKey(dayViewTO.getId())) {
                                        daysWithHourToRemove.forEach((dayId2, dayViewTO1) -> {
                                            Map<Long, HourViewTO> hoursToRemove = dayViewTO1.getHours();
                                            hoursToRemove.forEach((aLong, hourViewTO) -> dayViewTO.getHours().remove(aLong));

                                        });
                                    }
                                });
                            }
                        });
                    });
                });
            });
        });
    }

    private void setScheduleTime(DayViewTO dayViewTO, HourViewTO hourViewTO, WeekViewTO weekViewTO) {
        for (FreeRoom room : rooms) {
            List<Week> week = room.getWeek();
            for (Week week1 : week) {
                if (week1.getId().equals(weekViewTO.getId())) {
                    List<Day> days = week1.getDays();
                    for (Day day : days) {
                        if (day.getId().equals(dayViewTO.getId())) {
                            List<Hour> hour = day.getHour();
                            for (Hour hour1 : hour) {
                                if (hour1.getId().equals(hourViewTO.getId())) {
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private String getDates(List<Day> days) {
        Date from = null;
        Date to = null;
        for (Day day : days) {
            Date date = day.getDate();
            if (from == null) {
                from = date;
                to = date;
            }
            if (from.compareTo(date) > 0) {
                from = date;
            }
            if (to.compareTo(date) < 0) {
                to = date;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
        return simpleDateFormat.format(from) + " - " + simpleDateFormat.format(to);
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

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
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
