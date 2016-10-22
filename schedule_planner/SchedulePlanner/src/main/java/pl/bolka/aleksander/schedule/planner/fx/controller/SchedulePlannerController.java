package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.*;
import pl.bolka.aleksander.schedule.planner.model.entity.*;
import pl.bolka.aleksander.schedule.planner.model.filter.*;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.sql.Time;
import java.util.*;

/**
 * Created by Aleksander on 2016-09-12.
 */
public class SchedulePlannerController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/SchedulePlanner.fxml";

    @FXML
    public TableView<Semester> semesterTable;

    @FXML
    public TableView<StudentGroup> groupTable;

    @FXML
    public TableView<Subject> subjectTable;

    @FXML
    public TableView<Lecturer> lecturerTable;

    @FXML
    public TableView<FreeRoom> roomsTable;

    @FXML
    public TableView<Week> weekTable;

    @FXML
    public TableView<Day> dayTable;

    @FXML
    public TableView<Hour> hourTable;

    @FXML
    public TableView<Schedule> scheduleTable;

    @FXML
    public Button add;

    @FXML
    public Button save;

    @FXML
    public Button exportToPdf;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @Autowired
    private AbstractRepositoryService<Semester, SemesterFilter> semesterRepositoryService;

    @Autowired
    private AbstractRepositoryService<StudentGroup, StudentGroupFilter> studentGroupRepositoryService;

    @Autowired
    private AbstractRepositoryService<Subject, SubjectFilter> subjectRepositoryService;

    @Autowired
    private AbstractRepositoryService<Schedule, ScheduleFilter> scheduleRepositoryService;

    @Autowired
    private AbstractRepositoryService<Lecturer, LecturerFilter> lecturerRepositoryService;

    @Autowired
    private AbstractRepositoryService<FreeRoom, RoomFilter> roomRepositoryService;

    @Autowired
    private AbstractRepositoryService<Week, WeekFilter> weekRepositoryService;

    @Autowired
    private AbstractRepositoryService<Day, DayFilter> dayRepositoryService;

    @Autowired
    private AbstractRepositoryService<Hour, HourFilter> hourRepositoryService;

    private List<Schedule> plan;

    public SchedulePlannerController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public void initialize() {
        setButtons();

        setSemesterTable();
        setTableListeners();
        setMultiSelection();

        plan = new ArrayList<>();
    }

    private void setButtons() {
        setNavigationButtons();

        setAddButton();
        setSaveButton();
    }

    private void setSaveButton() {
        save.setOnAction(event -> {
//            scheduleRepositoryService.addAll(plan);
            for (Schedule row : plan) {
                BusyRoom busyRoom = row.getBusyRoom();
                RoomFilter filter = new RoomFilter();
                filter.setNumber(busyRoom.getNumber());
                FreeRoom freeRoom = roomRepositoryService.findOne(filter);
                List<Week> weeksFromRoom = freeRoom.getWeek();
                List<Week> weeksFromPlan = busyRoom.getWeek();
                removeHour(weeksFromRoom, weeksFromPlan);
                roomRepositoryService.save(freeRoom);
                setSemesterTable();
            }
        });
    }

    private void removeHour(List<Week> weeksFromRoom, List<Week> weeksFromPlan) {
        for (Week weekFromRoom : weeksFromRoom) {
            for (Week weekFromPlan : weeksFromPlan) {
                if(weekFromRoom.getId().equals(weekFromPlan.getId())){
                    Set<Day> daysFromRoom = weekFromRoom.getDays();
                    Set<Day> daysFromPlan = weekFromPlan.getDays();
                    for (Day dayFromRoom : daysFromRoom) {
                        for (Day dayFromPlan : daysFromPlan) {
                            if(dayFromPlan.getId().equals(dayFromRoom.getId())){
                                dayFromRoom.getHour().remove(dayFromPlan.getHour());
                            }
                        }
                    }
                }
            }
        }
    }

    private void setAddButton() {
        add.setOnAction(event -> {
            Semester semester = getSelectedItemFromTable(semesterTable);
            StudentGroup studentGroup = getSelectedItemFromTable(groupTable);
            Subject subject = getSelectedItemFromTable(subjectTable);
            Lecturer lecturer = getSelectedItemFromTable(lecturerTable);
            FreeRoom freeRoom = getSelectedItemFromTable(roomsTable);
            List<Week> weeks = getSelectedItemsFromTable(weekTable);
            Day day = getSelectedItemFromTable(dayTable);
            List<Hour> hours = getSelectedItemsFromTable(hourTable);
            day.setHour(hours);
            for (Week week : weeks) {
                week.getDays().add(day);
            }

            Schedule schedule = new Schedule();
            schedule.setSemester(semester);
            schedule.setStudentGroup(studentGroup);
            schedule.setSubject(subject);
            schedule.setLecturer(lecturer);

            BusyRoom busyRoom = new BusyRoom();
            busyRoom.setWeek(weeks);
            busyRoom.setDay(day);
            busyRoom.setNumber(freeRoom.getNumber());
            busyRoom.setRoomSpace(freeRoom.getRoomSpace());

            schedule.setBusyRoom(busyRoom);

            plan.add(schedule);

            setScheduleTable();

        });
    }

    private void setScheduleTable() {
        List<ScheduleRow> scheduleRows = new ArrayList<>();

        for (Schedule schedule : plan) {
            ScheduleRow scheduleRow = new ScheduleRow();
            scheduleRow.setSemester(schedule.getSemester());
            scheduleRow.setStudentGroup(schedule.getStudentGroup());
            scheduleRow.setLecturer(schedule.getLecturer());
            scheduleRow.setBusyRoom(schedule.getBusyRoom());
//            scheduleRow.setDay(schedule.getDay());
            scheduleRow.setHoursFrom(getTimeFrom(schedule));
            scheduleRow.setHoursTo(getTimeTo(schedule));
            scheduleRows.add(scheduleRow);
        }

        Map<TableColumn, String> tableColumns = new HashMap<>();

        tableColumns.put(new SemesterColumn("Semestr"), "semester");
        tableColumns.put(new StudentGroupColumn("Grupy"), "studentGroup");
        tableColumns.put(new SubjectColumn("Przedmiot"), "subject");
        tableColumns.put(new LecturerColumn("Wykładowaca"), "lecturer");
        tableColumns.put(new RoomColumn("Sala"), "freeRoom");
        tableColumns.put(new DayColumn("Dzień"), "day");
        tableColumns.put(new TimeColumn("Od"), "timeFrom");
        tableColumns.put(new TimeColumn("Do"), "timeTo");

        addColumnsForScheduleTable(scheduleTable, tableColumns, scheduleRows);

    }

    private Time getTimeTo(Schedule schedule) {
        Time time = null;
//        List<Hour> hours = schedule.getHour();
//        for (Hour hour : hours) {
//            Time timeFrom = hour.getTimeTo();
//            if (time == null) {
//                time = timeFrom;
//            } else {
//                if (time.compareTo(timeFrom) > 0) {
//                    time = timeFrom;
//                }
//            }
//        }
        return time;
    }

    private Time getTimeFrom(Schedule schedule) {
        Time time = null;
//        List<Hour> hours = schedule.getHour();
//        for (Hour hour : hours) {
//            Time timeFrom = hour.getTimeFrom();
//            if (time == null) {
//                time = timeFrom;
//            } else {
//                if (time.compareTo(timeFrom) < 0) {
//                    time = timeFrom;
//                }
//            }
//        }
        return time;
    }

    private void setMultiSelection() {
        setSelectionModeMultiple(weekTable);
        setSelectionModeMultiple(hourTable);
        setSelectionModeMultiple(dayTable);
    }

    private void setTableListeners() {
        setSemesterTableListener();
        setGroupTableListener();
        setSubjectTableListener();
        setLecturerTableListener();
        setRoomTableListener();
        setWeekTableListener();
        setDayTableListener();
    }

    private void setDayTableListener() {
        dayTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Day day = getSelectedItemFromTable(dayTable);
            if(day != null){
                List<Hour> hours = day.getHour();
                setHourTable(hours);
            }
        });
    }

    private void setHourTable(List<Hour> hours) {
        setColumn(hourTable, new HourColumn("Godziny"), hours);
    }

    private void setWeekTableListener() {
        weekTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Week week = getSelectedItemFromTable(weekTable);
            if(week != null){
                Set<Day> days = week.getDays();
                setDayTable(days);
            }
        });
    }

    private void setDayTable(Set<Day> days) {
        setColumn(dayTable, new DayColumn("Dzień tygodnia"), days);
    }

    private void setRoomTableListener() {
        roomsTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            FreeRoom freeRoom = getSelectedItemFromTable(roomsTable);
            if(freeRoom != null){
                List<Week> week = freeRoom.getWeek();
                setWeekTable(week);
            }
        });
    }

    private void setWeekTable(List<Week> week) {
        setColumn(weekTable, new WeekColumn("Tygodnie"), week);
    }

    private void setLecturerTableListener() {
        lecturerTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Subject subject = getSelectedItemFromTable(subjectTable);
            if (subject != null) {

                setRoomTable(subject);
            }
        });
    }

    private void setRoomTable(Subject subject) {
        Subject eagerSubject = subjectRepositoryService.getEager(subject);
        List<FreeRoom> freeRoom = eagerSubject.getFreeRoom();
        freeRoom.forEach(room1 -> roomRepositoryService.getEager(room1));
        setColumn(roomsTable, new RoomColumn("Sale"), freeRoom);
    }

    private void setSubjectTableListener() {
        subjectTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Subject subject = getSelectedItemFromTable(subjectTable);
            if (subject != null) {
                clearTable(semesterTable);
                setLecturerTable(subject);
            }
        });
    }

    private void setLecturerTable(Subject subject) {
        LecturerFilter filter = new LecturerFilter();
        filter.setSubject(subject);
        List<Lecturer> lecturers = lecturerRepositoryService.findAll(filter);
        setColumn(lecturerTable, new LecturerColumn("Wykładowcy"), lecturers);
    }

    private void setGroupTableListener() {
        groupTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            StudentGroup studentGroup = getSelectedItemFromTable(groupTable);
            if (studentGroup != null) {
                clearTable(groupTable);
                setSubjectTable(studentGroup);
            }
        });
    }

    private void setSubjectTable(StudentGroup studentGroup) {
        Semester semester = studentGroup.getSemester();
        SubjectFilter filter = new SubjectFilter();
        filter.setSemester(semester);
        List<Subject> subjects = subjectRepositoryService.findAll(filter);
        setColumn(subjectTable, new SubjectColumn("Przedmiot"), subjects);
    }

    private void setSemesterTable() {
        semesterTable.getColumns().clear();
        groupTable.getColumns().clear();
        subjectTable.getColumns().clear();
        lecturerTable.getColumns().clear();
        roomsTable.getColumns().clear();
        weekTable.getColumns().clear();
        dayTable.getColumns().clear();
        hourTable.getColumns().clear();
        scheduleTable.getColumns().clear();
        setSemesterTableData();
    }

    private void setSemesterTableListener() {
        semesterTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Semester semester = getSelectedItemFromTable(semesterTable);
            if (semester != null) {
                clearTable(semesterTable);
                setGroupTable(semester);
            }
        });
    }

    private void setGroupTable(Semester semester) {
        StudentGroupFilter filter = new StudentGroupFilter();
        filter.setSemester(semester);
        setColumn(groupTable, new StudentGroupColumn("Grupy"), studentGroupRepositoryService.findAll(filter));
    }

    private void setSemesterTableData() {
        setColumn(semesterTable, new SemesterColumn("Semestr"), translateToObsList(semesterRepositoryService.findAll()));
    }

    private void setNavigationButtons() {
        setBackButton();
        setMainMainButton();
    }

    private void clearTable(TableView tableView) {
        //TODO indexOutOfBound
//        List<TableView> viewList = new ArrayList<>();
//        viewList.save(semesterTable);
//        viewList.save(groupTable);
//        viewList.save(subjectTable);
//        viewList.save(lecturerTable);
//        viewList.save(roomsTable);
//        viewList.save(weekTable);
//        viewList.save();
//        viewList.save(hourTable);
//        viewList.save(scheduleTable);
//
//        Collections.reverse(viewList);
//
//        for (TableView table : viewList) {
//            if (table.equals(tableView)) {
//                return;
//            }
//            table.getColumns().clear();
//        }

    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
